/**
 * 
 */
package com.rage.models.csv.repository;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.rage.models.csv.Csv;
import com.rage.models.csv.repository.impl.CsvRepositoryImpl;

/**
 * @author neethithevan.r
 *
 */

@ImplementedBy(CsvRepositoryImpl.class)
public interface CsvRepository {
	
	public Csv addCsvDetails(Csv csv);
	
	public Csv getLatestCsvDetails();
	
	public List<Csv> getCsvList();
	
	public Csv getCsvDetailById(String csvId);
	
	public Csv updateCsvReportStatusUsingId(String id);
	
	public boolean deleteCsvUsingId(String id);
}
