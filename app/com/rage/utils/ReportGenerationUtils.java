package com.rage.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;

import com.rage.models.report.main.MainReport;
import com.rage.models.report.provider.ProviderReport;

public class ReportGenerationUtils {

	public static File createExcelFile(List<MainReport> mainReportList) {

		File excelFile = null;
		try {
			String reportName = "Report";
			Date date = new Date();
			String fileName = reportName.replace(" ", "_") + "_" + date.getTime() + ".xlsx";
			String file = fileName;
			excelFile = new File(file);
			int rowCount = 0;
			// Create workbook
			HSSFWorkbook wb = new HSSFWorkbook();
			// creation helper
			CreationHelper creationHelper = wb.getCreationHelper();

			// Sheet creation
			Sheet sheet = wb.createSheet("Report-1");

			// Create a Font for styling header cells
			Font headerFont = wb.createFont();
			headerFont.setBold(true);
			headerFont.setFontHeightInPoints((short) 10);
			headerFont.setColor(IndexedColors.BLACK.index);

			// Create a CellStyle with the font
			CellStyle headerCellStyle = wb.createCellStyle();
			headerCellStyle.setFillBackgroundColor(IndexedColors.BLUE.index);
			headerCellStyle.setFillForegroundColor(IndexedColors.GREY_50_PERCENT.index);
			headerCellStyle.setFont(headerFont);

			createHeader(mainReportList, sheet, headerCellStyle, rowCount, wb);

			// Create Cell Style for formatting Date
			CellStyle dateCellStyle = wb.createCellStyle();
			dateCellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("dd-MM-yyyy"));

			// sheet.autoSizeColumn(0);

			FileOutputStream fileOut;
			fileOut = new FileOutputStream(excelFile);
			wb.write(fileOut);
			fileOut.close();
			wb.close();

		} catch (IOException e) {
			System.out.println("Exception ::: " + e.getMessage());
			e.printStackTrace();
		}
		return excelFile;
	}

	private static void createHeader(List<MainReport> mainReportList, Sheet sheet, CellStyle headerCellStyle,
			int rowCount, HSSFWorkbook wb) {
		// Create a Row
		int rowNumStart = 0, rowNumEnd = 1, colNumStart = 0, colNumEnd = 0, cellNum = 0;
		System.out.println(rowCount);
		Row headerRow = sheet.createRow(rowCount);
		rowCount += 1;
		Row row = sheet.createRow(rowCount);
		if (mainReportList != null && !mainReportList.isEmpty()) {
			MainReport mainReport = mainReportList.get(0);
			if (mainReport.getSiteUrl() != null) {
				mergeRowHeader(sheet, headerCellStyle, rowNumStart, rowNumEnd, colNumStart, colNumEnd, cellNum,
						"SITE URL", headerRow);
			}
			if (mainReport.getWebsite() != null && mainReport.getWebsite().getEmail() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				mergeRowHeader(sheet, headerCellStyle, rowNumStart, rowNumEnd, colNumStart, colNumEnd, cellNum,
						"EMAIL ID", headerRow);
			}
			if (mainReport.getWebsite() != null && mainReport.getWebsite().getPhone() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				mergeRowHeader(sheet, headerCellStyle, rowNumStart, rowNumEnd, colNumStart, colNumEnd, cellNum, "PHONE",
						headerRow);
			}
			if (mainReport.getFbFollowers() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				mergeRowHeader(sheet, headerCellStyle, rowNumStart, rowNumEnd, colNumStart, colNumEnd, cellNum,
						"FACEBOOK FOLLOWERS", headerRow);
			}
			if (mainReport.getTwitterFollowers() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				mergeRowHeader(sheet, headerCellStyle, rowNumStart, rowNumEnd, colNumStart, colNumEnd, cellNum,
						"TWITTER FOLLOWERS", headerRow);
			}
			if (mainReport.getFbUrl() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				mergeRowHeader(sheet, headerCellStyle, rowNumStart, rowNumEnd, colNumStart, colNumEnd, cellNum,
						"FACEBOOK URL", headerRow);
			}
			if (mainReport.getTwitterUrl() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				mergeRowHeader(sheet, headerCellStyle, rowNumStart, rowNumEnd, colNumStart, colNumEnd, cellNum,
						"TWITTER URL", headerRow);
			}
			if (mainReport.getProviderReport() != null && !mainReport.getProviderReport().isEmpty()) {
				List<ProviderReport> providerReport = mainReport.getProviderReport();
				if (providerReport != null) {
					int websiteSize = providerReport.size();
					int reportRowStart = 0, reportRowEnd = 0;
					colNumStart += 1;
					cellNum += 1;
					colNumEnd += websiteSize;
					mergeRowHeader(sheet, headerCellStyle, reportRowStart, reportRowEnd, colNumStart, colNumEnd,
							cellNum++, "TRAFFIC", headerRow);
					cellNum -= 1;
					for (ProviderReport provReport : providerReport) {
						if (provReport != null && provReport.getSiteName() != null
								&& !provReport.getSiteName().isEmpty()) {
							rowHeader(sheet, headerCellStyle, rowCount, cellNum, provReport.getSiteName().toUpperCase(),
									row);
							cellNum += 1;
						}
					}
				}
			}
			if (mainReport.getDailyUniqueVisitors() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				setExcelValue(sheet, row, headerCellStyle, "DAILY UNIQUE VISITORS", cellNum);
			}
			if (mainReport.getDailyRevenue() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				setExcelValue(sheet, row, headerCellStyle, "DAILY REVENUE", cellNum);
			}
			if (mainReport.getDailyUniquePageviews() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				setExcelValue(sheet, row, headerCellStyle, "DAILY UNIQUE PAGE VIEWS", cellNum);
			}
			if (mainReport.getMonthlyUniqueVisitors() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				setExcelValue(sheet, row, headerCellStyle, "MONTHLY UNIQUE VISITORS", cellNum);
			}
			if (mainReport.getMonthlyRevenue() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				setExcelValue(sheet, row, headerCellStyle, "MONTHLY REVENUE", cellNum);
			}
			if (mainReport.getMonthlyUniquePageviews() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				setExcelValue(sheet, row, headerCellStyle, "MONTHLY UNIQUE PAGE VIEWS", cellNum);
			}
			if (mainReport.getYearlyUniqueVisitors() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				setExcelValue(sheet, row, headerCellStyle, "YEARLY UNIQUE VISITORS", cellNum);
			}
			if (mainReport.getYearlyRevenue() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				setExcelValue(sheet, row, headerCellStyle, "YEARLY REVENUE", cellNum);
			}
			if (mainReport.getYearlyUniquePageviews() != null) {
				colNumStart += 1;
				colNumEnd += 1;
				cellNum += 1;
				setExcelValue(sheet, row, headerCellStyle, "YEARLY UNIQUE PAGE VIEWS", cellNum);
			}
			CellStyle style = setFont(wb);
			rowCount += 1;
			setReport(mainReportList, sheet, style, rowCount);
		}
	}

	private static CellStyle setFont(HSSFWorkbook wb) {
		Font font = wb.createFont();
		font.setFontHeightInPoints((short) 10);
		font.setColor(IndexedColors.BLACK.getIndex());
		CellStyle headerCellStyle = wb.createCellStyle();
		headerCellStyle.setFont(font);
		return headerCellStyle;
	}

	private static void mergeRowHeader(Sheet sheet, CellStyle headerCellStyle, int rowNumStart, int rowNumEnd,
			int colNumStart, int colNumEnd, int cellNum, String headerName, Row headerRow) {
		Cell cell = headerRow.createCell(cellNum);
		cell.setCellValue(headerName);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cell.setCellStyle(headerCellStyle);
		sheet.addMergedRegion(new CellRangeAddress(rowNumStart, rowNumEnd, colNumStart, colNumEnd));
	}

	private static void rowHeader(Sheet sheet, CellStyle headerCellStyle, int rowCount, int cellNum, String headerName,
			Row row) {
		Cell cell = row.createCell(cellNum);
		cell.setCellValue(headerName);
		headerCellStyle.setAlignment(CellStyle.ALIGN_CENTER);
		cell.setCellStyle(headerCellStyle);
	}

	private static void setReport(List<MainReport> mainReportList, Sheet sheet, CellStyle style, int rowCount) {

		for (MainReport mainReport : mainReportList) {
			Row row = sheet.createRow(rowCount);
			setReportValue(mainReport, sheet, style, row);
			rowCount += 1;
		}

	}

	private static void setReportValue(MainReport mainReport, Sheet sheet, CellStyle style, Row row) {
		int cellNum = 0;
		if (mainReport != null) {
			if (mainReport.getSiteUrl() != null) {
				setExcelValue(sheet, row, style, mainReport.getSiteUrl(), cellNum);
			}
			if (mainReport.getWebsite() != null && mainReport.getWebsite().getEmail() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getWebsite().getEmail(), cellNum);
			}
			if (mainReport.getWebsite() != null && mainReport.getWebsite().getPhone() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getWebsite().getPhone(), cellNum);
			}
			if (mainReport.getFbFollowers() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getFbFollowers(), cellNum);
			}
			if (mainReport.getTwitterFollowers() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getTwitterFollowers(), cellNum);
			}
			if (mainReport.getFbUrl() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getFbUrl(), cellNum);
			}
			if (mainReport.getTwitterUrl() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getTwitterUrl(), cellNum);
			}
			if (mainReport.getProviderReport() != null) {
				List<ProviderReport> providerReport = mainReport.getProviderReport();
				if (providerReport != null && !providerReport.isEmpty()) {
					for (ProviderReport provReport : providerReport) {
						if (provReport.getTrafficCount() != null) {
							cellNum += 1;
							setExcelValue(sheet, row, style, provReport.getTrafficCount(), cellNum);
						}
					}
				}
			}
			if (mainReport.getDailyUniqueVisitors() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getDailyUniqueVisitors(), cellNum);
			}
			if (mainReport.getDailyRevenue() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getDailyRevenue(), cellNum);
			}
			if (mainReport.getDailyUniquePageviews() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getDailyRevenue(), cellNum);
			}
			if (mainReport.getMonthlyUniqueVisitors() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getMonthlyUniqueVisitors(), cellNum);
			}
			if (mainReport.getMonthlyRevenue() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getMonthlyRevenue(), cellNum);
			}
			if (mainReport.getMonthlyUniquePageviews() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getDailyRevenue(), cellNum);
			}
			if (mainReport.getYearlyUniqueVisitors() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getYearlyUniqueVisitors(), cellNum);
			}
			if (mainReport.getYearlyRevenue() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getYearlyRevenue(), cellNum);
			}
			if (mainReport.getYearlyUniquePageviews() != null) {
				cellNum += 1;
				setExcelValue(sheet, row, style, mainReport.getYearlyUniquePageviews(), cellNum);
			}
		}
	}

	private static void setExcelValue(Sheet sheet, Row row, CellStyle style, String value, int cellNum) {
		Cell cell = row.createCell(cellNum);
		cell.setCellValue(value);
		style.setAlignment(CellStyle.ALIGN_CENTER);
		cell.setCellStyle(style);
	}
}
