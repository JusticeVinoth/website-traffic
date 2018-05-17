/**
 * 
 */
package com.rage.models.website.csv.mapping.repository.impl;

import java.util.List;

import com.rage.models.website.csv.mapping.CsvWebsiteMapping;
import com.rage.models.website.csv.mapping.repository.CsvWebsiteMappingRepository;

import io.ebean.Ebean;

/**
 * @author neethithevan.r
 *
 */
public class CsvWebsiteMappingRepositoryImpl implements CsvWebsiteMappingRepository {

	@Override
	public List<CsvWebsiteMapping> getCsvWebsiteMappingByCsvId(String csvId) {
		List<CsvWebsiteMapping> csvWebsiteMappingList = Ebean.find(CsvWebsiteMapping.class).fetch("website").where()
				.eq("csv_id", csvId).findList();
		return csvWebsiteMappingList;
	}

	@Override
	public boolean addCsvWebsiteMapping(List<CsvWebsiteMapping> csvWebsiteMappingList) {
		if (!csvWebsiteMappingList.isEmpty()) {
			csvWebsiteMappingList.forEach(csvWebsiteMapping -> {
				csvWebsiteMapping.save();
			});
			return true;
		}
		return false;
	}

}
