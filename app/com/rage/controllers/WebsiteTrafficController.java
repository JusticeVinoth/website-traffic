/**
 * 
 */
package com.rage.controllers;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.rage.models.csv.Csv;
import com.rage.models.csv.service.CsvService;
import com.rage.models.report.main.MainReport;
import com.rage.models.report.service.MainReportService;
import com.rage.models.website.csv.mapping.CsvWebsiteMapping;
import com.rage.models.website.csv.mapping.service.CsvWebsiteMappingService;
import com.rage.utils.RootClass;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author neethithevan.r
 *
 */
public class WebsiteTrafficController extends Controller {

	@Inject
	public CsvService csvServ;

	@Inject
	public MainReportService mainReportServ;

	@Inject
	public CsvWebsiteMappingService csvWebsiteMappingServ;

	public Result findWebsiteTraffic() {
		Csv latestCsvData = csvServ.getLatestCsvDetails();
		System.out.println("latestCsvData :: " + latestCsvData);
		List<CsvWebsiteMapping> csvWebsiteMapping = csvWebsiteMappingServ
				.getCsvWebsiteMapping(latestCsvData.getId().toString());
		List<String> websiteUrlList = new ArrayList<>();
		csvWebsiteMapping.forEach(website -> {
			if (website != null && website.getWebsite() != null && !website.getWebsite().getUrl().isEmpty()) {
				String url = website.getWebsite().getUrl();
				websiteUrlList.add(url);
			}
		});
		List<MainReport> mainReportList = RootClass.getData(websiteUrlList);
		if (!mainReportList.isEmpty()) {
			mainReportList.forEach(mainReport -> {
				mainReport.setCsvId(latestCsvData.getId());
				mainReportServ.addMainReport(mainReport);
			});
		}
		return ok(Json.toJson(mainReportList));
	}

	public Result findWebsiteTrafficWithCsvId(String csvId) {
		List<Csv> csvList = csvServ.getCsvList();
		List<MainReport> mainReportList = new ArrayList<>();
		mainReportList.addAll(mainReportServ.getMainReport(csvId));
		if (csvList!=null && !csvList.isEmpty() && mainReportList != null) {
			return ok(com.rage.views.html.index.render(Json.toJson(csvList), Json.toJson(mainReportList)));
		} else if (csvList!=null && !csvList.isEmpty() && csvList != null) {
			return ok(com.rage.views.html.index.render(Json.toJson(csvList), null));
		}
		return ok(com.rage.views.html.index.render(null, null));
	}

	public Result home() {
		List<Csv> csvList = csvServ.getCsvList();
		Csv latestCsvData = csvServ.getLatestCsvDetails();
		List<MainReport> mainReportList = new ArrayList<>();
		if (latestCsvData != null && latestCsvData.getId() != null) {
			String csvId = latestCsvData.getId().toString();
			mainReportList.addAll(mainReportServ.getMainReport(csvId));
		}
		if (csvList!=null && !csvList.isEmpty() && mainReportList != null) {
			return ok(com.rage.views.html.index.render(Json.toJson(csvList), Json.toJson(mainReportList)));
		} else if (csvList!=null && !csvList.isEmpty() && csvList != null) {
			return ok(com.rage.views.html.index.render(Json.toJson(csvList), null));
		}
		return ok(com.rage.views.html.index.render(null, null));
	}
	
}
