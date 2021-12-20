package com.boighor.core.servlets;

import java.util.LinkedList;
import java.util.List;

import javax.jcr.Node;
import javax.jcr.Session;
import javax.jcr.query.Query;
import javax.jcr.query.QueryResult;
import javax.jcr.query.Row;
import javax.jcr.query.RowIterator;
import javax.servlet.Servlet;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.day.cq.search.QueryBuilder;

@Component(service = Servlet.class, property = { Constants.SERVICE_DESCRIPTION + "=Query Builder servlet",
		"sling.servlet.methods=" + HttpConstants.METHOD_GET, "sling.servlet.paths=" + "/bin/cfquery"

})
public class ContentFragmentQuery extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 2610051404257637265L;

	private static final Logger log = LoggerFactory.getLogger(ContentFragmentQuery.class);

	@Reference
	private QueryBuilder builder;

	private Session session;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response) {

		try {
			String brand = request.getParameter("brand").replace('+', ' ');
			String description = request.getParameter("dec").replace('+', ' ');
			String queryDescription = "SELECT * FROM [nt:unstructured] WHERE description='" + description
					+ "' AND ISDESCENDANTNODE([/content/dam/tcp/app-core/app-content/offers])";
			String queryBrand = "SELECT * FROM [nt:unstructured] WHERE brandTitle='" + brand
					+ "' AND ISDESCENDANTNODE([/content/dam/tcp/app-core/app-content/offers])";
			String query = queryBrand+"UNION "+queryDescription;
			log.info(query);
			ResourceResolver resourceResolver = request.getResourceResolver();
			
			List ar = new LinkedList();
			session = resourceResolver.adaptTo(Session.class);

			Query query1 = session.getWorkspace().getQueryManager().createQuery(query, Query.JCR_SQL2);
			QueryResult result = query1.execute();

			RowIterator iterator = result.getRows();
			while (iterator.hasNext()) {

				Row row = iterator.nextRow();
				Node n = row.getNode();
				ar.add("http://localhost:4502/editor.html" + n.getPath().replace("/jcr:content/data/master", ""));
			}
			response.getWriter().print(ar.toString().replace('[', ' ').replace(']', ' '));

		} catch (Exception e) {

			log.error(e.getMessage(), e);
		} finally {

			if (session != null) {

				session.logout();
			}
		}
	}

}
