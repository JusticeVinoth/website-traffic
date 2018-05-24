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
import com.rage.models.website.csv.mapping.CsvWebsiteMapping;
import com.rage.models.website.csv.mapping.service.CsvWebsiteMappingService;
import com.rage.utils.RootClass;

import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Http.RequestBody;
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
		List<MainReport> mainReportList = new ArrayList<>();
		mainReportList.addAll(mainReportServ.getMainReport(csvId));
		if (mainReportList != null) {
			return ok(com.rage.views.html.index.render(Json.toJson(mainReportList)));
		}else {
			return ok(com.rage.views.html.index.render(null));
		}
	}

	public Result home() {
		Csv latestCsvData = csvServ.getLatestCsvDetails();
		List<MainReport> mainReportList = new ArrayList<>();
		if (latestCsvData != null && latestCsvData.getId() != null) {
			String csvId = latestCsvData.getId().toString();
			mainReportList.addAll(mainReportServ.getMainReport(csvId));
		}
		if (mainReportList != null) {
			return ok(com.rage.views.html.index.render(Json.toJson(mainReportList)));
		} else {
			return ok(com.rage.views.html.index.render(null));
		}
	}

	
	public Result contactInfo(){
		String id = request().getQueryString("id");

		System.out.println("id:"+id);
			/*Fetch contact info from db here*/
		
		ObjectNode res = Json.newObject();
		res.put("phone", "97760987");
		res.put("email", "b@gmail.com");
		return ok(res);
	}
	
	public Result contactInfoUpdate(){
		Map<String, String[]> data = request().body().asFormUrlEncoded();

		ObjectNode res = Json.newObject();
		if(data.get("id")==null){
			res.put("success", false);
			res.put("msg", "Invalid data");
			return ok(res);
		}else{
			if(data.get("phone")!=null && data.get("phone")[0]!=null ){
				System.out.println("ph:"+data.get("phone")[0]);
			}
			if(data.get("email")!=null&& data.get("email")[0]!=null){

				System.out.println("email:"+data.get("email")[0]);
			}
			/*Update contact info here*/
			
			res.put("success", true);
			res.put("msg", "Updated successfully");
			return ok(res);
		}
		
		
	}
}
