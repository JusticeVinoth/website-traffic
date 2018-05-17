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
public class Website extends Model implements Serializable{

	private static final long serialVersionUID = 8259303456445993351L;
	
	@Id
	private Long id;
	
	@Column(name = "url")
	private String url;
	
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

	public List<CsvWebsiteMapping> getCsvWebsiteMapping() {
		return csvWebsiteMapping;
	}

	public void setCsvWebsiteMapping(List<CsvWebsiteMapping> csvWebsiteMapping) {
		this.csvWebsiteMapping = csvWebsiteMapping;
	}

	@Override
	public String toString() {
		return "Website [id=" + id + ", url=" + url + ", csvWebsiteMapping=" + csvWebsiteMapping + "]";
	}

}
