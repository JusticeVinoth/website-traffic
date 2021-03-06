/**
 * 
 */
package com.rage.models.website.repository.impl;

import java.util.List;

import com.rage.models.website.Website;
import com.rage.models.website.repository.WebsiteRepository;

import io.ebean.Ebean;

/**
 * @author neethithevan.r
 *
 */
public class WebsiteRepositoryImpl implements WebsiteRepository {

	@Override
	public Website getWebsiteByUrl(String websiteUrl) {
		return Ebean.find(Website.class).where().eq("url", websiteUrl).findOne();
	}

	@Override
	public List<Website> addWebsiteUrl(List<Website> websiteList) {
		if (!websiteList.isEmpty()) {
			websiteList.forEach(website -> {
				if (!isWebsiteUrlPresent(website.getUrl()))
					website.save();
			});
			return websiteList;
		}
		return null;
	}

	@Override
	public List<Website> getWebsiteList() {
		return Ebean.find(Website.class).findList();
	}

	private boolean isWebsiteUrlPresent(String Url) {
		Website website = getWebsiteByUrl(Url);
		if (website != null)
			return true;
		return false;
	}

	@Override
	public Website getWebsiteById(String id) {
		if (id != null) {
			Website website = Ebean.find(Website.class).where().eq("id", id).findOne();
			if (website != null) {
				return website;
			}
		}
		return null;
	}

	@Override
	public boolean updateWebsite(String id, Website latestWebsite) {
		if (id != null) {
			Website oldWebsite = getWebsiteById(id);
			if (oldWebsite != null) {
				oldWebsite.setPhone(latestWebsite.getPhone());
				oldWebsite.setEmail(latestWebsite.getEmail());
				oldWebsite.update();
				return true;
			}
			return false;
		}
		return false;
	}
}
