package com.boighor.core.models;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RecentPostAreaModel {
	@ValueMapValue(name = "title")
	private String title;
	@ValueMapValue(name = "description")
	private String description;
	@Inject
	private List<Blogs> blogs;
	private String title1;
	private String title2;
	
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

	public List<Blogs> getBlogs() {
		return blogs;
	}
}
