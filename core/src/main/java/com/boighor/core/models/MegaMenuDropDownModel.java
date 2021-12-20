package com.boighor.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MegaMenuDropDownModel {
	@ValueMapValue(name = "navTitle")
	private String navTitle;
	@Inject
	private List<MegaMenuDropDownList> megaMenuDropDownList;
	
	public String getNavTitle() {
		return navTitle;
	}

	public List<MegaMenuDropDownList> getMegaMenuDropDownList() {
		return megaMenuDropDownList;
	}
}
