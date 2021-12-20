package com.boighor.core.servlets;

import java.io.IOException;
import java.util.Set;

import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import javax.jcr.query.InvalidQueryException;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.json.JSONArray;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boighor.core.services.Search;
import com.boighor.core.utils.BoighorConstants;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.paths=" + "/bin/totalOffers", "sling.servlet.extensions=" + "json" })
public class GetTotalOffers extends SlingSafeMethodsServlet {
	private static final Logger LOG = LoggerFactory.getLogger(GetTotalOffers.class);
	ResourceResolver resourceResolver;
	@Reference
	Search search;
	Session session;
	int count = 0;
	int allCount = 0;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		resourceResolver = request.getResourceResolver();
		String searchText = "SELECT * FROM [nt:unstructured] AS node WHERE ISDESCENDANTNODE(node,'"
				+ BoighorConstants.PARENT_PATH + "offers')";
		LOG.info("searchText = " + searchText);
		session = resourceResolver.adaptTo(Session.class);

		try {
			this.getCount(searchText);
			response.getWriter().println(count);

			Set<String> dd = search.searchForNodesWithPropertie("brandTitle", "offers");
			for (String pro : dd) {
				String searchText1 = "SELECT * FROM [nt:unstructured] AS node WHERE ISDESCENDANTNODE(node,'"
						+ BoighorConstants.PARENT_PATH + "offers') AND node.[" + pro + "]";
			}
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void getCount(String searchText) throws InvalidQueryException, RepositoryException {
		Query query = session.getWorkspace().getQueryManager().createQuery(searchText, BoighorConstants.JCR_SQL2);
		QueryResult result = (query).execute();
		NodeIterator nodeIterator = result.getNodes();
		while (nodeIterator.hasNext()) {
			Node node = nodeIterator.nextNode();
			count++;
		}

	}
}
