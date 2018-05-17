package com.rage.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class TwitterData {

	public static String getTwitterFollwersCount(String siteName) {
		String link = "https://twitter.com/" + siteName + "?lang=en";
		String Followers = "";
		// System.out.println("getTwitterFollwersCount : "+link);
		WebDriver driver = new HtmlUnitDriver();

		try {
			driver.get(link);
			Document doc = Jsoup.parse(driver.getPageSource());
			Elements els = doc.select(".ProfileNav-stat");
			for (Element ele : els) {
				String Followers_title = ele.select(".ProfileNav-label").text().trim();
				if (Followers_title.equalsIgnoreCase("Followers")) {
					Followers = ele.select(".ProfileNav-value").text();
					break;
				}
			}
		} catch (Exception e) {
		}
		// System.out.println("Followers : " + Followers);
		return Followers;
	}
}
