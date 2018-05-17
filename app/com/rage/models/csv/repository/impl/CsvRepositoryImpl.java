/**
 * 
 */
package com.rage.models.csv.repository.impl;

import java.util.List;

import com.rage.models.csv.Csv;
import com.rage.models.csv.repository.CsvRepository;

import io.ebean.Ebean;

/**
 * @author neethithevan.r
 *
 */
public class CsvRepositoryImpl implements CsvRepository {

	@Override
	public Csv addCsvDetails(Csv csv) {
		if (csv != null) {
			csv.save();
			return csv;
		}
		return null;
		
	}

	@Override
	public Csv getLatestCsvDetails() {
		return Ebean.find(Csv.class).orderBy("createdTime desc").findList().get(0);
	}

	@Override
	public List<Csv> getCsvList() {
		return Ebean.find(Csv.class).findList();
	}

	@Override
	public Csv getCsvDetailById(String csvId) {
		return Ebean.find(Csv.class).where().eq("id", csvId).findOne();
	}

}
