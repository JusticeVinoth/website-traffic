/**
 * 
 */
package com.rage.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

	@Inject
	public WebsiteService websiteServ;

	public Result findWebsiteTraffic() {
		Csv latestCsvData = csvServ.getLatestCsvDetails();
		System.out.println("latestCsvData :: " + latestCsvData);
		List<CsvWebsiteMapping> csvWebsiteMapping = csvWebsiteMappingServ
				.getCsvWebsiteMapping(latestCsvData.getId().toString());
		List<Website> websiteUrlList = new ArrayList<>();
		csvWebsiteMapping.forEach(website -> {
			if (website != null && website.getWebsite() != null && !website.getWebsite().getUrl().isEmpty()) {
				websiteUrlList.add(website.getWebsite());
			}
		});
		if (websiteUrlList != null && !websiteUrlList.isEmpty()) {
			List<MainReport> mainReportList = RootClass.getData(websiteUrlList);
			if (!mainReportList.isEmpty()) {
				mainReportList.forEach(mainReport -> {
					mainReport.setCsvId(latestCsvData.getId());
					mainReportServ.addMainReport(mainReport);
				});
			}
			return ok(Json.toJson(mainReportList));
		}
		return notFound();
	}

	public Result findWebsiteTrafficWithCsvId(String csvId) {
		List<MainReport> mainReportList = mainReportServ.getMainReport(csvId);
		if (mainReportList != null) {
			return ok(com.rage.views.html.index.render(Json.toJson(mainReportList)));
		} else {
			return ok(com.rage.views.html.index.render(null));
		}
	}

	public Result home() {
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
				if (mainReportList != null)
					return ok(com.rage.views.html.index.render(Json.toJson(mainReportList)));
			}
		}
		return ok(com.rage.views.html.index.render(null));
	}

	public Result contactInfo() {
		System.out.println("WebsiteTrafficController.contactInfo()");
		String id = request().getQueryString("id");
		System.out.println("id ::: " + id);
		ObjectNode res = Json.newObject();
		if (id != null) {
			Website website = websiteServ.getWebsiteById(id);
			if (website != null) {
				res.put("phone", website.getPhone());
				res.put("email", website.getEmail());
				res.put("success", true);
				return ok(res);
			} else {
				res.put("success", false);
				res.put("response", "Resource not found");
				return ok(res);
			}
		}
		res.put("success", false);
		res.put("response", "Resource not found");
		return ok(res);
	}

	public Result contactInfoUpdate() {
		Map<String, String[]> data = request().body().asFormUrlEncoded();

		System.out.println("data ::: " + data);
		ObjectNode res = Json.newObject();
		if (data != null && !data.isEmpty() && data.get("id") != null && data.get("id")[0] != null
				&& data.get("phone") != null && data.get("phone")[0] != null && data.get("email") != null
				&& data.get("email")[0] != null) {
			String id = data.get("id")[0];
			String phone = data.get("phone")[0];
			String email = data.get("email")[0];
			Website website = new Website();
			website.setPhone(phone);
			website.setEmail(email);
			boolean isUpdateWebsite = websiteServ.updateWebsite(id, website);
			if (isUpdateWebsite) {
				res.put("success", true);
				res.put("msg", "Updated successfully");
				return ok(res);
			} else {
				res.put("success", false);
				res.put("msg", "Updated unsuccessfully");
				return ok(res);
			}
		} else {
			res.put("success", false);
			res.put("msg", "Invalid data");
			return ok(res);
		}
	}
}
