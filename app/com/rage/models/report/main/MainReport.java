/**
 * 
 */
package com.rage.models.report.main;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.rage.models.report.provider.ProviderReport;

import io.ebean.Model;

/**
 * @author neethithevan.r
 *
 */

@Entity
@Table(name = "main_report")
public class MainReport extends Model implements Serializable {

	private static final long serialVersionUID = 6875097553312939613L;

	@Id
	private Long id;

	@Column(name = "csv_id")
	private Long csvId;

	@Column(name = "site_url")
	private String siteUrl;

	@Column(name = "has_fB_page")
	private String hasFbPage;

	@Column(name = "has_twitter_page")
	private String hasTwitterPage;

	@Column(name = "fb_followers")
	private String fbFollowers;

	@Column(name = "twitter_followers")
	private String twitterFollowers;

	@Column(name = "fb_url")
	private String fbUrl;

	@Column(name = "twitter_urlL")
	private String twitterUrl;
	
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

	@OneToMany
	@JoinColumn(name = "main_report_id", referencedColumnName = "id", nullable = false)
	private List<ProviderReport> providerReport;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCsvId() {
		return csvId;
	}

	public void setCsvId(Long csvId) {
		this.csvId = csvId;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public void setSiteUrl(String siteUrl) {
		this.siteUrl = siteUrl;
	}

	public String getHasFbPage() {
		return hasFbPage;
	}

	public void setHasFbPage(String hasFbPage) {
		this.hasFbPage = hasFbPage;
	}

	public String getHasTwitterPage() {
		return hasTwitterPage;
	}

	public void setHasTwitterPage(String hasTwitterPage) {
		this.hasTwitterPage = hasTwitterPage;
	}

	public String getFbFollowers() {
		return fbFollowers;
	}

	public void setFbFollowers(String fbFollowers) {
		this.fbFollowers = fbFollowers;
	}

	public String getTwitterFollowers() {
		return twitterFollowers;
	}

	public void setTwitterFollowers(String twitterFollowers) {
		this.twitterFollowers = twitterFollowers;
	}

	public String getFbUrl() {
		return fbUrl;
	}

	public void setFbUrl(String fbUrl) {
		this.fbUrl = fbUrl;
	}

	public String getTwitterUrl() {
		return twitterUrl;
	}

	public void setTwitterUrl(String twitterUrl) {
		this.twitterUrl = twitterUrl;
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

	public List<ProviderReport> getProviderReport() {
		return providerReport;
	}

	public void setProviderReport(List<ProviderReport> providerReport) {
		this.providerReport = providerReport;
	}

	
}
