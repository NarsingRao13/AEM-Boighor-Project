package com.boighor.core.servlets;

import java.io.IOException;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.commons.io.IOUtils;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.json.JSONException;
import org.json.JSONObject;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boighor.core.services.Search;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_PUT,
		"sling.servlet.paths=" + "/bin/downloadOffers", "sling.servlet.extensions=" + "json" })
public class DownloadQueryResults extends SlingAllMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(DownloadQueryResults.class);
	@Reference
	private Search search;

	@Override
	protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		try {
			String result = new String();
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
						result = search.searchOffers(bodyJSON, "bundle-offers").toString().replace('[', ' ')
								.replace(']', ' ').replace('\"', ' ');
						result = result + "," + search.searchOffers(bodyJSON, "offers").toString().replace('[', ' ')
								.replace(']', ' ').replace('\"', ' ');
					} else if (isbundle.equals("false")) {
						result = search.searchOffers(bodyJSON, "offers").toString().replace('[', ' ').replace(']', ' ')
								.replace('\"', ' ');
					}
					response.getWriter().println(result);
				}
			} catch (JSONException | RepositoryException e) {
				LOG.error(e.getLocalizedMessage());
			}

		} catch (JSONException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
	}
}
