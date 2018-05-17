package com.rage.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Statchest {

	public static void main(String[] args) {
		String site = "http://www.flipkart.com";
		// System.out.println(getTrafficCount(site));
	}

	public static String getTrafficCount(String siteName) {

		siteName = siteName.replaceAll("http://", "");
		siteName = siteName.replaceAll("https://", "");
		siteName = siteName.replaceAll("www.", "");
		String link = "https://www.statchest.com/" + siteName + ".html";
		String DailyUniqueVisitors = "";
		// System.out.println(link);
		WebDriver driver = new HtmlUnitDriver();
		try {
			driver.get(link);
			Document doc = Jsoup.parse(driver.getPageSource());
			Elements els = doc.select(".table-hover").select("tr");
			for (Element ele : els) {
				String title = ele.select("td").text().trim();
				if (title.contains("Daily Unique Visitors")) {
					DailyUniqueVisitors = title.replaceAll("Daily Unique Visitors", "").trim();
					break;
				}
			}
		} catch (Exception e) {
		}
		return DailyUniqueVisitors;
	}
}
