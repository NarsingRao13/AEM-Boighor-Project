package com.boighor.core.servlets;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.io.IOUtils;

import com.boighor.core.services.Search;
import com.boighor.core.utils.BoighorConstants;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_PUT,
		"sling.servlet.paths=" + "/bin/searchOffers", "sling.servlet.extensions=" + "json" })
public class SearchOffers extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(SearchOffers.class);
	@Reference
	private Search search;
	private String searchText;

	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		try {
			JSONArray result = new JSONArray();
			response.setContentType("application/json");
			search.setResourceResolver(request.getResourceResolver());
			String body = IOUtils.toString(request.getReader());
			LOG.info("body " + body);
			JSONObject bodyJSON = new JSONObject(body);
			try {
				if (bodyJSON.has("isbundle")) {
					String isbundle = bodyJSON.getString("isbundle");
					bodyJSON.remove("isbundle");
					if (isbundle.equals("true")) {

						String offersQuery = "SELECT * FROM [nt:unstructured] AS node WHERE ISDESCENDANTNODE(node,'"
								+ BoighorConstants.PARENT_PATH + "offers')";
						offersQuery = search.getQueryText(offersQuery, bodyJSON);
						String bundleOffersQuery = "SELECT * FROM [nt:unstructured] AS node WHERE ISDESCENDANTNODE(node,'"
								+ BoighorConstants.PARENT_PATH + "bundle-offers')";
						bundleOffersQuery = search.getQueryText(bundleOffersQuery, bodyJSON);
						searchText = offersQuery + " UNION " + bundleOffersQuery;

						result = search.searchOffers1(searchText, bodyJSON);
						response.getWriter().println(result);
					} else if (isbundle.equals("false")) {
						searchText = "SELECT * FROM [nt:unstructured] AS node WHERE ISDESCENDANTNODE(node,'"
								+ BoighorConstants.PARENT_PATH + "offers')";
						searchText = search.getQueryText(searchText, bodyJSON);
						result = search.searchOffers1(searchText, bodyJSON);
						response.getWriter().println(result);
					}
				}
			} catch (JSONException | RepositoryException e) {
				LOG.error(e.getLocalizedMessage());
			}

		} catch (JSONException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
	}

	/*
	 * private String getQueryText(String queryText, JSONObject properties) throws
	 * JSONException { Iterator<String> iterator = properties.keys(); while
	 * (iterator.hasNext()) { String property = iterator.next(); if
	 * (!properties.getString(property).isEmpty() &&
	 * !property.equalsIgnoreCase("isactive")) { queryText = queryText +
	 * " AND  node.[" + property + "] = '" + properties.getString(property) + "'"; }
	 * } return queryText; }
	 */

}
