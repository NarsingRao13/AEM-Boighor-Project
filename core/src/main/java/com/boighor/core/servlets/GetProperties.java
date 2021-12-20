package com.boighor.core.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.jcr.RepositoryException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.HttpConstants;
import org.apache.sling.api.servlets.SlingSafeMethodsServlet;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.boighor.core.services.Search;
import com.boighor.core.utils.BoighorConstants;

@Component(service = Servlet.class, property = { "sling.servlet.methods=" + HttpConstants.METHOD_GET,
		"sling.servlet.paths=" + "/bin/GetProperties", "sling.servlet.extensions=" + "txt" })
public class GetProperties extends SlingSafeMethodsServlet {

	private static final long serialVersionUID = 1L;
	private static final Logger LOG = LoggerFactory.getLogger(GetProperties.class);
	@Reference
	private Search search;

	@Override
	protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
			throws ServletException, IOException {
		List<String> dd = new ArrayList<String>();
		response.setContentType("application/json");
		PrintWriter out = response.getWriter();
		String property = request.getParameter("param");
		String folderName = request.getParameter("folderName");
		folderName = (BoighorConstants.PARENT_PATH + folderName).trim();
		search.setResourceResolver(request.getResourceResolver());
		try {
			dd.addAll(search.searchForNodesWithPropertie(property, folderName));
			out.println(dd.toString().replace("[", "").replace("]", ""));
		} catch (RepositoryException e) {
			LOG.error(e.getMessage());
			e.printStackTrace();
		}
	}
}


