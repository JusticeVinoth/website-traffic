package com.rage.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Siteworthtraffic {

	public static void main(String[] args) {
		getTrafficCount("flipkart.com");
	}

	public static String getTrafficCount(String siteName) {
		siteName = siteName.replaceAll("http://", "");
		siteName = siteName.replaceAll("https://", "");
		siteName = siteName.replaceAll("www.", "");
		String link = "http://www.siteworthtraffic.com/report/" + siteName;
		String DailyUniqueVisitors = "";
		System.out.println("getTrafficCount link : " + link);
		WebDriver driver = new HtmlUnitDriver();
		try {
			driver.get(link);
			Document doc = Jsoup.parse(driver.getPageSource());
			Elements els = doc.select("tbody").select("tr");
			for (Element ele : els) {
				String title = "";
				title = ele.select("td").text().trim();
				if (title.contains("Unique Visitors")) {
					DailyUniqueVisitors = title.replaceAll("Unique Visitors", "").trim();
					break;
				}
			}
		} catch (Exception e) {
		}
		return DailyUniqueVisitors;
	}
}
