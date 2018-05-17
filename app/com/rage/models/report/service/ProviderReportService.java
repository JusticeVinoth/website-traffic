/**
 * 
 */
package com.rage.models.report.service;

import com.google.inject.ImplementedBy;
import com.rage.models.report.provider.ProviderReport;
import com.rage.models.report.service.impl.ProviderReportServiceImpl;

/**
 * @author neethithevan.r
 *
 */
@ImplementedBy(ProviderReportServiceImpl.class)
public interface ProviderReportService {
	
	public boolean addPrividerReport(ProviderReport providerReport);
}
