/**
 * 
 */
package com.rage.models.report.repository.impl;

import com.rage.models.report.provider.ProviderReport;
import com.rage.models.report.repository.ProviderReportRepository;

/**
 * @author neethithevan.r
 *
 */
public class ProviderReportRepositoryImpl implements ProviderReportRepository {

	@Override
	public boolean addProviderReport(ProviderReport providerReport) {
		if(providerReport!=null) {
			providerReport.save();
			return true;
		}
		return false;
	}

}
