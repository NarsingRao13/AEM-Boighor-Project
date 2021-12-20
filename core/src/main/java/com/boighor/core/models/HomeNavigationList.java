package com.boighor.core.models;

import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class HomeNavigationList {
	@ValueMapValue(name = "navTitle")
	private String navTitle;

	@ValueMapValue(name = "navLInkURL")
	private String navLInkURL;

	public String getNavTitle() {
		return navTitle;
	}

	public String getNavLInkURL() {
		return navLInkURL;
	}

}
