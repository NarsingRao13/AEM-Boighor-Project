package com.boighor.core.models;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderModel {
	private static final Logger LOG = (Logger) LoggerFactory.getLogger(HeaderModel.class);
	@Inject
	private String logo;
	@ValueMapValue
	@Named("logoLInkURL")
	private String logoLink;
	@ValueMapValue
	@Named("logoAlterTitle")
	private String logoAlterTitle;

	@Inject
	// @ValueMapValue(name = "homeNavigationList")
	private List<HomeNavigationList> homeNavigationList;

	public String getLogo() {
		return logo;
	}

	public String getLogoLink() {
		return logoLink;
	}

	public String getLogoAlterTitle() {
		return logoAlterTitle;
	}

	public List<HomeNavigationList> getHomeNavigationList() {
		return homeNavigationList;
	}

	@PostConstruct
	void init() {
		LOG.info(logo);
		LOG.info(logoLink);
		LOG.info(logoAlterTitle);
		LOG.info(getHomeNavigationList().toString());
		for (int i = 0; i < getHomeNavigationList().size(); i++) {
			LOG.info(getHomeNavigationList().get(i).getNavTitle());
		}
	}
}
