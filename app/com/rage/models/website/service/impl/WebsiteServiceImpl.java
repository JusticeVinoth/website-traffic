/**
 * 
 */
package com.rage.models.website.service.impl;

import java.util.List;

import com.google.inject.Inject;
import com.rage.models.website.Website;
import com.rage.models.website.repository.WebsiteRepository;
import com.rage.models.website.service.WebsiteService;

/**
 * @author neethithevan.r
 *
 */
public class WebsiteServiceImpl implements WebsiteService {

	@Inject
	public WebsiteRepository websiteRepo;

	@Override
	public List<Website> addWebsiteUrl(List<Website> websiteList) {
		return websiteRepo.addWebsiteUrl(websiteList);
	}

	@Override
	public Website getWebsiteByUrl(String url) {
		return websiteRepo.getWebsiteByUrl(url);
	}

	@Override
	public List<Website> getWebsiteList() {
		return websiteRepo.getWebsiteList();
	}

	@Override
	public Website getWebsiteById(String id) {
		return websiteRepo.getWebsiteById(id);
	}

	@Override
	public boolean updateWebsite(String id, Website latestWebsite) {
		return websiteRepo.updateWebsite(id, latestWebsite);
	}

}
