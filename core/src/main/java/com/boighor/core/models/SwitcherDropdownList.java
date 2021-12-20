package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SwitcherDropdownList {
	@ValueMapValue(name = "dropDownData")
	private String dropDownData;

	public String getDropDownData() {
		return dropDownData;
	}
}
