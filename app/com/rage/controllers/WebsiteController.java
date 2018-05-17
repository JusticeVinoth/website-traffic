package com.rage.controllers;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.google.inject.Inject;
import com.rage.models.csv.Csv;
import com.rage.models.csv.service.CsvService;
import com.rage.models.website.Website;
import com.rage.models.website.csv.mapping.CsvWebsiteMapping;
import com.rage.models.website.csv.mapping.service.CsvWebsiteMappingService;
import com.rage.models.website.service.WebsiteService;

import play.mvc.Controller;
import play.mvc.Result;

public class WebsiteController extends Controller {

	@Inject
	public WebsiteService websiteServ;

	@Inject
	public CsvService csvServ;

	@Inject
	public CsvWebsiteMappingService csvWebsiteMappingServ;

	public Result sayHi() {
		return ok();
	}

	public Result addWebsites() throws IOException {
		List<Website> websiteList = csvFileRead();
		websiteList.forEach(System.out::println);
		List<Website> websiteUrlLst = websiteServ.addWebsiteUrl(websiteList);
		Csv csv = new Csv();
		csv.setFileName("website");
		csv.setCreatedTime(new Date());
		Csv csvDetails = csvServ.addCsvDetail(csv);

		List<CsvWebsiteMapping> csvWebsiteList = new ArrayList<>();
		websiteUrlLst.forEach(website -> {
			CsvWebsiteMapping csvWebsiteMapping = new CsvWebsiteMapping();
			if(website.getId() == null) {
				Website existingWebsite = websiteServ.getWebsiteByUrl(website.getUrl());
				website.setId(existingWebsite.getId());
			}
			if (csvDetails.getId() != null ) {
				csvWebsiteMapping.setCsvId(csvDetails.getId());
				csvWebsiteMapping.setWebsiteId(website.getId());
				csvWebsiteList.add(csvWebsiteMapping);
			}
		});
		csvWebsiteMappingServ.addCsvWebsiteMapping(csvWebsiteList);
		return ok();
	}

	private List<Website> csvFileRead() throws FileNotFoundException, IOException {
		BufferedReader reader = new BufferedReader(new FileReader("C:\\Users\\neethithevan.r\\Desktop\\website.csv"));
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
				System.out.println("invalid data::" + data);
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
		return ok();
	}

}
