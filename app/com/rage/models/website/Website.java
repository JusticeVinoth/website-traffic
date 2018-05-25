/**
 * 
 */
package com.rage.models.website;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.rage.models.website.csv.mapping.CsvWebsiteMapping;

import io.ebean.Model;

/**
 * @author neethithevan.r
 *
 */
@Entity
@Table(name = "website")
public class Website extends Model implements Serializable {

	private static final long serialVersionUID = 8259303456445993351L;

	@Id
	private Long id;

	@Column(name = "url")
	private String url;

	@Column(name = "alexa_ranking")
	private String alexaRanking;

	@Column(name = "country_ip")
	private String countryIp;

	@Column(name = "country_code_page")
	private String countryCodePage;

	@Column(name = "ip")
	private String ip;

	@Column(name = "web_server")
	private String webServer;

	@Column(name = "email")
	private String email;

	@OneToMany
	@JoinColumn(name = "website_id", referencedColumnName = "id", nullable = false)
	private List<CsvWebsiteMapping> csvWebsiteMapping;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAlexaRanking() {
		return alexaRanking;
	}

	public void setAlexaRanking(String alexaRanking) {
		this.alexaRanking = alexaRanking;
	}

	public String getCountryIp() {
		return countryIp;
	}

	public void setCountryIp(String countryIp) {
		this.countryIp = countryIp;
	}

	public String getCountryCodePage() {
		return countryCodePage;
	}

	public void setCountryCodePage(String countryCodePage) {
		this.countryCodePage = countryCodePage;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getWebServer() {
		return webServer;
	}

	public void setWebServer(String webServer) {
		this.webServer = webServer;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<CsvWebsiteMapping> getCsvWebsiteMapping() {
		return csvWebsiteMapping;
	}

	public void setCsvWebsiteMapping(List<CsvWebsiteMapping> csvWebsiteMapping) {
		this.csvWebsiteMapping = csvWebsiteMapping;
	}

	@Override
	public String toString() {
		return "Website [id=" + id + ", url=" + url + ", alexaRanking=" + alexaRanking + ", countryIp=" + countryIp
				+ ", countryCodePage=" + countryCodePage + ", ip=" + ip + ", webServer=" + webServer + ", email="
				+ email + ", csvWebsiteMapping=" + csvWebsiteMapping + "]";
	}

}
