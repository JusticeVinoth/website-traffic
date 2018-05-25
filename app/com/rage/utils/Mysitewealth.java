package com.rage.utils;

import java.util.Iterator;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import play.libs.Json;

public class Mysitewealth {

	public static void main(String[] args) {
		
		getTrafficCountNew("facebook.com");
		
		/*String json="{\"Traffic_Stats\":[{\"Daily_Unique_Visitors\":\"16,305,929\"},{\"Daily_Page_Views\":\"146,753,361\"},{\"Income_Per_Day\":\"$ 366,883\"}]}";
		
		ObjectNode Mysitewealth_objnode=(ObjectNode) Json.parse(json);
		System.out.println(Mysitewealth_objnode);
		
		if(Mysitewealth_objnode.hasNonNull("Traffic_Stats")){
			ArrayNode rankNode=(ArrayNode) Mysitewealth_objnode.get("Traffic_Stats");
			
			if (rankNode != null) {
				for (Iterator<JsonNode> it = rankNode.iterator(); it.hasNext();) {
					JsonNode obj = it.next();
					if(obj.hasNonNull("Daily_Unique_Visitors")){
					System.out.println(obj.get("Daily_Unique_Visitors").asText());
					}
					if(obj.hasNonNull("Country_Label")){
						System.out.println(obj.get("Country_Label").asText().trim());
					}
				}
			}
		}
		*/
		
	}

