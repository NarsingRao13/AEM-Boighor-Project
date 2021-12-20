package com.boighor.core.models;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class AllProductsModel {
	@ValueMapValue(name = "title")
	private String title;

	@ValueMapValue(name = "description")
	private String description;
	@Inject
	private List<ProductType> productType;
	private String title1;
	private String title2;
	@Inject
	private List<AllProducts> allProducts;
	@Inject
	private List<BiographicProducts> biographicProducts;
	@Inject
	private List<AdventureProducts> adventureProducts;
	@Inject
	private List<ChildrenProducts> childrenProducts;
	@Inject
	private List<CookProducts> cookProducts;

	@PostConstruct
	void init() {
		String[] s = title.split(" ");
		title1 = s[0];
		title2 = s[1];
	}

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getTitle1() {
		return title1;
	}

	public String getTitle2() {
		return title2;
	}

	public List<ProductType> getProductType() {
		return productType;
	}

	public List<AllProducts> getAllProducts() {
		return allProducts;
	}

	public List<BiographicProducts> getBiographicProducts() {
		return biographicProducts;
	}

	public List<AdventureProducts> getAdventureProducts() {
		return adventureProducts;
	}

	public List<ChildrenProducts> getChildrenProducts() {
		return childrenProducts;
	}

	public List<CookProducts> getCookProducts() {
		return cookProducts;
	}
}
