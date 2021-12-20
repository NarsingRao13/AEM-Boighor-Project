package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class FooterTags {
	@ValueMapValue(name = "tagTitle")
	private String tagTitle;
	@ValueMapValue(name = "tagLink")
	private String tagLink;

	public String getTagTitle() {
		return tagTitle;
	}

	public String getTagLink() {
		return tagLink;
	}

}
