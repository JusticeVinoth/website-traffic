package com.rage.models.website.csv.mapping.repository;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.rage.models.website.csv.mapping.CsvWebsiteMapping;
import com.rage.models.website.csv.mapping.repository.impl.CsvWebsiteMappingRepositoryImpl;

@ImplementedBy(CsvWebsiteMappingRepositoryImpl.class)
public interface CsvWebsiteMappingRepository {

	public List<CsvWebsiteMapping> getCsvWebsiteMappingByCsvId(String csvId);
	
	public boolean addCsvWebsiteMapping(List<CsvWebsiteMapping> csvWebsiteMappingList);
}
