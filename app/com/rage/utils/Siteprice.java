package com.rage.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Siteprice {

	public static String getTrafficCount(String siteName) {

		siteName = siteName.replaceAll("http://", "");
		siteName = siteName.replaceAll("https://", "");
		siteName = siteName.replaceAll("www.", "");
		String link = "http://www.siteprice.org/website-worth/" + siteName;
		String DailyUniqueVisitors = "";
		String DailyPageviews = "";
		System.out.println("getTrafficCount link : " + link);
		WebDriver driver = new HtmlUnitDriver();
		try {
			driver.get(link);
			Document doc = Jsoup.parse(driver.getPageSource());
			DailyUniqueVisitors = doc.select("#lblDailyUniqueVisitors").text();
			DailyPageviews = doc.select("#lblDailyPageviews").text();
			System.out.println("DailyUniqueVisitors count : " + DailyUniqueVisitors);
			// System.out.println("DailyPageviews count : "+DailyPageviews);
		} catch (Exception e) {
		}
		return DailyUniqueVisitors;
	}

}
