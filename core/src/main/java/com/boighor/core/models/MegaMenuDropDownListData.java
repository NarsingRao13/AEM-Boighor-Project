package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MegaMenuDropDownListData {
	@ValueMapValue(name = "navMegaMenuDDTitle")
	private String navMegaMenuDDTitle;
	@ValueMapValue(name = "navMegaMenuDDLInk")
	private String navMegaMenuDDLInk;
	public String getNavMegaMenuDDTitle() {
		return navMegaMenuDDTitle;
	}
	public String getNavMegaMenuDDLInk() {
		return navMegaMenuDDLInk;
	}
}
