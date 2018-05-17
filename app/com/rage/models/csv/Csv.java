/**
 * 
 */
package com.rage.models.csv;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.rage.models.website.csv.mapping.CsvWebsiteMapping;

import io.ebean.Model;
import play.data.format.Formats.DateTime;

/**
 * @author neethithevan.r
 *
 */
@Entity
@Table(name = "csv")
public class Csv extends Model implements Serializable {

	private static final long serialVersionUID = 5322400743066014581L;

	@Id
	private Long id;

	@Column
	private String fileName;

	@Temporal(TemporalType.TIMESTAMP)
	@OrderBy("")
	@Column(name = "created_time", columnDefinition = "datetime", updatable = false)
	@DateTime(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createdTime;

	@OneToMany
	@JoinColumn(name = "csv_id", referencedColumnName = "id", nullable = false)
	private List<CsvWebsiteMapping> csvWebsiteMapping;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}

	public List<CsvWebsiteMapping> getCsvWebsiteMapping() {
		return csvWebsiteMapping;
	}

	public void setCsvWebsiteMapping(List<CsvWebsiteMapping> csvWebsiteMapping) {
		this.csvWebsiteMapping = csvWebsiteMapping;
	}

	@Override
	public String toString() {
		return "Csv [id=" + id + ", fileName=" + fileName + ", createdTime=" + createdTime + ", csvWebsiteMapping="
				+ csvWebsiteMapping + "]";
	}

}
