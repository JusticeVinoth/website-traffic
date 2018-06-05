/**
 * 
 */
package com.rage.controllers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;
import com.rage.models.csv.Csv;
import com.rage.models.csv.service.CsvService;
import com.rage.models.report.main.MainReport;
import com.rage.models.report.service.MainReportService;
import com.rage.models.website.Website;
import com.rage.models.website.service.WebsiteService;
import com.rage.utils.ReportGenerationUtils;

import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author neethithevan.r
 *
 */
public class ReportGeneration extends Controller {

	@Inject
	public MainReportService mainReportServ;

	@Inject
	public CsvService csvServ;

	@Inject
	public WebsiteService websiteServ;

	public Result getReport(String csvId) throws IOException {
		Csv csv = csvServ.getCsvDetailById(csvId);
		List<MainReport> mainReportList = new ArrayList<>();
		if (csv != null && csv.getId() != null) {
			String csv_Id = csv.getId().toString();
			List<MainReport> mainReportLst = mainReportServ.getMainReport(csv_Id);
			mainReportLst.forEach(mainReport -> {
				if (mainReport != null && mainReport.getSiteId() != null) {
					Website website = websiteServ.getWebsiteById(mainReport.getSiteId());
					mainReport.setWebsite(website);
				}
			});
			mainReportList.addAll(mainReportLst);
			File reportFile = ReportGenerationUtils.createExcelFile(mainReportList);
			return ok(reportFile);
		}
		return notFound();
	}

}
