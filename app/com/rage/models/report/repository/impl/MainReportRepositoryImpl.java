/**
 * 
 */
package com.rage.models.report.repository.impl;

import com.rage.models.report.main.MainReport;
import com.rage.models.report.repository.MainReportRepository;

/**
 * @author neethithevan.r
 *
 */
public class MainReportRepositoryImpl implements MainReportRepository {

	@Override
	public boolean addMainReport(MainReport mainReport) {
		if (mainReport != null) {
			mainReport.save();
			mainReport.getProviderReport().forEach(providerReport -> {
				providerReport.setMainReportId(mainReport.getId());
				providerReport.save();
			});
			return true;
		}
		return false;
	}

}
