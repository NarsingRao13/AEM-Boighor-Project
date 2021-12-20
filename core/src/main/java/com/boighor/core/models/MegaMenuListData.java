package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MegaMenuListData {
	@ValueMapValue(name = "navMegaMenuTitle")
	private String navMegaMenuTitle;
	@ValueMapValue(name = "navMegaMenuLInk")
	private String navMegaMenuLInk;
	public String getNavMegaMenuTitle() {
		return navMegaMenuTitle;
	}
	public String getNavMegaMenuLInk() {
		return navMegaMenuLInk;
	}
}
