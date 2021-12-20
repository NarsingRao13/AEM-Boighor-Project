package com.boighor.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HeaderBodyModel {
	@Inject
	private List<NavList> navList;
	@Inject
	private List<HeaderSideBarList> headerSideBarList;
	@Inject
	private List<MegaMenuList> megaMenuList;

	public List<NavList> getNavList() {
		return navList;
	}

	public List<MegaMenuList> getMegaMenuList() {
		return megaMenuList;
	}

	public List<HeaderSideBarList> getHeaderSideBarList() {
		return headerSideBarList;
	}	
}
