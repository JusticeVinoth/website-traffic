/**
 * 
 */
package com.rage.models.report.service.impl;

import com.google.inject.Inject;
import com.rage.models.report.provider.ProviderReport;
import com.rage.models.report.repository.ProviderReportRepository;
import com.rage.models.report.service.ProviderReportService;

/**
 * @author neethithevan.r
 *
 */
public class ProviderReportServiceImpl implements ProviderReportService {

	@Inject
	public ProviderReportRepository providerReportRepo;

	@Override
	public boolean addPrividerReport(ProviderReport providerReport) {
		return providerReportRepo.addProviderReport(providerReport);
	}

}
