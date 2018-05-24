/**
 * 
 */
package com.rage.models.csv.service.impl;

import java.util.List;

import com.google.inject.Inject;
import com.rage.models.csv.Csv;
import com.rage.models.csv.repository.CsvRepository;
import com.rage.models.csv.service.CsvService;

/**
 * @author neethithevan.r
 *
 */
public class CsvServiceImpl implements CsvService {

	@Inject
	public CsvRepository csvRepo;

	@Override
	public Csv addCsvDetail(Csv csv) {
		return csvRepo.addCsvDetails(csv);
	}

	@Override
	public Csv getLatestCsvDetails() {
		return csvRepo.getLatestCsvDetails();
	}

	@Override
	public List<Csv> getCsvList() {
		return csvRepo.getCsvList();
	}

	@Override
	public Csv getCsvDetailById(String csvId) {
		return csvRepo.getCsvDetailById(csvId);
	}

	@Override
	public Csv updateCsvReportStatusUsingId(String id) {
		return csvRepo.updateCsvReportStatusUsingId(id);
	}

	@Override
	public boolean deleteCsvUsingId(String id) {
		return csvRepo.deleteCsvUsingId(id);
	}

}
