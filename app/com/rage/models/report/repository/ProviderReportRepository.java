/**
 * 
 */
package com.rage.models.report.repository;

import com.google.inject.ImplementedBy;
import com.rage.models.report.provider.ProviderReport;
import com.rage.models.report.repository.impl.ProviderReportRepositoryImpl;

/**
 * @author neethithevan.r
 *
 */
@ImplementedBy(ProviderReportRepositoryImpl.class)
public interface ProviderReportRepository {

	public boolean addProviderReport(ProviderReport providerReport);
	
}
