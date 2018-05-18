/**
 * 
 */
package com.rage.models.report.repository;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.rage.models.report.main.MainReport;
import com.rage.models.report.repository.impl.MainReportRepositoryImpl;

/**
 * @author neethithevan.r
 *
 */
@ImplementedBy(MainReportRepositoryImpl.class)
public interface MainReportRepository {
	
	public boolean addMainReport(MainReport mainReport);
	
	public List<MainReport> getMainReport(String csvId);

}
