package com.boighor.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class ShopcartModel {
	@ValueMapValue(name = "cartCount")
	private String cartCount;
	@ValueMapValue(name = "closeTitle")
	private String closeTitle;
	@ValueMapValue(name = "subTotalTitle")
	private String subTotalTitle;
	@ValueMapValue(name = "cartTotal")
	private String cartTotal;
	@ValueMapValue(name = "buttonTitle")
	private String buttonTitle;
	@ValueMapValue(name = "buttonHref")
	private String buttonHref;
	@ValueMapValue(name = "bottomButtonTitle")
	private String bottomButtonTitle;
	@ValueMapValue(name = "bottomButtonHref")
	private String bottomButtonHref;
	@Inject
	private List<CartList> cartList;
	public String getCartCount() {
		return cartCount;
	}
	public String getCloseTitle() {
		return closeTitle;
	}
	public String getSubTotalTitle() {
		return subTotalTitle;
	}
	public String getCartTotal() {
		return cartTotal;
	}
	public String getButtonTitle() {
		return buttonTitle;
	}
	public String getButtonHref() {
		return buttonHref;
	}
	public String getBottomButtonTitle() {
		return bottomButtonTitle;
	}
	public String getBottomButtonHref() {
		return bottomButtonHref;
	}
	public List<CartList> getCartList() {
		return cartList;
	}
}
