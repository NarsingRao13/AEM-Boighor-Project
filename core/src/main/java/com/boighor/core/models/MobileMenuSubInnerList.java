package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class MobileMenuSubInnerList {
	@ValueMapValue(name = "title")
	private String title;
	@ValueMapValue(name = "link")
	private String link;
	public String getTitle() {
		return title;
	}
	public String getLink() {
		return link;
	}
}
