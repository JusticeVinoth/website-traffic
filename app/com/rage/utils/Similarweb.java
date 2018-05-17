package com.rage.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class Similarweb {

	public static void main(String[] args) {
		getTrafficCount("");
	}

	public static String getTrafficCount(String siteName) {
		siteName = siteName.replaceAll("https://", "");
		siteName = siteName.replaceAll("http://", "");
		siteName = siteName.replaceAll("www.", "");
		String link = "https://www.similarweb.com/website/" + siteName + "#overview";
		System.out.println("getTrafficCount link :" + link);
		String Total_Visits = "";
		String Avg_Visit_Duration = "";
		String Pages_per_Visit = "";
		String Bounce_Rate = "";

		WebDriver driver = new HtmlUnitDriver();
		try {
			driver.get(link);
			Document doc = Jsoup.parse(driver.getPageSource());
			Elements els = doc.select(".engagementInfo-line").select(".engagementInfo-content");
			// System.out.println(els.html());
			for (Element ele : els) {
				String title = ele.select(".engagementInfo-param").text();
				String value = ele.select(".engagementInfo-valueNumber").text();
				// System.out.println("title : "+title);
				// System.out.println("value : "+value);
				if (title.trim().equals("Total Visits"))
					Total_Visits = value;

				if (title.trim().equals("Avg. Visit Duration"))
					Avg_Visit_Duration = value;

				if (title.trim().equals("Pages per Visit"))
					Pages_per_Visit = value;

				if (title.trim().equals("Bounce Rate"))
					Bounce_Rate = value;
			}
			System.out.println("Total_Visits : " + Total_Visits);
			// System.out.println("Avg_Visit_Duration : "+Avg_Visit_Duration);
			// System.out.println("Pages_per_Visit : "+Pages_per_Visit);
			// System.out.println("Bounce_Rate : "+Bounce_Rate);
		} catch (Exception e) {
		}
		return Total_Visits;
	}
}
