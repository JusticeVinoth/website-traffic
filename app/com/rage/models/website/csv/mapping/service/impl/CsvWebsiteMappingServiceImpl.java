/**
 * 
 */
package com.rage.models.website.csv.mapping.service.impl;

import java.util.List;

import com.google.inject.Inject;
import com.rage.models.website.csv.mapping.CsvWebsiteMapping;
import com.rage.models.website.csv.mapping.repository.CsvWebsiteMappingRepository;
import com.rage.models.website.csv.mapping.service.CsvWebsiteMappingService;

/**
 * @author neethithevan.r
 *
 */
public class CsvWebsiteMappingServiceImpl implements CsvWebsiteMappingService {

	@Inject
	public CsvWebsiteMappingRepository csvWebsiteMappingRepo;

	@Override
	public List<CsvWebsiteMapping> getCsvWebsiteMapping(String csvId) {
		return csvWebsiteMappingRepo.getCsvWebsiteMappingByCsvId(csvId);
	}

	@Override
	public boolean addCsvWebsiteMapping(List<CsvWebsiteMapping> csvWebsiteMappingList) {
		return csvWebsiteMappingRepo.addCsvWebsiteMapping(csvWebsiteMappingList);
	}

}
