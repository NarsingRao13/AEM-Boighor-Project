package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class)
public class SocialIcons {
	@ValueMapValue(name = "icon")
	private String icon;

	public String getIcon() {
		return icon;
	}

}
