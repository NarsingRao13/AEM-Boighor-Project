package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class CartList {
	@ValueMapValue(name = "productTitle")
	private String productTitle;
	@ValueMapValue(name = "productLInk")
	private String productLInk;
	@ValueMapValue(name = "productImage")
	private String productImage;
	@ValueMapValue(name = "productPrice")
	private String productPrice;
	@ValueMapValue(name = "productCount")
	private String productCount;
	@ValueMapValue(name = "isFirst")
	private String isFirst;
	public String getProductTitle() {
		return productTitle;
	}
	public String getProductLInk() {
		return productLInk;
	}
	public String getProductImage() {
		return productImage;
	}
	public String getProductPrice() {
		return productPrice;
	}
	public String getProductCount() {
		return productCount;
	}
	public String getIsFirst() {
		return isFirst;
	}
}
