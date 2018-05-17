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

	@OneToMany
	@JoinColumn(name = "provider_report_id", referencedColumnName = "id", nullable = false)
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

	public List<ProviderReport> getProviderReport() {
		return providerReport;
	}

	public void setProviderReport(List<ProviderReport> providerReport) {
		this.providerReport = providerReport;
	}

	@Override
	public String toString() {
		return "MainReport [id=" + id + ", csvId=" + csvId + ", siteUrl=" + siteUrl + ", hasFbPage=" + hasFbPage
				+ ", hasTwitterPage=" + hasTwitterPage + ", fbFollowers=" + fbFollowers + ", twitterFollowers="
				+ twitterFollowers + ", fbUrl=" + fbUrl + ", twitterUrl=" + twitterUrl + ", providerReport="
				+ providerReport + "]";
	}

}
