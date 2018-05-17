/**
 * 
 */
package com.rage.models.website.repository;

import java.util.List;

import com.google.inject.ImplementedBy;
import com.rage.models.website.Website;
import com.rage.models.website.repository.impl.WebsiteRepositoryImpl;

/**
 * @author neethithevan.r
 *
 */
@ImplementedBy(WebsiteRepositoryImpl.class)
public interface WebsiteRepository {
	
	public Website getWebsiteByUrl(String websiteUrl);
	
	public List<Website> addWebsiteUrl(List<Website> websiteList);
	
	public List<Website> getWebsiteList();
	
}
