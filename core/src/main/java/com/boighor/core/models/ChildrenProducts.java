package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ChildrenProducts {
	@ValueMapValue(name = "image1")
	private String image1;
	@ValueMapValue(name = "image2")
	private String image2;
	@ValueMapValue(name = "hotLabel")
	private String hotLabel;
	@ValueMapValue(name = "productTitle")
	private String productTitle;
	@ValueMapValue(name = "prize")
	private String prize;
	@ValueMapValue(name = "oldPrize")
	private String oldPrize;
	public String getImage1() {
		return image1;
	}
	public String getImage2() {
		return image2;
	}
	public String getHotLabel() {
		return hotLabel;
	}
	public String getProductTitle() {
		return productTitle;
	}
	public String getPrize() {
		return prize;
	}
	public String getOldPrize() {
		return oldPrize;
	}
}
