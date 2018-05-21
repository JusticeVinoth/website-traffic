/**
 * 
 */
package com.rage.models.report.repository.impl;

import java.util.List;

import com.rage.models.report.main.MainReport;
import com.rage.models.report.repository.MainReportRepository;

import io.ebean.Ebean;

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

	@Override
	public List<MainReport> getMainReport(String csvId) {
		List<MainReport> mainReportList = Ebean.find(MainReport.class).fetch("providerReport").where()
				.eq("csvId", csvId).findList();
		if (mainReportList != null) {
			return mainReportList;
		}
		return null;
	}

}
