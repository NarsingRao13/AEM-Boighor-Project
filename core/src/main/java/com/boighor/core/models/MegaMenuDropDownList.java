package com.boighor.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MegaMenuDropDownList {
	@ValueMapValue(name = "navTitle")
	private String navTitle;
	@ValueMapValue(name = "navLInk")
	private String navLInk;
	@ValueMapValue(name = "isParent")
	private String isParent;
	@Inject
	private List<MegaMenuDropDownListData> megaMenuDropDownListData;
	public String getNavTitle() {
		return navTitle;
	}
	public String getNavLInk() {
		return navLInk;
	}
	public String getIsParent() {
		return isParent;
	}
	public List<MegaMenuDropDownListData> getMegaMenuDropDownListData() {
		return megaMenuDropDownListData;
	}
	
}
