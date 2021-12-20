package com.boighor.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SettingsModel {
	@Inject
	private List<SettingsContentList> settingsContentList;

	public List<SettingsContentList> getSettingsContentList() {
		return settingsContentList;
	}
}
