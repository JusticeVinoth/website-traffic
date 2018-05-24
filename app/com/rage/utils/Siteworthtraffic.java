package com.rage.utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;

public class Siteworthtraffic {

	public static void main(String[] args) {
		getTrafficCountNew("flipkart.com");
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
	
	public static ObjectNode getTrafficCountNew(String siteName) {
		siteName = siteName.replaceAll("http://", "");
		siteName = siteName.replaceAll("https://", "");
		siteName = siteName.replaceAll("www.", "");
		String link = "http://www.siteworthtraffic.com/report/" + siteName;
		
		ObjectNode main_objnode=Json.newObject();
		
		System.out.println("getTrafficCount link : " + link);
		WebDriver driver = new HtmlUnitDriver();
		try {
			driver.get(link);
			Document doc = Jsoup.parse(driver.getPageSource());

			Elements table_els=doc.select(".styled");
			int count=0;
			for(Element table_el:table_els){
				count++;
				String addFrontValue="";
				if(count==1)addFrontValue="Daily_";
				if(count==2)addFrontValue="Monthly_";
				if(count==3)addFrontValue="Yearly_";
				
				Elements tr_els=table_el.select("tr");
				for(Element tr_el:tr_els){
					int size=tr_el.select("td").size();
					if(size==2){
						String title=null;
						String value=null;
						try{
							title=tr_el.select("td").first().text();
							value=tr_el.select("td").last().text();
						}catch(Exception e){}
						if(title !=null && !title.isEmpty())
							main_objnode.put(addFrontValue+title.replaceAll("\\s", "_").trim(), value.trim());
					}
				}
				if(count==3)break;
			}
		} catch (Exception e) {
		}
		
		System.out.println(main_objnode);
		
		return main_objnode;
	}
}
