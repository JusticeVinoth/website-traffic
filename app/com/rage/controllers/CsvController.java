/**
 * 
 */
package com.rage.controllers;

import java.util.List;

import com.google.inject.Inject;
import com.rage.models.csv.Csv;
import com.rage.models.csv.service.CsvService;
import com.rage.models.report.service.MainReportService;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author neethithevan.r
 *
 */
public class CsvController extends Controller {

	@Inject
	public CsvService csvServ;

	@Inject
	public MainReportService mainReportServ;

	public Result getLatestCsvData() {
		Csv latestCsvDetails = csvServ.getLatestCsvDetails();
		System.out.println("website :: " + latestCsvDetails);
		if (latestCsvDetails != null) {
			return ok(Json.toJson(latestCsvDetails));
		}
		return ok();
	}

	public Result getCsvDataList() {
		List<Csv> csvList = csvServ.getCsvList();
		csvList.forEach(System.out::println);
		if (!csvList.isEmpty()) {
			return ok(Json.toJson(csvList));
		}
		return ok();
	}

	public Result getCsvDetailsById(String csvId) {
		Csv csvData = csvServ.getCsvDetailById(csvId);
		if (csvData != null) {
			return ok(Json.toJson(csvData));
		}
		return ok();
	}

	public Result deleteCsvUsingId(String csvId) {
		System.out.println("CsvController.deleteCsvUsingId()");
		Csv csv = csvServ.getCsvDetailById(csvId);
		if (csv != null) {
			boolean isDeleteCsv = csvServ.deleteCsvUsingId(csvId);
		}
		List<Csv> csvList = csvServ.getCsvList();
		if (csvList != null && !csvList.isEmpty()) {
			return ok(com.rage.views.html.upload.render(Json.toJson(csvList)));
		} else {
			return ok(com.rage.views.html.upload.render(null));
		}
	}

	public Result getReportStatus(String csvId) {
		System.out.println("CsvController.getReportStatus() :: " + csvId);
		Csv csv = csvServ.getCsvDetailById(csvId);
		if (csv != null && csv.isReportGenerated()) {
			return ok("true");
		} else {
			return ok("false");
		}
	}
}
