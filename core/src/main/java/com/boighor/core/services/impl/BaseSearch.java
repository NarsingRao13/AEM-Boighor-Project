package com.boighor.core.services.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.ValueFormatException;
import javax.jcr.query.InvalidQueryException;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;

import org.apache.sling.api.resource.ResourceResolver;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boighor.core.services.Search;
import com.boighor.core.utils.BoighorConstants;

@Component(service = Search.class)
public class BaseSearch implements Search {
	private static final Logger LOG = LoggerFactory.getLogger(Search.class);
	private ResourceResolver resourceResolver;

	@Override
	public ResourceResolver getResourceResolver() {
		return resourceResolver;
	}

	@Override
	public void setResourceResolver(ResourceResolver resourceResolver) {
		this.resourceResolver = resourceResolver;
	}

	@Override
	public Set<String> searchForNodesWithPropertie(String property, String folderName)
			throws InvalidQueryException, RepositoryException {
		JSONArray results = new JSONArray();
		Set<String> hash_Set = new HashSet<String>();
		Session session = getResourceResolver().adaptTo(Session.class);
		String searchText = "SELECT * FROM [nt:unstructured] AS node WHERE ISDESCENDANTNODE(node,'" + folderName
				+ "') AND node.[" + property + "] IS NOT NULL AND node.[" + property + "] <> '' ";
		Query query = session.getWorkspace().getQueryManager().createQuery(searchText, BoighorConstants.JCR_SQL2);
		QueryResult result = (query).execute();
		NodeIterator iterator = result.getNodes();
		while (iterator.hasNext()) {
			Node node = iterator.nextNode();
			if (node.hasProperty(property)) {
				hash_Set.add(node.getProperty(property).getValue().toString());
				results.put(BoighorConstants.URL + node.getPath().replace(BoighorConstants.SUB_NODE, ""));
			}
		}
		LOG.info(hash_Set.toString());
		return hash_Set;
	}

	@Override
	public JSONArray searchOffers(JSONObject properties, String folderName)
			throws JSONException, InvalidQueryException, RepositoryException {
		JSONArray results = new JSONArray();
		Session session = getResourceResolver().adaptTo(Session.class);
		String searchText = "SELECT * FROM [nt:unstructured] AS node WHERE ISDESCENDANTNODE(node,'"
				+ BoighorConstants.PARENT_PATH + folderName + "')";
		searchText = this.getQueryText(searchText, properties);
		LOG.info("Query = " + searchText);
		Query query = session.getWorkspace().getQueryManager().createQuery(searchText, BoighorConstants.JCR_SQL2);
		QueryResult result = (query).execute();
		if (result.getRows().getSize() == 0) {
			results.put("nodata");
			return results;
		}
		NodeIterator nodeIterator = result.getNodes();
		while (nodeIterator.hasNext()) {
			Node node = nodeIterator.nextNode();
			Node jcr_Content_Node = node.getParent().getParent();

			// IS Active Code

			if (properties.getString("isactive").equals("true")) {
				if (jcr_Content_Node.hasProperty("cq:lastReplicationAction")) {
					String isActiveValue = jcr_Content_Node.getProperty("cq:lastReplicationAction").getValue()
							.toString();
					if (isActiveValue.equalsIgnoreCase("Activate")) {
						results.put(BoighorConstants.URL + node.getPath().replace(BoighorConstants.SUB_NODE, ""));
					}
				}
			} else {
				if (jcr_Content_Node.hasProperty("cq:lastReplicationAction")) {
					String isActiveValue = jcr_Content_Node.getProperty("cq:lastReplicationAction").getValue()
							.toString();
					if (!isActiveValue.equalsIgnoreCase("Activate")) {
						results.put(BoighorConstants.URL + node.getPath().replace(BoighorConstants.SUB_NODE, ""));
					}
				} else {
					results.put(BoighorConstants.URL + node.getPath().replace(BoighorConstants.SUB_NODE, ""));
				}
			}

			/*
			 * if (jcr_Content_Node.hasProperty("cq:lastReplicationAction")) { String
			 * isActiveValue =
			 * jcr_Content_Node.getProperty("cq:lastReplicationAction").getValue().toString(
			 * ); if (properties.getString("isactive").equals("true")) { if
			 * (isActiveValue.equalsIgnoreCase("Activate")) {
			 * results.put(BoighorConstants.URL +
			 * node.getPath().replace(BoighorConstants.SUB_NODE, "")); } } else { if
			 * (isActiveValue.equals("Deactivate")) { results.put(BoighorConstants.URL +
			 * node.getPath().replace(BoighorConstants.SUB_NODE, "")); } } } else {
			 * results.put(BoighorConstants.URL +
			 * node.getPath().replace(BoighorConstants.SUB_NODE, "")); }
			 */

		}
		LOG.info("original result size = " + result.getRows().getSize() + " and actual result size = "
				+ results.length());
		if (results.length() == 0) {
			results.put("nodata");
		}
		return results;
	}

