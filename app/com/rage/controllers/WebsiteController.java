package com.rage.controllers;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;
import com.rage.models.csv.Csv;
import com.rage.models.csv.service.CsvService;
import com.rage.models.report.main.MainReport;
import com.rage.models.report.service.MainReportService;
import com.rage.models.website.Website;
import com.rage.models.website.csv.mapping.CsvWebsiteMapping;
import com.rage.models.website.csv.mapping.service.CsvWebsiteMappingService;
import com.rage.models.website.service.WebsiteService;
import com.rage.utils.RootClass;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;

public class WebsiteController extends Controller {

	@Inject
	public WebsiteService websiteServ;

	@Inject
	public CsvService csvServ;

	@Inject
	public CsvWebsiteMappingService csvWebsiteMappingServ;

	@Inject
	public MainReportService mainReportServ;

	public Result sayHi() {
		return ok();
	}

	public Result addWebsites() throws IOException {
		Http.MultipartFormData<File> body = request().body().asMultipartFormData();
		Http.MultipartFormData.FilePart<File> csvFile = body.getFile("csvFile");
		Date date = new Date();
		List<Csv> csvList = csvServ.getCsvList();
		String fName = csvFile.getFilename();
		if (csvFile != null && !fName.isEmpty()) {
			int lastIndex = fName.lastIndexOf(".");
			String fileName = fName.substring(0, lastIndex);
			File file = csvFile.getFile();
			FileUtils.copyFile(file, new File("public/uploadFile", fileName + "" + date.getTime() + ".csv"));
			List<Website> websiteList = csvFileRead(file);
			List<Website> websiteUrlLst = websiteServ.addWebsiteUrl(websiteList);
			Csv csvDetails = setCsvData(date, fileName);
			List<CsvWebsiteMapping> csvWebsiteList = setCsvWebsiteMappingData(websiteUrlLst, csvDetails);
			csvWebsiteMappingServ.addCsvWebsiteMapping(csvWebsiteList);

			new Thread(() -> {
				List<MainReport> mainReportList = reportGenerate(csvDetails);
				if (mainReportList != null) {
					csvServ.updateCsvReportStatusUsingId(csvDetails.getId().toString());
				}
			}).start();

			if (csvDetails != null && csvDetails.getId() != null && csvDetails.isReportGenerated()) {
				Csv csvData = csvServ.getCsvDetailById(csvDetails.getId().toString());
				List<MainReport> mainReportList = new ArrayList<>();
				if (csvData != null && csvData.getId() != null) {
					String csvId = csvData.getId().toString();
					mainReportList.addAll(mainReportServ.getMainReport(csvId));
				}
				flash("success", "File uploaded successfully");
				return ok(com.rage.views.html.index.render(Json.toJson(mainReportList)));
			} else if (csvDetails != null && csvDetails.getId() != null && !(csvDetails.isReportGenerated())) {
				ObjectNode resp = Json.newObject();
				resp.put("msg", "Report generating please wait ...");
				resp.put("csvId", csvDetails.getId().toString());
				flash("info", resp.toString());
				return ok(com.rage.views.html.upload.render(Json.toJson(csvList)));
			} else {
				flash("error", "File upload failed");
				return ok(com.rage.views.html.upload.render(null));
			}
		} else {
			flash("error", "File upload failed");
			return ok(com.rage.views.html.upload.render(null));
		}
	}

	private List<MainReport> reportGenerate(Csv csvDetails) {
		Csv latestCsvData = csvServ.getCsvDetailById(csvDetails.getId().toString());
		List<CsvWebsiteMapping> csvWebsiteMapping = csvWebsiteMappingServ
				.getCsvWebsiteMapping(latestCsvData.getId().toString());
		List<Website> websiteUrlList = new ArrayList<>();
		csvWebsiteMapping.forEach(website -> {
			if (website != null && website.getWebsite() != null && !website.getWebsite().getUrl().isEmpty()
					&& website.getWebsite().getId() != null) {
				websiteUrlList.add(website.getWebsite());
			}
		});
		List<MainReport> mainReportList = RootClass.getData(websiteUrlList);
		if (!mainReportList.isEmpty()) {
			mainReportList.forEach(mainReport -> {
				mainReport.setCsvId(latestCsvData.getId());
				mainReportServ.addMainReport(mainReport);
			});
		}
		return mainReportList;
	}

