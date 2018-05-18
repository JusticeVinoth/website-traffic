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
		List<Csv> csvList = Ebean.find(Csv.class).orderBy("createdTime desc").findList();
		if (!csvList.isEmpty()) {
			return csvList.get(0);
		}
		return null;
	}

	@Override
	public List<Csv> getCsvList() {
		List<Csv> csvList = Ebean.find(Csv.class).orderBy("createdTime desc").findList();
		if (!csvList.isEmpty()) {
			return csvList;
		}
		return null;
	}

	@Override
	public Csv getCsvDetailById(String csvId) {
		Csv csv = Ebean.find(Csv.class).where().eq("id", csvId).findOne();
		if (csv != null) {
			return csv;
		}
		return null;
	}

}
