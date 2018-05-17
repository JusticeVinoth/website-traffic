/**
 * 
 */
package com.rage.controllers;

import java.util.List;

import com.google.inject.Inject;
import com.rage.models.website.csv.mapping.CsvWebsiteMapping;
import com.rage.models.website.csv.mapping.service.CsvWebsiteMappingService;

import io.ebean.Ebean;
import play.mvc.Controller;
import play.mvc.Result;

/**
 * @author neethithevan.r
 *
 */
public class CsvWebsiteMappingController extends Controller {

	@Inject
	public CsvWebsiteMappingService csvWebsiteMappingServ;

	public Result getcsvWebsiteMappingList(String csvId) {
		List<CsvWebsiteMapping> csvWebsiteMappingList = csvWebsiteMappingServ.getCsvWebsiteMapping(csvId);
		return ok(Ebean.json().toJson(csvWebsiteMappingList));
	}

}
