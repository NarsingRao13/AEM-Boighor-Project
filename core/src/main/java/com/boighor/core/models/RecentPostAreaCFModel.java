package com.boighor.core.models;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.jcr.Node;
import javax.jcr.NodeIterator;
import javax.jcr.Property;
import javax.jcr.RepositoryException;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.SlingObject;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import com.boighor.boighor.core.utils.Blog;

@Model(adaptables = SlingHttpServletRequest.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class RecentPostAreaCFModel {
	@SlingObject
	private ResourceResolver resourceResolver;
	@ValueMapValue(name = "title")
	private String title;
	@ValueMapValue(name = "description")
	private String description;
	@ValueMapValue(name = "cfdata")
	private String cfdata;
	private String title1;
	private String title2;
	private List<Blog> blogs = new ArrayList<Blog>();

	public String getTitle() {
		return title;
	}

	public String getDescription() {
		return description;
	}

	public String getCfdata() {
		return cfdata;
	}

	public String getTitle1() {
		return title1;
	}

	public String getTitle2() {
		return title2;
	}

	public List<Blog> getBlogs() {
		return blogs;
	}

	@PostConstruct
	void init() {
		String[] s = title.split(" ");
		title1 = s[0];
		title2 = s[1];
		Node cfNode = resourceResolver.getResource(cfdata + "/jcr:content/data/").adaptTo(Node.class);
		try {
			if (cfNode.hasNodes()) {
				NodeIterator nodes = cfNode.getNodes();
				while (nodes.hasNext()) {
					Node node = nodes.nextNode();
					if (node != null) {
						if (!node.getName().equals("master")) {
							Blog blog = new Blog();
							if (node.hasProperty("title")) {
								Property pro = node.getProperty("title");
								blog.setTitle(pro.getString());
							}
							if (node.hasProperty("description")) {
								Property pro = node.getProperty("description");
								blog.setDescription(pro.getString());
							}
							if (node.hasProperty("path")) {
								Property pro = node.getProperty("path");
								blog.setPath(pro.getString());
							}
							if (node.hasProperty("date")) {
								Property pro = node.getProperty("date");
								blog.setDate(pro.getString());
							}
							if (node.hasProperty("likesCount")) {
								Property pro = node.getProperty("likesCount");
								blog.setLikesCount((int) pro.getDouble());
							}
							if (node.hasProperty("commentCount")) {
								Property pro = node.getProperty("commentCount");
								blog.setCommentCount((int) pro.getDouble());
							}
							blogs.add(blog);
						}
					}
				}
			}
		} catch (RepositoryException e) {
			e.printStackTrace();
		}
	}
}