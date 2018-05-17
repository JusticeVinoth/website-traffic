/**
 * 
 */
package com.rage.models.csv.service;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.rage.models.csv.Csv;
import com.rage.models.csv.service.impl.CsvServiceImpl;

/**
 * @author neethithevan.r
 *
 */
@ImplementedBy(CsvServiceImpl.class)
public interface CsvService {

		public Csv addCsvDetail(Csv csv);
		
		public Csv getLatestCsvDetails();
		
		public List<Csv> getCsvList();
		
		public Csv getCsvDetailById(String csvId);
}