	private List<CsvWebsiteMapping> setCsvWebsiteMappingData(List<Website> websiteUrlLst, Csv csvDetails) {
		List<CsvWebsiteMapping> csvWebsiteList = new ArrayList<>();
		websiteUrlLst.forEach(website -> {
			CsvWebsiteMapping csvWebsiteMapping = new CsvWebsiteMapping();
			if (website.getId() == null) {
				Website existingWebsite = websiteServ.getWebsiteByUrl(website.getUrl());
				website.setId(existingWebsite.getId());
			}
			if (csvDetails.getId() != null) {
				csvWebsiteMapping.setCsvId(csvDetails.getId());
				csvWebsiteMapping.setWebsiteId(website.getId());
				csvWebsiteList.add(csvWebsiteMapping);
			}
		});
		return csvWebsiteList;
	}

	private Csv setCsvData(Date date, String fileName) {
		Csv csv = new Csv();
		csv.setFileName(fileName + "" + date.getTime() + ".csv");
		csv.setCreatedTime(date);
		csv.setReportGenerated(false);
		Csv csvDetails = csvServ.addCsvDetail(csv);
		return csvDetails;
	}

	private List<Website> csvFileRead(File file) throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;
		Scanner scanner = null;
		int index = 0;
		List<Website> websiteList = new ArrayList<>();
		while ((line = reader.readLine()) != null) {
			Website website = new Website();
			scanner = new Scanner(line);
			scanner.useDelimiter(",");
			while (scanner.hasNext()) {
				String data = scanner.next();
				if (index == 0)
					website.setUrl(data);
				else if (index == 1)
					website.setAlexaRanking(data);
				else if (index == 2)
					website.setCountryIp(data);
				else if (index == 3)
					website.setCountryCodePage(data);
				else if (index == 4)
					website.setIp(data);
				else if (index == 5)
					website.setWebServer(data);
				else if (index == 6)
					website.setPhone(data);
				else if (index == 7)
					website.setEmail(data);
				index++;
			}
			index = 0;
			websiteList.add(website);
		}
		reader.close();
		System.out.println(websiteList);
		return websiteList;
	}

	public Result getWebsiteByUrl() {
		String url = request().getQueryString("url");
		Website websiteUrl = websiteServ.getWebsiteByUrl(url);
		System.out.println("websiteUrl ::: " + websiteUrl);
		return ok(com.rage.views.html.index.render(null));
	}

	public Result uploadCsvFile() throws IOException {
		System.out.println();
		Http.MultipartFormData<File> body = request().body().asMultipartFormData();
		Http.MultipartFormData.FilePart<File> csvFile = body.getFile("csvFile");
		if (csvFile != null) {
			String fileName = csvFile.getFilename();
			System.out.println("fileName ::: " + fileName);
			File file = csvFile.getFile();
			FileUtils.moveFile(file, new File("public/uploadFile", fileName));
			return ok("File uploaded");
		} else {
			flash("error", "Missing file");
			return ok("Missing file");
		}
	}

	public Result getWebsiteResult() {
		Csv latestCsvData = csvServ.getLatestCsvDetails();
		List<MainReport> mainReportList = new ArrayList<>();
		if (latestCsvData != null && latestCsvData.getId() != null) {
			String csvId = latestCsvData.getId().toString();
			List<MainReport> mainReportLst = mainReportServ.getMainReport(csvId);
			if (mainReportLst != null) {
				mainReportLst.forEach(mainReport -> {
					if (mainReport != null && mainReport.getSiteId() != null) {
						Website website = websiteServ.getWebsiteById(mainReport.getSiteId());
						mainReport.setWebsite(website);
					}
				});
				mainReportList.addAll(mainReportLst);
			}
			if (mainReportList != null) {
				return ok(com.rage.views.html.index.render(Json.toJson(mainReportList)));
			}
		}
		return ok(com.rage.views.html.index.render(null));
	}

	public Result uploadCsv() {
		List<Csv> csvList = csvServ.getCsvList();
		if (csvList != null && !csvList.isEmpty()) {
			return ok(com.rage.views.html.upload.render(Json.toJson(csvList)));
		} else {
			return ok(com.rage.views.html.upload.render(null));
		}
	}
}
