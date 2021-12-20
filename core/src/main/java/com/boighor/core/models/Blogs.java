package com.boighor.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class,defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Blogs {
	@ValueMapValue(name = "blogTitle")
	private String blogTitle;
	@ValueMapValue(name = "blogLink")
	private String blogLink;
	@ValueMapValue(name = "blogDescription")
	private String blogDescription;
	@ValueMapValue(name = "date")
	private String date;
	@ValueMapValue(name = "likeCount")
	private String likeCount;
	@ValueMapValue(name = "commnentCount")
	private String commnentCount;
	public String getBlogTitle() {
		return blogTitle;
	}
	public String getBlogLink() {
		return blogLink;
	}
	public String getBlogDescription() {
		return blogDescription;
	}
	public String getDate() {
		return date;
	}
	public String getLikeCount() {
		return likeCount;
	}
	public String getCommnentCount() {
		return commnentCount;
	}
	
	
}
