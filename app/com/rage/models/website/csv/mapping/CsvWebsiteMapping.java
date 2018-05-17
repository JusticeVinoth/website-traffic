/**
 * 
 */
package com.rage.models.website.csv.mapping;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.rage.models.csv.Csv;
import com.rage.models.website.Website;

import io.ebean.Model;

/**
 * @author neethithevan.r
 *
 */
@Entity
@Table(name = "csv_website_mapping")
public class CsvWebsiteMapping extends Model implements Serializable {

	private static final long serialVersionUID = 6824600301920615866L;

	@Id
	private Long id;

	@Column(name = "csv_id")
	private Long csvId;

	@ManyToOne
	@JoinColumn(name = "csv_id", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonBackReference
	private Csv csv;

	@Column(name = "website_id")
	private Long websiteId;

	@ManyToOne
	@JoinColumn(name = "website_id", referencedColumnName = "id", insertable = false, updatable = false)
	@JsonBackReference
	private Website website;

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

	public Csv getCsv() {
		return csv;
	}

	public Long getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Long websiteId) {
		this.websiteId = websiteId;
	}

	public Website getWebsite() {
		return website;
	}

	@Override
	public String toString() {
		return "CsvWebsiteMapping [id=" + id + ", csvId=" + csvId + ", csv=" + csv + ", websiteId=" + websiteId
				+ ", website=" + website + "]";
	}

}
