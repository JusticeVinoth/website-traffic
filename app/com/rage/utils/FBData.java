package com.rage.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class FBData {

	public static void main(String[] args) {
		getFBFollowersCount("flipkart");
	}

	public static String getFBFollowersCount(String siteName) {
		String link = "https://www.facebook.com/" + siteName;
		String Followers = "";
		System.out.println("getFBFollowersCount link :" + link);
		WebDriver driver = new HtmlUnitDriver();
		try {
			driver.get(link);
			Document doc = Jsoup.parse(driver.getPageSource());
			Elements els = doc.select("._4bl9");
			for (Element ele : els) {
				Followers = ele.text().trim();
				if (Followers.toLowerCase().contains("follow ")) {
					System.out.println("Followers : " + Followers);
					break;
				}
			}
		} catch (Exception e) {
		}
		return Followers;
	}
}
