package com.rage.utils;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.rage.utils.helpers.SocialBean;

public class FBLink {

	public static void main(String[] args) {
		getFaceBook_TwitterName("http://www.whatarage.com");
		// getFaceBookDriver();
		// CleanFBLink();
	}

	public static List<SocialBean> getFaceBook_TwitterName(String siteName) {
		WebDriver driver = new HtmlUnitDriver();
		String FBName = "";
		String TwitterName = "";
		System.out.println("getFaceBook_TwitterName : " + siteName);
		try {
			driver.get(siteName);
			Document doc = Jsoup.parse(driver.getPageSource());
			Elements els = doc.select("a");
			for (Element ele : els) {
				if (ele.attr("href").contains("www.facebook.com")) {
					FBName = ele.attr("href").trim();
				}

				if (ele.attr("href").contains("www.twitter.com"))
					TwitterName = ele.attr("href").trim();
			}
		} catch (Exception e) {
		}

		// Clean FB Link
		if (!FBName.equals("")) {
			String sp[] = FBName.split("/");
			FBName = sp[sp.length - 1];
		}

		// Clean Twitter Link
		if (!TwitterName.equals("")) {
			String sp[] = TwitterName.split("/");
			TwitterName = sp[sp.length - 1];
		}

		List<SocialBean> bean_list = new ArrayList<>();
		SocialBean bean = new SocialBean();
		bean.setFacebookNameBean(FBName);
		bean.setTwitterNameBean(TwitterName);
		bean_list.add(bean);
		System.out.println("FBName : " + FBName);
		System.out.println("TwitterName : " + TwitterName);
		return bean_list;
	}

	public static void getFaceBookDriver() {
		WebDriver driver = new HtmlUnitDriver();
		String link = "https://www.google.co.in/search?q=sky+sathish";
		link = "http://www.whatarage.com/";
		// link="https://www.flipkart.com/";
		// /ol?link=http://www.facebook.com/flipkart
		driver.get(link);
		String FBLink = getFaceBookLink(driver);
		System.out.println("FBLink : " + FBLink);
	}

	public static String getFaceBookLink(WebDriver driver) {

		System.out.println(driver.getTitle());

		Document doc = Jsoup.parse(driver.getPageSource());

		String FBLink = "";

		Elements els = doc.select("a");

		for (Element ele : els) {

			if (ele.attr("href").contains("www.facebook.com"))
				FBLink = ele.attr("href").trim();
		}

		// Clean FB Link

		if (!FBLink.equals("")) {

			String sp[] = FBLink.split("/");

			FBLink = sp[sp.length - 1];

		}

		return FBLink;
	}

	public static void CleanFBLink() {

		String s = "ol?link/http://www.facebook.com/flipkart";

		s = " https://www.facebook.com/?ref=tn_tnmn#!/ragecommunications";

		if (!s.trim().equals("")) {
			String sp[] = s.split("/");

			String ss = sp[sp.length - 1];

			System.out.println(ss);

		}
	}
}
