package com.boighor.core.services;

import java.util.Set;

import javax.jcr.RepositoryException;
import javax.jcr.query.InvalidQueryException;

import org.apache.sling.api.resource.ResourceResolver;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public interface Search {
	public ResourceResolver getResourceResolver();
	public void setResourceResolver(ResourceResolver resourceResolver);
	public Set<String> searchForNodesWithPropertie(String property, String folderName) throws InvalidQueryException, RepositoryException;
	public JSONArray searchOffers(JSONObject properties,String folderName) throws JSONException, InvalidQueryException, RepositoryException;
	public JSONArray searchOffers1(String queryText, JSONObject properties) throws JSONException, InvalidQueryException, RepositoryException;
	public JSONArray searchBundleOffers(JSONObject properties,String folderName) throws JSONException, InvalidQueryException, RepositoryException;
	public String getQueryText(String offersQuery, JSONObject bodyJSON) throws JSONException;
}