	@Override
	public JSONArray searchBundleOffers(JSONObject properties, String folderName)
			throws JSONException, InvalidQueryException, RepositoryException {
		JSONArray results = new JSONArray();
		Session session = getResourceResolver().adaptTo(Session.class);
		String offersQuery = "SELECT * FROM [nt:unstructured] AS node WHERE ISDESCENDANTNODE(node,'"
				+ BoighorConstants.PARENT_PATH + "offers')";
		offersQuery = this.getQueryText(offersQuery, properties);
		String bundleOffersQuery = "SELECT * FROM [nt:unstructured] AS node WHERE ISDESCENDANTNODE(node,'"
				+ BoighorConstants.PARENT_PATH + "bundle-offers')";
		bundleOffersQuery = this.getQueryText(bundleOffersQuery, properties);
		String searchText = offersQuery + " UNION " + bundleOffersQuery;
		LOG.info("Query = " + searchText);
		Query query = session.getWorkspace().getQueryManager().createQuery(searchText, BoighorConstants.JCR_SQL2);
		QueryResult result = (query).execute();
		if (result.getRows().getSize() == 0) {
			results.put("nodata");
			return results;
		}
		NodeIterator nodeIterator = result.getNodes();
		while (nodeIterator.hasNext()) {
			Node node = nodeIterator.nextNode();
			Node jcr_Content_Node = node.getParent().getParent();

			// IS Active Code
			if (jcr_Content_Node.hasProperty("cq:lastReplicationAction")) {
				String isActiveValue = jcr_Content_Node.getProperty("cq:lastReplicationAction").getValue().toString();
				if (properties.getString("isactive").equals("true") && isActiveValue.equalsIgnoreCase("Activate")) {
					results.put(BoighorConstants.URL + node.getPath().replace(BoighorConstants.SUB_NODE, ""));
				} else if (properties.getString("isactive").equals("false")
						&& isActiveValue.equalsIgnoreCase("Deactivate")) {
					results.put(BoighorConstants.URL + node.getPath().replace(BoighorConstants.SUB_NODE, ""));
				}
			}
		}
		LOG.info("size = " + result.getRows().getSize() + "");
		return results;
	}

	@Override
	public String getQueryText(String searchText, JSONObject properties) throws JSONException {
		Iterator<String> iterator = properties.keys();
		while (iterator.hasNext()) {
			String property = iterator.next();
			if (!properties.getString(property).isEmpty() && !property.equalsIgnoreCase("isactive")) {
				searchText = searchText + " AND  node.[" + property + "] = '" + properties.getString(property) + "'";
			}
		}
		return searchText;
	}

	@Override
	public JSONArray searchOffers1(String queryText, JSONObject properties)
			throws JSONException, InvalidQueryException, RepositoryException {
		JSONArray results = new JSONArray();
		Session session = getResourceResolver().adaptTo(Session.class);
		
		LOG.info("Query = " + queryText);
		Query query = session.getWorkspace().getQueryManager().createQuery(queryText, BoighorConstants.JCR_SQL2);
		QueryResult result = (query).execute();
		NodeIterator nodeIterator = result.getNodes();
		while (nodeIterator.hasNext()) {
			JSONObject offer = new JSONObject();
			Node node = nodeIterator.nextNode();
			Node jcr_Content_Node = node.getParent().getParent();

			// IS Active Code
			if (properties.getString("isactive").equals("true")) {
				if (jcr_Content_Node.hasProperty("cq:lastReplicationAction")) {
					String isActiveValue = jcr_Content_Node.getProperty("cq:lastReplicationAction").getValue()
							.toString();
					if (isActiveValue.equalsIgnoreCase("Activate")) {
						offer = this.getOfferProperties(node);
						results.put(offer);
					}
				}
			} else {
				/*
				 * if (jcr_Content_Node.hasProperty("cq:lastReplicationAction")) { String
				 * isActiveValue =
				 * jcr_Content_Node.getProperty("cq:lastReplicationAction").getValue()
				 * .toString(); if (!isActiveValue.equalsIgnoreCase("Activate")) { offer =
				 * this.getOfferProperties(node); results.put(offer); } } else { offer =
				 * this.getOfferProperties(node); results.put(offer); }
				 */
				offer = this.getOfferProperties(node);
				results.put(offer);
			}
		}
		LOG.info("final result length = " + results.length());
		return results;
	}

	public JSONObject getOfferProperties(Node offerNode)
			throws ValueFormatException, IllegalStateException, JSONException, RepositoryException {
		JSONObject offerProperties = new JSONObject();
		offerProperties.put("href", BoighorConstants.URL + offerNode.getPath().replace(BoighorConstants.SUB_NODE, ""));
		if (offerNode.hasProperty("offerId")) {
			offerProperties.put("offerId", offerNode.getProperty("offerId").getValue().toString());
		} else {
			offerProperties.put("offerId", "");
		}

		if (offerNode.hasProperty("brandTitle")) {
			offerProperties.put("brandTitle", offerNode.getProperty("brandTitle").getValue().toString());
		} else {
			offerProperties.put("brandTitle", "");
		}

		if (offerNode.hasProperty("programId")) {
			offerProperties.put("programId", offerNode.getProperty("programId").getValue().toString());
		} else {
			offerProperties.put("programId", "");
		}

		if (offerNode.hasProperty("description")) {
			offerProperties.put("description", offerNode.getProperty("description").getValue().toString());
		} else {
			offerProperties.put("description", "");
		}

		if (offerNode.hasProperty("expiryDate")) {
			offerProperties.put("expiryDate", offerNode.getProperty("expiryDate").getValue().toString());
		} else {
			offerProperties.put("expiryDate", "");
		}
		return offerProperties;
	}
}
