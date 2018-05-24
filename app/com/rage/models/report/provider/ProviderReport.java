/**
 * 
 */
package com.rage.models.report.provider;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rage.models.report.main.MainReport;

import io.ebean.Model;

/**
 * @author neethithevan.r
 *
 */

@Entity
@Table(name = "provider_report")
public class ProviderReport extends Model implements Serializable {

	private static final long serialVersionUID = -1755112904534281345L;

	@Id
	private Long id;

	@Column(name = "search_site_url")
	private String searchSiteUrl;

	@Column(name = "site_name")
	private String siteName;

	@Column(name = "daily_unique_visitors")
	private String dailyUniqueVisitors;
	
	@Column(name = "daily_revenue")
	private String dailyRevenue;
	
	@Column(name = "daily_unique_pageviews")
	private String dailyUniquePageviews;
	
	@Column(name = "monthly_unique_visitors")
	private String monthlyUniqueVisitors;
	
	@Column(name = "monthly_revenue")
	private String monthlyRevenue;
	
	@Column(name = "monthly_unique_pageviews")
	private String monthlyUniquePageviews;
	
	@Column(name = "yearly_unique_visitors")
	private String yearlyUniqueVisitors;
	
	@Column(name = "yearly_revenue")
	private String yearlyRevenue;
	
	@Column(name = "yearly_unique_pageviews")
	private String yearlyUniquePageviews;

	@Column(name = "main_report_id")
	private Long mainReportId;

	@ManyToOne
	@JoinColumn(name = "main_report_id", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonBackReference
	private MainReport mainReport;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getSearchSiteUrl() {
		return searchSiteUrl;
	}

	public void setSearchSiteUrl(String searchSiteUrl) {
		this.searchSiteUrl = searchSiteUrl;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public String getDailyUniqueVisitors() {
		return dailyUniqueVisitors;
	}

	public void setDailyUniqueVisitors(String dailyUniqueVisitors) {
		this.dailyUniqueVisitors = dailyUniqueVisitors;
	}

	public String getDailyRevenue() {
		return dailyRevenue;
	}

	public void setDailyRevenue(String dailyRevenue) {
		this.dailyRevenue = dailyRevenue;
	}

	public String getDailyUniquePageviews() {
		return dailyUniquePageviews;
	}

	public void setDailyUniquePageviews(String dailyUniquePageviews) {
		this.dailyUniquePageviews = dailyUniquePageviews;
	}

	public String getMonthlyUniqueVisitors() {
		return monthlyUniqueVisitors;
	}

	public void setMonthlyUniqueVisitors(String monthlyUniqueVisitors) {
		this.monthlyUniqueVisitors = monthlyUniqueVisitors;
	}

	public String getMonthlyRevenue() {
		return monthlyRevenue;
	}

	public void setMonthlyRevenue(String monthlyRevenue) {
		this.monthlyRevenue = monthlyRevenue;
	}

	public String getMonthlyUniquePageviews() {
		return monthlyUniquePageviews;
	}

	public void setMonthlyUniquePageviews(String monthlyUniquePageviews) {
		this.monthlyUniquePageviews = monthlyUniquePageviews;
	}

	public String getYearlyUniqueVisitors() {
		return yearlyUniqueVisitors;
	}

	public void setYearlyUniqueVisitors(String yearlyUniqueVisitors) {
		this.yearlyUniqueVisitors = yearlyUniqueVisitors;
	}

	public String getYearlyRevenue() {
		return yearlyRevenue;
	}

	public void setYearlyRevenue(String yearlyRevenue) {
		this.yearlyRevenue = yearlyRevenue;
	}

	public String getYearlyUniquePageviews() {
		return yearlyUniquePageviews;
	}

	public void setYearlyUniquePageviews(String yearlyUniquePageviews) {
		this.yearlyUniquePageviews = yearlyUniquePageviews;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getMainReportId() {
		return mainReportId;
	}

	public void setMainReportId(Long mainReportId) {
		this.mainReportId = mainReportId;
	}

	public MainReport getMainReport() {
		return mainReport;
	}

	public void setMainReport(MainReport mainReport) {
		this.mainReport = mainReport;
	}

}
