package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SaleProducts {
	@ValueMapValue(name = "productLink")
	private String productLink;
	@ValueMapValue(name = "index")
	private String index;

	public String getProductLink() {
		return productLink;
	}

	public String getIndex() {
		return index;
	}
	
}
