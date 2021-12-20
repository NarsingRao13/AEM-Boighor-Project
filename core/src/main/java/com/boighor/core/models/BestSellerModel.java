package com.boighor.core.models;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class BestSellerModel {
	@ValueMapValue(name = "title1")
	private String title1;
	@ValueMapValue(name = "title2")
	private String title2;
	@ValueMapValue(name = "description")
	private String description;
	@Inject
	private List<BestSellerProducts> bestSellerProducts;
	public String getTitle1() {
		return title1;
	}
	public String getTitle2() {
		return title2;
	}
	public String getDescription() {
		return description;
	}
	public List<BestSellerProducts> getBestSellerProducts() {
		return bestSellerProducts;
	}
	
}
