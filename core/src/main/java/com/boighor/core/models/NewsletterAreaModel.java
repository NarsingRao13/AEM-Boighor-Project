package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class NewsletterAreaModel {
	@ValueMapValue(name = "sectionTitle")
	private String sectionTitle;
	@ValueMapValue(name = "sectionDescription")
	private String sectionDescription;
	@ValueMapValue(name = "buttonTitle")
	private String buttonTitle;

	public String getSectionTitle() {
		return sectionTitle;
	}

	public String getSectionDescription() {
		return sectionDescription;
	}

	public String getButtonTitle() {
		return buttonTitle;
	}

}
