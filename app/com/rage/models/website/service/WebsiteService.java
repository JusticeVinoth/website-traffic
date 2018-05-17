/**
 * 
 */
package com.rage.models.website.service;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.rage.models.website.Website;
import com.rage.models.website.service.impl.WebsiteServiceImpl;

/**
 * @author neethithevan.r
 *
 */
@ImplementedBy(WebsiteServiceImpl.class)
public interface WebsiteService {

	public List<Website> addWebsiteUrl(List<Website> websiteList);
	
	public Website getWebsiteByUrl(String url);
	
	public List<Website> getWebsiteList();
}
