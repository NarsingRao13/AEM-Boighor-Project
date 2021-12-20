package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NavList {
	@ValueMapValue(name = "navTitle")
	private String navTitle;
	@ValueMapValue(name = "navLInk")
	private String navLInk;
	@ValueMapValue(name = "component")
	private String component;
	public String getNavTitle() {
		return navTitle;
	}
	public String getNavLInk() {
		return navLInk;
	}
	public String getComponent() {
		return component;
	}
	
	
}
