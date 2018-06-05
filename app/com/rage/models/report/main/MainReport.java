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
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.rage.models.report.provider.ProviderReport;
import com.rage.models.website.Website;

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

	@Column(name = "site_id")
	private String siteId;

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

	@Column(name = "global_rank")
	private String globalRank;

	@Column(name = "country_rank")
	private String countryRank;

	@Column(name = "country_label")
	private String countryLabel;

	@Column(name = "website_load_time")
	private String websiteLoadTime;

	@Column(name = "website_page_size ")
	private String websitePageSize;

	@Column(name = "domain_name")
	private String domainName;

	@Column(name = "domain_organization")
	private String domainOrganization;

	@Column(name = "domain_fax")
	private String domainFax;

	@Column(name = "domain_phone")
	private String domainPhone;

	@Column(name = "domain_owner_street")
	private String domainOwnerStreet;

	@Column(name = "domain_owner_city")
	private String domainOwnerCity;

	@Column(name = "domain_owner_state")
	private String domainOwnerState;

	@Column(name = "domain_owner_postal_code")
	private String domainOwnerPostalCode;

	@Column(name = "domain_owner_country")
	private String domainOwnerCountry;

	@Column(name = "domain_owner_email")
	private String domainOwnerEmail;

	@Column(name = "domain_registration_date")
	private String domainRegistrationDate;

	@Column(name = "domain_last_updated")
	private String domainLastUpdated;

	@Column(name = "domain_expiration_date")
	private String domainExpirationDate;

	@Column(name = "domain_registrar")
	private String domainRegistrar;

	@Column(name = "domain_sever_ip_address")
	private String domainSeverIpAddress;

	@Column(name = "domain_server_location")
	private String domainServerLocation;

	@Column(name = "domain_hosting_provider")
	private String domainHostingProvider;

	@Column(name = "domain_server_http_code")
	private String domainServerHttpCode;

	@Column(name = "web_server_name")
	private String webServerName;

	@Column(name = "web_server_ip_address")
	private String webServerIPAddress;

	@Column(name = "web_server_country")
	private String webServerCountry;

	@Column(name = "google_pagerank")
	private String googlePagerank;

	@Column(name = "page_speed_score")
	private String pageSpeedScore;

	@Column(name = "mobile_speed_score")
	private String mobileSpeedScore;

	@Column(name = "bounce_rate")
	private String bounceRate;

	@Column(name = "moz_rank")
	private String mozRank;

	@Column(name = "domain_authority")
	private String domainAuthority;

	@Column(name = "page_authority")
	private String pageAuthority;

	@Column(name = "google_safe_browsing")
	private String googleSafeBrowsing;

	@Column(name = "spamhaus_blocklist")
	private String spamhausBlocklist;

	@Column(name = "total_backinks")
	private String totalBackinks;

	@Column(name = "robots_txt")
	private String robotsTxt;

	@Column(name = "sitemap_xml")
	private String sitemapXml;

	@OneToOne
	@JoinColumn(name = "site_id", referencedColumnName = "id", insertable = false, updatable = false)
	private Website website;

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

	public String getSiteId() {
		return siteId;
	}

	public void setSiteId(String siteId) {
		this.siteId = siteId;
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

	public String getGlobalRank() {
		return globalRank;
	}

	public void setGlobalRank(String globalRank) {
		this.globalRank = globalRank;
	}

	public String getCountryRank() {
		return countryRank;
	}

	public void setCountryRank(String countryRank) {
		this.countryRank = countryRank;
	}

	public String getCountryLabel() {
		return countryLabel;
	}

	public void setCountryLabel(String countryLabel) {
		this.countryLabel = countryLabel;
	}

	public String getWebsiteLoadTime() {
		return websiteLoadTime;
	}

	public void setWebsiteLoadTime(String websiteLoadTime) {
		this.websiteLoadTime = websiteLoadTime;
	}

	public String getWebsitePageSize() {
		return websitePageSize;
	}

	public void setWebsitePageSize(String websitePageSize) {
		this.websitePageSize = websitePageSize;
	}

	public String getDomainName() {
		return domainName;
	}

	public void setDomainName(String domainName) {
		this.domainName = domainName;
	}

	public String getDomainOrganization() {
		return domainOrganization;
	}

	public void setDomainOrganization(String domainOrganization) {
		this.domainOrganization = domainOrganization;
	}

	public String getDomainFax() {
		return domainFax;
	}

	public void setDomainFax(String domainFax) {
		this.domainFax = domainFax;
	}

	public String getDomainPhone() {
		return domainPhone;
	}

	public void setDomainPhone(String domainPhone) {
		this.domainPhone = domainPhone;
	}

	public String getDomainRegistrationDate() {
		return domainRegistrationDate;
	}

	public void setDomainRegistrationDate(String domainRegistrationDate) {
		this.domainRegistrationDate = domainRegistrationDate;
	}

	public String getDomainLastUpdated() {
		return domainLastUpdated;
	}

	public void setDomainLastUpdated(String domainLastUpdated) {
		this.domainLastUpdated = domainLastUpdated;
	}

	public String getDomainExpirationDate() {
		return domainExpirationDate;
	}

	public void setDomainExpirationDate(String domainExpirationDate) {
		this.domainExpirationDate = domainExpirationDate;
	}

	public String getDomainRegistrar() {
		return domainRegistrar;
	}

	public void setDomainRegistrar(String domainRegistrar) {
		this.domainRegistrar = domainRegistrar;
	}

	public String getDomainOwnerStreet() {
		return domainOwnerStreet;
	}

	public void setDomainOwnerStreet(String domainOwnerStreet) {
		this.domainOwnerStreet = domainOwnerStreet;
	}

	public String getDomainOwnerCity() {
		return domainOwnerCity;
	}

	public void setDomainOwnerCity(String domainOwnerCity) {
		this.domainOwnerCity = domainOwnerCity;
	}

	public String getDomainOwnerState() {
		return domainOwnerState;
	}

	public void setDomainOwnerState(String domainOwnerState) {
		this.domainOwnerState = domainOwnerState;
	}

	public String getDomainOwnerPostalCode() {
		return domainOwnerPostalCode;
	}

	public void setDomainOwnerPostalCode(String domainOwnerPostalCode) {
		this.domainOwnerPostalCode = domainOwnerPostalCode;
	}

	public String getDomainOwnerCountry() {
		return domainOwnerCountry;
	}

	public void setDomainOwnerCountry(String domainOwnerCountry) {
		this.domainOwnerCountry = domainOwnerCountry;
	}

	public String getDomainOwnerEmail() {
		return domainOwnerEmail;
	}

	public void setDomainOwnerEmail(String domainOwnerEmail) {
		this.domainOwnerEmail = domainOwnerEmail;
	}

	public String getWebServerName() {
		return webServerName;
	}

	public void setWebServerName(String webServerName) {
		this.webServerName = webServerName;
	}

	public String getWebServerIPAddress() {
		return webServerIPAddress;
	}

	public void setWebServerIPAddress(String webServerIPAddress) {
		this.webServerIPAddress = webServerIPAddress;
	}

	public String getWebServerCountry() {
		return webServerCountry;
	}

	public void setWebServerCountry(String webServerCountry) {
		this.webServerCountry = webServerCountry;
	}

	public String getDomainSeverIpAddress() {
		return domainSeverIpAddress;
	}

	public void setDomainSeverIpAddress(String domainSeverIpAddress) {
		this.domainSeverIpAddress = domainSeverIpAddress;
	}

	public String getDomainServerLocation() {
		return domainServerLocation;
	}

	public void setDomainServerLocation(String domainServerLocation) {
		this.domainServerLocation = domainServerLocation;
	}

	public String getDomainHostingProvider() {
		return domainHostingProvider;
	}

	public void setDomainHostingProvider(String domainHostingProvider) {
		this.domainHostingProvider = domainHostingProvider;
	}

	public String getDomainServerHttpCode() {
		return domainServerHttpCode;
	}

	public void setDomainServerHttpCode(String domainServerHttpCode) {
		this.domainServerHttpCode = domainServerHttpCode;
	}

	public String getGooglePagerank() {
		return googlePagerank;
	}

	public void setGooglePagerank(String googlePagerank) {
		this.googlePagerank = googlePagerank;
	}

	public String getPageSpeedScore() {
		return pageSpeedScore;
	}

	public void setPageSpeedScore(String pageSpeedScore) {
		this.pageSpeedScore = pageSpeedScore;
	}

	public String getMobileSpeedScore() {
		return mobileSpeedScore;
	}

	public void setMobileSpeedScore(String mobileSpeedScore) {
		this.mobileSpeedScore = mobileSpeedScore;
	}

	public String getBounceRate() {
		return bounceRate;
	}

	public void setBounceRate(String bounceRate) {
		this.bounceRate = bounceRate;
	}

	public String getMozRank() {
		return mozRank;
	}

	public void setMozRank(String mozRank) {
		this.mozRank = mozRank;
	}

	public String getDomainAuthority() {
		return domainAuthority;
	}

	public void setDomainAuthority(String domainAuthority) {
		this.domainAuthority = domainAuthority;
	}

	public String getPageAuthority() {
		return pageAuthority;
	}

	public void setPageAuthority(String pageAuthority) {
		this.pageAuthority = pageAuthority;
	}

	public String getGoogleSafeBrowsing() {
		return googleSafeBrowsing;
	}

	public void setGoogleSafeBrowsing(String googleSafeBrowsing) {
		this.googleSafeBrowsing = googleSafeBrowsing;
	}

	public String getSpamhausBlocklist() {
		return spamhausBlocklist;
	}

	public void setSpamhausBlocklist(String spamhausBlocklist) {
		this.spamhausBlocklist = spamhausBlocklist;
	}

	public String getTotalBackinks() {
		return totalBackinks;
	}

	public void setTotalBackinks(String totalBackinks) {
		this.totalBackinks = totalBackinks;
	}

	public String getRobotsTxt() {
		return robotsTxt;
	}

	public void setRobotsTxt(String robotsTxt) {
		this.robotsTxt = robotsTxt;
	}

	public String getSitemapXml() {
		return sitemapXml;
	}

	public void setSitemapXml(String sitemapXml) {
		this.sitemapXml = sitemapXml;
	}

	public Website getWebsite() {
		return website;
	}

	public void setWebsite(Website website) {
		this.website = website;
	}

	public List<ProviderReport> getProviderReport() {
		return providerReport;
	}

	public void setProviderReport(List<ProviderReport> providerReport) {
		this.providerReport = providerReport;
	}

}
