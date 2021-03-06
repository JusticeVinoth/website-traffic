/**
 * 
 */
package com.rage.models.report.service.impl;

import java.util.List;

import com.google.inject.Inject;
import com.rage.models.report.main.MainReport;
import com.rage.models.report.repository.MainReportRepository;
import com.rage.models.report.service.MainReportService;

/**
 * @author neethithevan.r
 *
 */
public class MainReportServiceImpl implements MainReportService {

	@Inject
	public MainReportRepository mainReportRepo;

	@Override
	public boolean addMainReport(MainReport mainReport) {
		return mainReportRepo.addMainReport(mainReport);
	}

	@Override
	public List<MainReport> getMainReport(String csvId) {
		return mainReportRepo.getMainReport(csvId);
	}

}
