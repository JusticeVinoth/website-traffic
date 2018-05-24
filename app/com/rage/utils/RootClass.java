package com.rage.utils;

import java.util.ArrayList;
import java.util.List;

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
			if (!siteName.contains("http"))
				siteName = "http://" + siteName;
			if (siteName.endsWith("/"))
				siteName = siteName.substring(0, siteName.length() - 1);
			/*String Siteprice_visit_count = "";
			String Similarweb_visit_count = "";*/
			String Siteworthtraffic_visit_count = "";
			/*String Mysitewealth_visit_count = "";
			String Statchest_visit_count = "";*/
			String FBFollowers = "";
			String TwitterFollowers = "";
			
			
			ObjectNode Siteworthtraffic_objnode=Json.newObject();

			// get Traffic visited count ----------------------------------

		/*	Siteprice_visit_count = Siteprice.getTrafficCount(siteName);
			Similarweb_visit_count = Similarweb.getTrafficCount(siteName);*/
			Siteworthtraffic_objnode = Siteworthtraffic.getTrafficCountNew(siteName);
			/*Mysitewealth_visit_count = Mysitewealth.getTrafficCount(siteName);
			Statchest_visit_count = Statchest.getTrafficCount(siteName);*/

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
			TwitterFollowers = TwitterFollowers.replaceAll("[^0-9,]", "").trim();
			if (TwitterFollowers.equals("")) {
				TwitterFollowers = "0";
			}
			mainReport.setTwitterFollowers(TwitterFollowers);

			List<ProviderReport> providerReportList = new ArrayList<>();

		/*	siteRelatedTrafficCount("http://www.siteprice.org", "siteprice", Siteprice_visit_count, providerReportList);
			siteRelatedTrafficCount("https://www.similarweb.com", "similarweb", Similarweb_visit_count,
					providerReportList);*/
			siteRelatedTrafficCount("http://www.siteworthtraffic.com", "siteworthtraffic", Siteworthtraffic_objnode,
					providerReportList);
			/*siteRelatedTrafficCount("https://mysitewealth.com", "mysitewealth", Mysitewealth_visit_count,
					providerReportList);
			siteRelatedTrafficCount("https://www.statchest.com", "statchest", Statchest_visit_count,
					providerReportList);*/

			mainReport.setProviderReport(providerReportList);
			mainReportList.add(mainReport);
		}
		System.out.println("mainReport ::: " + mainReportList);
		return mainReportList; 
	}

	private static void siteRelatedTrafficCount(String siteUrl, String siteName, ObjectNode siteResult,
			List<ProviderReport> providerReportList) {
		
		
		ProviderReport providerReport = new ProviderReport();
		providerReport.setSearchSiteUrl(siteUrl);
		providerReport.setSiteName(siteName);
		
		ObjectNode Sitetraffic_objnode=siteResult;
		
		if(Sitetraffic_objnode !=null)
		{
			if(Sitetraffic_objnode.hasNonNull("")){
				providerReport.setDailyUniqueVisitors(Sitetraffic_objnode.get("Daily_Unique_Visitors").asText());
			}
			if(Sitetraffic_objnode.hasNonNull("")){
				providerReport.setDailyRevenue(Sitetraffic_objnode.get("Daily_Revenue_(From_Ads)").asText());
			}
			if(Sitetraffic_objnode.hasNonNull("")){
				providerReport.setDailyUniquePageviews(Sitetraffic_objnode.get("Daily_Unique_Pageviews").asText());
			}
			if(Sitetraffic_objnode.hasNonNull("")){
				providerReport.setMonthlyUniqueVisitors(Sitetraffic_objnode.get("Monthly_Unique_Visitors").asText());
			}
			if(Sitetraffic_objnode.hasNonNull("")){
				providerReport.setMonthlyRevenue(Sitetraffic_objnode.get("Monthly_Revenue_(From_Ads)").asText());
			}
			if(Sitetraffic_objnode.hasNonNull("")){
				providerReport.setMonthlyUniquePageviews(Sitetraffic_objnode.get("Monthly_Unique_Pageviews").asText());
			}
			if(Sitetraffic_objnode.hasNonNull("")){
				providerReport.setYearlyUniqueVisitors(Sitetraffic_objnode.get("Yearly_Unique_Visitors").asText());
			}
			if(Sitetraffic_objnode.hasNonNull("")){
				providerReport.setYearlyRevenue(Sitetraffic_objnode.get("Yearly_Revenue_(From_Ads)").asText());
			}
			if(Sitetraffic_objnode.hasNonNull("")){
				providerReport.setYearlyUniquePageviews(Sitetraffic_objnode.get("Yearly_Unique_Pageviews").asText());
			}
		}
		
		
	//	providerReport.setTrafficCount(count);
		providerReportList.add(providerReport);
	}
}