	public static String getTrafficCount(String siteName) {

		siteName = siteName.replaceAll("http://", "");
		siteName = siteName.replaceAll("https://", "");
		siteName = siteName.replaceAll("www.", "");
		String link = "https://mysitewealth.com/" + siteName + ".html";
		String DailyUniqueVisitors = "";
		System.out.println("getTrafficCount link : " + link);
		WebDriver driver = new HtmlUnitDriver();

		try {
			driver.get(link);
			Document doc = Jsoup.parse(driver.getPageSource());
			Elements els = doc.select(".div-country");
			for (Element ele : els) {
				String title = "";
				title = ele.select("span").text().trim();
				if (title.contains("Daily Unique Visitors")) {
					DailyUniqueVisitors = ele.select("h2").text().trim();
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
		String link = "https://mysitewealth.com/" + siteName + ".html";
		
		System.out.println("getTrafficCount link : " + link);
		WebDriver driver = new HtmlUnitDriver();
		
		ObjectNode objnode_main=(ObjectNode)Json.newObject();
		
		ArrayNode traffic_array=objnode_main.putArray("Traffic_Stats");
		ArrayNode Search_Engines_Index_Stats_array=objnode_main.putArray("Search_Engines_Index_Stats");
		ArrayNode Website_Safety_Stats_array=objnode_main.putArray("Website_Safety_Stats");
		ArrayNode SEO_Stats_array=objnode_main.putArray("SEO_Stats");
		ArrayNode Domain_Information_array=objnode_main.putArray("Domain_Information");
		ArrayNode Domain_Owner_Information_array=objnode_main.putArray("Domain_Owner_Information");
		ArrayNode Domain_Hosting_Information_array=objnode_main.putArray("Domain_Hosting_Information");
		ArrayNode Web_Server_Information_array=objnode_main.putArray("Web_Server_Information");
		ArrayNode Social_Media_Stats_array=objnode_main.putArray("Social_Media_Stats");
		ArrayNode Website_Performance_array=objnode_main.putArray("Website_Performance");
		ArrayNode Featured_Stats_array=objnode_main.putArray("Featured_Stats");
		ArrayNode Rank_array=objnode_main.putArray("Rank");
		
		try {
			driver.get(link);
			Document doc = Jsoup.parse(driver.getPageSource());

			Elements els=doc.select(".div-country");
			
				for(Element ele:els){
				
				String title=null;
				try{
				title=ele.select("span").first().text().trim();
				}catch(Exception e){}
				
				String result=ele.text().trim();

				ObjectNode objnode_sub=Json.newObject();
				
				
				if(title !=null && result !=null){
				
				try{
				// General info ***********************************************************************
				if(result.contains("Global Rank"))
					objnode_main.put(title.replaceAll("\\s", "_"),result.replaceAll(title, "").trim());
				if(result.contains("Rank in")){
					
					String country_Rank=result.replaceAll(title, "").trim();
					String country_Label=title.replaceAll("Rank in", "");
					
					objnode_sub.put("Country_Rank",country_Rank);
					objnode_sub.put("Country_Label",country_Label.trim());
					Rank_array.add(objnode_sub);
				}
				
				// Traffic Stats ***********************************************************************
				if(result.contains("Daily Unique Visitors") || result.contains("Daily Page Views") || result.contains("Income Per Day")){
					objnode_sub.put(title.replaceAll("\\s", "_"),result.replaceAll(title, "").trim());
					traffic_array.add(objnode_sub);
				}
				
				// Search Engines Index Stats ***********************************************************************
				if(result.contains("Google Index") || result.contains("Bing Index") || result.contains("Yahoo Index")){
					objnode_sub.put(title.replaceAll("\\s", "_"),result.replaceAll(title, "").trim());
					Search_Engines_Index_Stats_array.add(objnode_sub);
				}
					
				// Website Safety Stats ***********************************************************************
				if(result.contains("Google Safe Browsing") || result.contains("Spamhaus Blocklist")){
					objnode_sub.put(title.replaceAll("\\s", "_"),result.replaceAll(title, "").trim());
					Website_Safety_Stats_array.add(objnode_sub);
				}
				
				// SEO Stats ***********************************************************************
				if(result.contains("Total Backinks") || result.contains("Robots.txt") || result.contains("Sitemap.xml")){
					objnode_sub.put(title.replaceAll("\\s", "_"),result.replaceAll(title, "").trim());
					SEO_Stats_array.add(objnode_sub);
				}
					
				}catch(Exception e1){
					e1.printStackTrace();
				}
				
				}
				}
			

			// more info *************************************************************************************
			Elements panel_els=doc.select(".panel-default");

			for(Element panel_ele:panel_els){
				
				String panel_title=panel_ele.select(".title-of-table").text().trim();
				
				// Featured Stats ***********************************************************************
				if(panel_title.equalsIgnoreCase("Featured Stats")){
					ObjectNode objnode_sub=Json.newObject();
					Elements seo_alert_els=panel_ele.select(".seo-alert");
					
					for(Element seo_ele:seo_alert_els){
						
						String title=seo_ele.select(".seo-stats-title").text().trim();
						String result=seo_ele.text().trim();
						
						if(result.contains(title))
							objnode_sub.put(title.replaceAll("\\s", "_"), result.replaceAll(title, "").trim());
					}
					Featured_Stats_array.add(objnode_sub);
				}
				// Website Performance ***********************************************************************
				if(panel_title.equalsIgnoreCase("Website Performance")){
					ObjectNode objnode_sub=Json.newObject();
					Elements seo_alert_els=panel_ele.select(".seo-alert");
					
					for(Element seo_ele:seo_alert_els){
						
						String title=seo_ele.select(".seo-stats-title").text().trim();
						String result=seo_ele.text().trim();
						
						if(result.contains(title))
							objnode_sub.put(title.replaceAll("\\s", "_"), result.replaceAll(title, "").trim());
						
					}
					Website_Performance_array.add(objnode_sub);
									
					}
				// Web Server Information ***********************************************************************
				if(panel_title.equalsIgnoreCase("Web Server Information")){
					ObjectNode objnode_sub=Json.newObject();
					Elements web_els=panel_ele.select("tbody").select("tr");
					
					// tr
					for(Element web_el:web_els){
						
						Elements web_td_els=web_el.select("td");
						
						String Name_Server=null;
						String IP_Address=null;
						String Country=null;
						// td
						
						int count=0;
						for(Element web_td_el:web_td_els){
							count++;
							if(count==1)
								Name_Server=web_td_el.text();
							if(count==2)
							IP_Address=web_td_el.text();
							if(count==3)
							Country=web_td_el.text();
						}
						
						if(Name_Server !=null && IP_Address !=null && Country !=null){
							objnode_sub.put("Name_Server", Name_Server);
							objnode_sub.put("IP_Address", IP_Address);
							objnode_sub.put("Country", Country);
						}
					}
					Web_Server_Information_array.add(objnode_sub);
					
				}
				// Social Media Stats ***********************************************************************
				if(panel_title.equalsIgnoreCase("Social Media Stats")){
					ObjectNode objnode_sub=Json.newObject();
					Elements social_els=panel_ele.select(".div-country");
					for(Element social_ele:social_els){
						
						String title=null;
						String result=null;
						
						try{
						title=social_ele.select("img").attr("src").replaceAll("https://mysitewealth.com/static/images/", "").replaceAll(".png", "");
						result=social_ele.text().trim();
						}catch(Exception e){}
						
						if(title !=null && result !=null)
						objnode_sub.put(title.replaceAll("\\s", "_"), result.replaceAll(title, "").trim());
						
					}
					Social_Media_Stats_array.add(objnode_sub);
				}
			}
			
			// Domain_Owner_Information, Domain Information && Domain Hosting Information ***************************
			try{
				
				Elements DomainHosting_els=doc.select(".table-responsive");
				
				
				for(Element DomainHosting_el:DomainHosting_els){
					
					String title=null;
					try{
						title=DomainHosting_el.select("thead").select("th").text().trim();
					}catch (Exception e) {}
				
					if(title !=null){
						// Domain_Owner_Information *****************************************************************
						if(title.equalsIgnoreCase("Domain Owner Information")){
							ObjectNode objnode_sub=Json.newObject();
							Elements td_els=DomainHosting_el.select("tbody").select("tr").select("td");
							
								for(Element td_el:td_els){
								
								String td_title=td_el.select("b").text().trim();
								String td_result=td_el.text().trim();
								
								objnode_sub.put(td_title.replaceAll(":", "").replaceAll("\\s", "_"), td_result.replaceAll(td_title, "").trim());
							}
							Domain_Owner_Information_array.add(objnode_sub);
							
						}
						
						// Domain Information ***********************************************************************
						if(title.equalsIgnoreCase("Domain Information")){
							ObjectNode objnode_sub=Json.newObject();
							Elements tr_els=DomainHosting_el.select("tbody").select("tr");
							
							for(Element tr_el:tr_els){
								
								String tr_title=null;
								String tr_value=null;
								String th_title=null;
								try{
									th_title=tr_el.select("th").text().trim();
								}catch(Exception e){}
								try{
									tr_title=tr_el.select("td").first().text().trim();
									tr_value=tr_el.select("td").last().text().trim();
								}catch(Exception e){}
								
								if(th_title !=null && !th_title.isEmpty())
									tr_title=th_title;
								
								if(tr_title !=null && tr_value !=null && !tr_title.isEmpty())
									objnode_sub.put(tr_title.replaceAll("\\s", "").replaceAll(":", "").trim(),tr_value);
							}
							Domain_Information_array.add(objnode_sub);
							
						}
						// Domain Hosting Information ***********************************************************************
						if(title.equalsIgnoreCase("Domain Hosting Information")){
							Elements td_els=DomainHosting_el.select("tbody").select("tr").select("td");
							ObjectNode objnode_sub=Json.newObject();
							
							for(Element td_el:td_els){
								
								String td_title=td_el.select("b").text().trim();
								String td_result=td_el.text().trim();
								
								objnode_sub.put(td_title.replaceAll(":", "").replaceAll("\\s", "_"), td_result.replaceAll(td_title, "").trim());
							}
							Domain_Hosting_Information_array.add(objnode_sub);
						}
					}
				}
			}catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println(objnode_main);
		
		
		
		return objnode_main;
	}
}
