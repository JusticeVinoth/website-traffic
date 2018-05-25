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

	@Column(name = "traffic_Count")
	private String trafficCount;

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

	public String getTrafficCount() {
		return trafficCount;
	}

	public void setTrafficCount(String trafficCount) {
		this.trafficCount = trafficCount;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
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
