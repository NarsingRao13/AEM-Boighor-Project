package com.boighor.core.models;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adobe.cq.sightly.WCMUsePojo;
import com.boighor.core.services.Search;
import com.boighor.core.services.impl.BaseSearch;

@Model(adaptables = SlingHttpServletRequest.class)
public class DynamicDropDown   {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(DynamicDropDown.class);
	@SlingObject
	private ResourceResolver resolver;
	//@Reference
	private Search search = new BaseSearch();
	private List<String> dropDownData = new LinkedList<String>();

	@Activate
	public void activate() throws Exception {
		LOG.info("Hello ");
		String inputData = "brandTitle";
		String folderName = "offers";
		LOG.info("inputData "+inputData);
		LOG.info("folderName "+folderName);
		LOG.info("resolver "+resolver.toString());
		search.setResourceResolver(resolver);
		dropDownData.addAll( search.searchForNodesWithPropertie(inputData, folderName));
		dropDownData.add("input");
 	}

	public List<String> getDropDownData() {
		return dropDownData;
	}

}
