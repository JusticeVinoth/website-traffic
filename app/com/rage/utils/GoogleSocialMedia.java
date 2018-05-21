package com.rage.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.rage.utils.helpers.StaticClass;

public class GoogleSocialMedia {

	public static void main(String[] args) {
		Google("whatarage", "fb");
		// Google("whatarage","twitter");
	}

	public static String Google(String siteName, String socialMediaName) {

		siteName = siteName.replaceAll("https://", "");
		siteName = siteName.replaceAll("http://", "");
		siteName = siteName.replaceAll("www.", "");
		siteName = siteName.replaceAll(".com", "");

		String link = "";
		if (socialMediaName.equalsIgnoreCase(StaticClass.FB))
			link = "https://www.google.co.in/search?q=" + siteName.trim() + "+fb";

		if (socialMediaName.equalsIgnoreCase(StaticClass.Twitter))
			link = "https://www.google.co.in/search?q=" + siteName.trim() + "+twitter";

		System.out.println(link);
		String Media_Link = "";

		if (!link.equals("")) {
			WebDriver driver = new HtmlUnitDriver();
			try {
				driver.get(link);
				Document doc = Jsoup.parse(driver.getPageSource());
				Media_Link = doc.select(".g").first().select(".s").select("cite").text().trim();
			} catch (Exception e) {
			}

			if (!Media_Link.equals("")) {
				String sp[] = Media_Link.split("/");
				Media_Link = sp[sp.length - 1];
			}
		}
		Media_Link=Media_Link.replaceAll("\\s++", "");
		System.out.println("Social Media link : " + Media_Link);
		return Media_Link;
	}
}
