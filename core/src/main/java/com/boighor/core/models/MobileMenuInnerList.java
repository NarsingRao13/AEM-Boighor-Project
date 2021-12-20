package com.boighor.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MobileMenuInnerList {
	@ValueMapValue(name = "title")
	private String title;
	@ValueMapValue(name = "link")
	private String link;
	@ValueMapValue(name = "isParent")
	private String isParent;
	@Inject
	private List<MobileMenuSubInnerList> mobileMenuSubInnerList;
	public String getTitle() {
		return title;
	}
	public String getLink() {
		return link;
	}
	public String getIsParent() {
		return isParent;
	}
	public List<MobileMenuSubInnerList> getMobileMenuSubInnerList() {
		return mobileMenuSubInnerList;
	}
}
