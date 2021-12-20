package com.boighor.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class FooterModel {

	@ValueMapValue(name = "logo")
	private String logo;

	@ValueMapValue(name = "logoLInkURL")
	private String logoLink;

	@ValueMapValue(name = "logoAlterTitle")
	private String logoAlterTitle;

	@ValueMapValue(name = "description")
	private String description;

	@ValueMapValue(name = "copyrightText")
	private String copyrightText;

	@ValueMapValue(name = "payment")
	private String payment;

	@Inject
	private List<SocialIcons> socialIcons;
	@Inject
	private List<FooterTags> footerTags;
	public String getLogo() {
		return logo;
	}
	public String getLogoLink() {
		return logoLink;
	}
	public String getLogoAlterTitle() {
		return logoAlterTitle;
	}
	public String getDescription() {
		return description;
	}
	public String getCopyrightText() {
		return copyrightText;
	}
	public String getPayment() {
		return payment;
	}
	public List<SocialIcons> getSocialIcons() {
		return socialIcons;
	}
	public List<FooterTags> getFooterTags() {
		return footerTags;
	}
	
	
}
