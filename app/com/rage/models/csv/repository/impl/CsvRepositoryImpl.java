/**
 * 
 */
package com.rage.models.csv.repository.impl;

import java.util.List;

import com.rage.models.csv.Csv;
import com.rage.models.csv.repository.CsvRepository;

import io.ebean.CallableSql;
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

	@Override
	public Csv updateCsvReportStatusUsingId(String id) {
		if (id != null) {
			Csv csv = getCsvDetailById(id);
			csv.setReportGenerated(true);
			csv.update();
			return csv;
		}
		return null;
	}

	@Override
	public boolean deleteCsvUsingId(String id) {
		Csv csv = getCsvDetailById(id);
		if (id != null && csv != null) {
			String deleteQueryForMapping = "delete from csv_website_mapping where csv_website_mapping.csv_id=" + id;
			String deleteQueryForCsv = "delete from csv where id=" + id;
			String deleteQueryForReport = "delete from provider_report  where main_report_id in (select id from main_report where csv_id="
					+ id + ")";
			String deleteQueryForMainReport = "delete from main_report where csv_id=" + id;
			CallableSql callableSqlForMapping = Ebean.createCallableSql(deleteQueryForMapping);
			CallableSql callableSqlForCsv = Ebean.createCallableSql(deleteQueryForCsv);
			CallableSql callableSqlForReport = Ebean.createCallableSql(deleteQueryForReport);
			CallableSql callableSqlForMainReport = Ebean.createCallableSql(deleteQueryForMainReport);
			Ebean.execute(callableSqlForMapping);
			Ebean.execute(callableSqlForCsv);
			Ebean.execute(callableSqlForReport);
			Ebean.execute(callableSqlForMainReport);
			return true;
		}
		return false;
	}

}
