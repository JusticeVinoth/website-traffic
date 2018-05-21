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
		Csv csv = csvServ.getCsvDetailById(csvId);
		if (csvId != null && csv != null) {
			boolean isDeleteCsv = csvServ.deleteCsvUsingId(csvId);
			System.out.println("isDeleteCsv :: "+isDeleteCsv);
		}
		List<Csv> csvList = csvServ.getCsvList();
		Csv latestCsvData = csvServ.getLatestCsvDetails();
		List<MainReport> mainReportList = new ArrayList<>();
		if (latestCsvData != null && latestCsvData.getId() != null) {
			String id = latestCsvData.getId().toString();
			mainReportList.addAll(mainReportServ.getMainReport(id));
		}
		if (csvList != null && !csvList.isEmpty() && mainReportList != null) {
			return ok(com.rage.views.html.index.render(Json.toJson(csvList), Json.toJson(mainReportList)));
		} else if (csvList != null && !csvList.isEmpty() && csvList != null) {
			return ok(com.rage.views.html.index.render(Json.toJson(csvList), null));
		}
		return ok(com.rage.views.html.index.render(null, null));
	}
}
