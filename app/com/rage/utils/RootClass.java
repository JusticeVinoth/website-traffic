package com.rage.utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.rage.models.report.main.MainReport;
import com.rage.models.report.provider.ProviderReport;
import com.rage.utils.helpers.SocialBean;
import com.rage.utils.helpers.StaticClass;

import play.libs.Json;

public class RootClass {

	public static void main(String[] args) {

		List<String> site_list = new ArrayList<>();
		site_list.add("www.whatarage.com");
		// site_list.add("https://www.flipkart.com");
		getData(site_list);
	}

	public static List<MainReport> getData(List<String> site_list) {
		
		List<MainReport> mainReportList = new ArrayList<>();

		for (String siteName : site_list) {
			MainReport mainReport = new MainReport();
			siteName = siteName.trim();
			if (!siteName.startsWith("http"))
				siteName = "http://" + siteName;
			if (siteName.endsWith("/"))
				siteName = siteName.substring(0, siteName.length() - 1);
			String Siteprice_visit_count = "";
			String Similarweb_visit_count = "";
			String Siteworthtraffic_visit_count = "";
			String Mysitewealth_visit_count = "";
			String Statchest_visit_count = "";
			String FBFollowers = "";
			String TwitterFollowers = "";
			
			
			ObjectNode Siteworthtraffic_objnode=Json.newObject();
			ObjectNode Mysitewealth_objnode=Json.newObject();

			// get Traffic visited count ----------------------------------

			Siteprice_visit_count = Siteprice.getTrafficCount(siteName);
			Similarweb_visit_count = Similarweb.getTrafficCount(siteName);
			Siteworthtraffic_objnode = Siteworthtraffic.getTrafficCountNew(siteName);
			Mysitewealth_objnode = Mysitewealth.getTrafficCountNew(siteName);
			Statchest_visit_count = Statchest.getTrafficCount(siteName);

			// create bean object -------------------------------------------
			List<SocialBean> bean_list = new ArrayList<>();
			bean_list = FBLink.getFaceBook_TwitterName(siteName);
			String fbName = "";
			String twitterName = "";
			for (SocialBean socialLink : bean_list) {
				fbName = socialLink.getFacebookNameBean();
				twitterName = socialLink.getTwitterNameBean();
			}

			// get FB name ---------------------------------------------------
			if (fbName.equals(""))
				fbName = GoogleSocialMedia.Google(siteName, StaticClass.FB);

			FBFollowers = FBData.getFBFollowersCount(fbName);

			// get Twitter name ----------------------------------------------
			if (twitterName.equals(""))
				twitterName = GoogleSocialMedia.Google(siteName, StaticClass.Twitter);

			TwitterFollowers = TwitterData.getTwitterFollwersCount(twitterName);

			// site url
			mainReport.setSiteUrl(siteName);

			// has fb page
			if (fbName.equals("")) {
				mainReport.setHasFbPage("false");
			} else {
				mainReport.setHasFbPage("true");
				mainReport.setFbUrl("https://www.facebook.com/" + fbName);
			}

			// has twitter page
			if (twitterName.equals("")) {
				mainReport.setHasTwitterPage("false");
			} else {
				mainReport.setHasTwitterPage("true");
				mainReport.setTwitterUrl("https://twitter.com/" + twitterName);
			}

			// fb followers
			FBFollowers = FBFollowers.replaceAll("[^0-9,]", "").trim();
			if (FBFollowers.equals("")) {
				FBFollowers = "0";
			}
			mainReport.setFbFollowers(FBFollowers);

			// twitter followers
			TwitterFollowers = TwitterFollowers.trim();
			if (TwitterFollowers.equals("")) {
				TwitterFollowers = "0";
			}
			mainReport.setTwitterFollowers(TwitterFollowers);

			/*sathish changed */
			
			// siteworthtraffic
			if(Siteworthtraffic_objnode !=null)
			{
				if(Siteworthtraffic_objnode.hasNonNull("Daily_Unique_Visitors")){
					mainReport.setDailyUniqueVisitors(Siteworthtraffic_objnode.get("Daily_Unique_Visitors").asText());
					Siteworthtraffic_visit_count=Siteworthtraffic_objnode.get("Daily_Unique_Visitors").asText();
				}
				if(Siteworthtraffic_objnode.hasNonNull("Daily_Revenue_(From_Ads)")){
					mainReport.setDailyRevenue(Siteworthtraffic_objnode.get("Daily_Revenue_(From_Ads)").asText());
				}
				if(Siteworthtraffic_objnode.hasNonNull("Daily_Unique_Pageviews")){
					mainReport.setDailyUniquePageviews(Siteworthtraffic_objnode.get("Daily_Unique_Pageviews").asText());
				}
				if(Siteworthtraffic_objnode.hasNonNull("Monthly_Unique_Visitors")){
					mainReport.setMonthlyUniqueVisitors(Siteworthtraffic_objnode.get("Monthly_Unique_Visitors").asText());
				}
				if(Siteworthtraffic_objnode.hasNonNull("Monthly_Revenue_(From_Ads)")){
					mainReport.setMonthlyRevenue(Siteworthtraffic_objnode.get("Monthly_Revenue_(From_Ads)").asText());
				}
				if(Siteworthtraffic_objnode.hasNonNull("Monthly_Unique_Pageviews")){
					mainReport.setMonthlyUniquePageviews(Siteworthtraffic_objnode.get("Monthly_Unique_Pageviews").asText());
				}
				if(Siteworthtraffic_objnode.hasNonNull("Yearly_Unique_Visitors")){
					mainReport.setYearlyUniqueVisitors(Siteworthtraffic_objnode.get("Yearly_Unique_Visitors").asText());
				}
				if(Siteworthtraffic_objnode.hasNonNull("Yearly_Revenue_(From_Ads)")){
					mainReport.setYearlyRevenue(Siteworthtraffic_objnode.get("Yearly_Revenue_(From_Ads)").asText());
				}
				if(Siteworthtraffic_objnode.hasNonNull("Yearly_Unique_Pageviews")){
					mainReport.setYearlyUniquePageviews(Siteworthtraffic_objnode.get("Yearly_Unique_Pageviews").asText());
				}
			}
			
			// Mysitewealth
			if(Mysitewealth_objnode != null){
				
				if(Mysitewealth_objnode.hasNonNull("Global_Rank")){
					mainReport.setGlobalRank(Mysitewealth_objnode.get("Global_Rank").asText());
				}
				
			if(Mysitewealth_objnode.hasNonNull("Rank")){
				ArrayNode arrayNode=(ArrayNode) Mysitewealth_objnode.get("Rank");
				
				if (arrayNode != null) {
					for (Iterator<JsonNode> it = arrayNode.iterator(); it.hasNext();) {
						JsonNode obj = it.next();
						if(obj.hasNonNull("Country_Rank")){
							mainReport.setCountryRank(obj.get("Country_Rank").asText());
						}
						if(obj.hasNonNull("Country_Label")){
							mainReport.setCountryLabel(obj.get("Country_Label").asText().trim());
						}
					}
				}
			}
			
			if(Mysitewealth_objnode.hasNonNull("Traffic_Stats")){
				ArrayNode arrayNode=(ArrayNode) Mysitewealth_objnode.get("Traffic_Stats");
				
				if (arrayNode != null) {
					for (Iterator<JsonNode> it = arrayNode.iterator(); it.hasNext();) {
						JsonNode obj = it.next();
						if(obj.hasNonNull("Daily_Unique_Visitors")){
							Mysitewealth_visit_count=obj.get("Daily_Unique_Visitors").asText();
						}
						
					}
				}
			}
			
			if(Mysitewealth_objnode.hasNonNull("Website_Performance")){
				ArrayNode arrayNode=(ArrayNode) Mysitewealth_objnode.get("Website_Performance");
				
				if (arrayNode != null) {
					for (Iterator<JsonNode> it = arrayNode.iterator(); it.hasNext();) {
						JsonNode obj = it.next();
						if(obj.hasNonNull("Website_Load_Time")){
							mainReport.setWebsiteLoadTime(obj.get("Website_Load_Time").asText());
						}
						if(obj.hasNonNull("Website_Page_Size")){
							mainReport.setWebsitePageSize(obj.get("Website_Page_Size").asText().trim());
						}
					}
				}
			}
			
			if(Mysitewealth_objnode.hasNonNull("Domain_Information")){
				ArrayNode arrayNode=(ArrayNode) Mysitewealth_objnode.get("Domain_Information");
				
				if (arrayNode != null) {
					for (Iterator<JsonNode> it = arrayNode.iterator(); it.hasNext();) {
						JsonNode obj = it.next();
						if(obj.hasNonNull("DomainRegistrar")){
							mainReport.setDomainRegistrar(obj.get("DomainRegistrar").asText());
						}
						if(obj.hasNonNull("RegistrationDate")){
							mainReport.setDomainRegistrationDate(obj.get("RegistrationDate").asText().trim());
						}
						if(obj.hasNonNull("LastUpdated")){
							mainReport.setDomainLastUpdated(obj.get("LastUpdated").asText().trim());
						}
						if(obj.hasNonNull("ExpirationDate")){
							mainReport.setDomainExpirationDate(obj.get("ExpirationDate").asText().trim());
						}
					}
				}
			}
			
			if(Mysitewealth_objnode.hasNonNull("Domain_Owner_Information")){
				ArrayNode arrayNode=(ArrayNode) Mysitewealth_objnode.get("Domain_Owner_Information");
				
				if (arrayNode != null) {
					for (Iterator<JsonNode> it = arrayNode.iterator(); it.hasNext();) {
						JsonNode obj = it.next();
						if(obj.hasNonNull("Name")){
							mainReport.setDomainName(obj.get("Name").asText());
						}
						if(obj.hasNonNull("Organization")){
							mainReport.setDomainOrganization(obj.get("Organization").asText().trim());
						}
						if(obj.hasNonNull("Street")){
							mainReport.setDomainOwnerStreet(obj.get("Street").asText().trim());
						}
						if(obj.hasNonNull("City")){
							mainReport.setDomainOwnerCity(obj.get("City").asText().trim());
						}
						if(obj.hasNonNull("State")){
							mainReport.setDomainOwnerState(obj.get("State").asText().trim());
						}
						if(obj.hasNonNull("Postal_Code")){
							mainReport.setDomainOwnerPostalCode(obj.get("Postal_Code").asText().trim());
						}
						if(obj.hasNonNull("Country")){
							mainReport.setDomainOwnerCountry(obj.get("Country").asText().trim());
						}
						if(obj.hasNonNull("Fax")){
							mainReport.setDomainFax(obj.get("Fax").asText().trim());
						}
						if(obj.hasNonNull("Phone")){
							mainReport.setDomainPhone(obj.get("Phone").asText().trim());
						}
						if(obj.hasNonNull("Email")){
							mainReport.setDomainOwnerEmail(obj.get("Email").asText().trim());
						}
					}
				}
			}
			
			if(Mysitewealth_objnode.hasNonNull("Domain_Hosting_Information")){
				ArrayNode arrayNode=(ArrayNode) Mysitewealth_objnode.get("Domain_Hosting_Information");
				
				if (arrayNode != null) {
					for (Iterator<JsonNode> it = arrayNode.iterator(); it.hasNext();) {
						JsonNode obj = it.next();
						if(obj.hasNonNull("Sever_IP_Address")){
							mainReport.setDomainSeverIpAddress(obj.get("Sever_IP_Address").asText());
						}
						if(obj.hasNonNull("Server_Location")){
							mainReport.setDomainServerLocation(obj.get("Server_Location").asText().trim());
						}
						if(obj.hasNonNull("Hosting_Provider(ISP)")){
							mainReport.setDomainHostingProvider(obj.get("Hosting_Provider(ISP)").asText().trim());
						}
						if(obj.hasNonNull("Server_HTTP_Code")){
							mainReport.setDomainServerHttpCode(obj.get("Server_HTTP_Code").asText().trim());
						}
					}
				}
			}
			
			if(Mysitewealth_objnode.hasNonNull("Web_Server_Information")){
				ArrayNode arrayNode=(ArrayNode) Mysitewealth_objnode.get("Web_Server_Information");
				
				if (arrayNode != null) {
					for (Iterator<JsonNode> it = arrayNode.iterator(); it.hasNext();) {
						JsonNode obj = it.next();
						if(obj.hasNonNull("Name_Server")){
							mainReport.setWebServerName(obj.get("Name_Server").asText());
						}
						if(obj.hasNonNull("IP_Address")){
							mainReport.setWebServerIPAddress(obj.get("IP_Address").asText().trim());
						}
						if(obj.hasNonNull("Country")){
							mainReport.setWebServerCountry(obj.get("Country").asText().trim());
						}
					}
				}
			}
			
			if(Mysitewealth_objnode.hasNonNull("Featured_Stats")){
				ArrayNode arrayNode=(ArrayNode) Mysitewealth_objnode.get("Featured_Stats");
				
				if (arrayNode != null) {
					for (Iterator<JsonNode> it = arrayNode.iterator(); it.hasNext();) {
						JsonNode obj = it.next();
						if(obj.hasNonNull("Google_Pagerank")){
							mainReport.setGooglePagerank(obj.get("Google_Pagerank").asText());
						}
						if(obj.hasNonNull("Page_Speed_Score")){
							mainReport.setPageSpeedScore(obj.get("Page_Speed_Score").asText().trim());
						}
						if(obj.hasNonNull("Mobile_Speed_Score")){
							mainReport.setMobileSpeedScore(obj.get("Mobile_Speed_Score").asText().trim());
						}
						if(obj.hasNonNull("Bounce_Rate")){
							mainReport.setBounceRate(obj.get("Bounce_Rate").asText().trim());
						}
						if(obj.hasNonNull("Moz_Rank")){
							mainReport.setMozRank(obj.get("Moz_Rank").asText().trim());
						}
						if(obj.hasNonNull("Domain_Authority")){
							mainReport.setDomainAuthority(obj.get("Domain_Authority").asText().trim());
						}
						if(obj.hasNonNull("Page_Authority")){
							mainReport.setPageAuthority(obj.get("Page_Authority").asText().trim());
						}
					}
				}
			}
			
			if(Mysitewealth_objnode.hasNonNull("Website_Safety_Stats")){
				ArrayNode arrayNode=(ArrayNode) Mysitewealth_objnode.get("Website_Safety_Stats");
				
				if (arrayNode != null) {
					for (Iterator<JsonNode> it = arrayNode.iterator(); it.hasNext();) {
						JsonNode obj = it.next();
						if(obj.hasNonNull("Google_Safe_Browsing")){
							mainReport.setGoogleSafeBrowsing(obj.get("Google_Safe_Browsing").asText());
						}
						if(obj.hasNonNull("Spamhaus_Blocklist")){
							mainReport.setSpamhausBlocklist(obj.get("Spamhaus_Blocklist").asText().trim());
						}
					}
				}
			}
			if(Mysitewealth_objnode.hasNonNull("SEO_Stats")){
				ArrayNode arrayNode=(ArrayNode) Mysitewealth_objnode.get("SEO_Stats");
				
				if (arrayNode != null) {
					for (Iterator<JsonNode> it = arrayNode.iterator(); it.hasNext();) {
						JsonNode obj = it.next();
						if(obj.hasNonNull("Total_Backinks")){
							mainReport.setTotalBackinks(obj.get("Total_Backinks").asText());
						}
						if(obj.hasNonNull("Robots.txt")){
							mainReport.setRobotsTxt(obj.get("Robots.txt").asText().trim());
						}
						if(obj.hasNonNull("Sitemap.xml")){
							mainReport.setSitemapXml(obj.get("Sitemap.xml").asText().trim());
						}
					}
				}
			}
			
			}// if  Mysitewealth_objnode end
			
			List<ProviderReport> providerReportList = new ArrayList<>();

			siteRelatedTrafficCount("http://www.siteprice.org", "siteprice", Siteprice_visit_count, providerReportList);
			siteRelatedTrafficCount("https://www.similarweb.com", "similarweb", Similarweb_visit_count,
					providerReportList);
			siteRelatedTrafficCount("http://www.siteworthtraffic.com", "siteworthtraffic", Siteworthtraffic_visit_count,
					providerReportList);
			siteRelatedTrafficCount("https://mysitewealth.com", "mysitewealth", Mysitewealth_visit_count,
					providerReportList);
			siteRelatedTrafficCount("https://www.statchest.com", "statchest", Statchest_visit_count,
					providerReportList);

			mainReport.setProviderReport(providerReportList);
			
			
			
			
			mainReportList.add(mainReport);
		}
		System.out.println("mainReport ::: " + mainReportList);
		return mainReportList; 
	}

	private static void siteRelatedTrafficCount(String siteUrl, String siteName, String count,
			List<ProviderReport> providerReportList) {
		
		
		ProviderReport providerReport = new ProviderReport();
		providerReport.setSearchSiteUrl(siteUrl);
		providerReport.setSiteName(siteName);
		providerReport.setTrafficCount(count);
		providerReportList.add(providerReport);
	}
}
