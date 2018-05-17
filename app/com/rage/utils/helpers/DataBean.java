package com.rage.utils.helpers;

public class DataBean {

	String site_URL, has_FB_Page, has_Twitter_Page, fb_Followers, twitter_followers, fb_URL, twitter_URL;
	String search_Site_URL, traffic_Count, site_Name;

	public String getSite_URL() {
		return site_URL;
	}

	public void setSite_URL(String site_URL) {
		this.site_URL = site_URL;
	}

	public String getHas_FB_Page() {
		return has_FB_Page;
	}

	public void setHas_FB_Page(String has_FB_Page) {
		this.has_FB_Page = has_FB_Page;
	}

	public String getHas_Twitter_Page() {
		return has_Twitter_Page;
	}

	public void setHas_Twitter_Page(String has_Twitter_Page) {
		this.has_Twitter_Page = has_Twitter_Page;
	}

	public String getFb_Followers() {
		return fb_Followers;
	}

	public void setFb_Followers(String fb_Followers) {
		this.fb_Followers = fb_Followers;
	}

	public String getTwitter_followers() {
		return twitter_followers;
	}

	public void setTwitter_followers(String twitter_followers) {
		this.twitter_followers = twitter_followers;
	}

	public String getFb_URL() {
		return fb_URL;
	}

	public void setFb_URL(String fb_URL) {
		this.fb_URL = fb_URL;
	}

	public String getTwitter_URL() {
		return twitter_URL;
	}

	public void setTwitter_URL(String twitter_URL) {
		this.twitter_URL = twitter_URL;
	}

	public String getSearch_Site_URL() {
		return search_Site_URL;
	}

	public void setSearch_Site_URL(String search_Site_URL) {
		this.search_Site_URL = search_Site_URL;
	}

	public String getTraffic_Count() {
		return traffic_Count;
	}

	public void setTraffic_Count(String traffic_Count) {
		this.traffic_Count = traffic_Count;
	}

	public String getSite_Name() {
		return site_Name;
	}

	public void setSite_Name(String site_Name) {
		this.site_Name = site_Name;
	}

	@Override
	public String toString() {
		return "{\"site_url\":" + "\"" + site_URL + "\"," + "\"has_FB_Page\":" + "\"" + has_FB_Page + "\","
				+ "\"has_Twitter_Page\":" + "\"" + has_Twitter_Page + "\"," + "\"fb_Followers\":" + "\"" + fb_Followers
				+ "\"," + "\"twitter_followers\":" + "\"" + twitter_followers + "\"," + "\"fb_URL\":" + "\"" + fb_URL
				+ "\"," + "\"twitter_URL\":" + "\"" + twitter_URL + "\",";
	}

	public String arrayString() {

		return "{\"search_Site_URL\":" + "\"" + search_Site_URL + "\"," + "\"site_Name\":" + "\"" + site_Name + "\","
				+ "\"traffic_Count\":" + "\"" + traffic_Count + "\"}";

	}

}
