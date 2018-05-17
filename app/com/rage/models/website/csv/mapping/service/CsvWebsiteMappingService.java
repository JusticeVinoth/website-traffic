/**
 * 
 */
package com.rage.models.website.csv.mapping.service;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.rage.models.website.csv.mapping.CsvWebsiteMapping;
import com.rage.models.website.csv.mapping.service.impl.CsvWebsiteMappingServiceImpl;

/**
 * @author neethithevan.r
 *
 */
@ImplementedBy(CsvWebsiteMappingServiceImpl.class)
public interface CsvWebsiteMappingService {

	public List<CsvWebsiteMapping> getCsvWebsiteMapping(String csvId);
	
	public boolean addCsvWebsiteMapping(List<CsvWebsiteMapping> csvWebsiteMappingList);
}
