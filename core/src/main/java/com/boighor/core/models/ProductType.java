package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ProductType {
	@ValueMapValue(name = "typeTitle")
	private String typeTitle;
	@ValueMapValue(name = "type")
	private String type;
	@ValueMapValue(name = "isActive")
	private String isActive;
	public String getTypeTitle() {
		return typeTitle;
	}
	public String getType() {
		return type;
	}
	public String getIsActive() {
		return isActive;
	}
	
}
