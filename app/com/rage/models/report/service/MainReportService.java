/**
 * 
 */
package com.rage.models.report.service;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.rage.models.report.main.MainReport;
import com.rage.models.report.service.impl.MainReportServiceImpl;

/**
 * @author neethithevan.r
 *
 */
@ImplementedBy(MainReportServiceImpl.class)
public interface MainReportService {
	
	public boolean addMainReport(MainReport mainReport);
	
	public List<MainReport> getMainReport(String csvId);
}
