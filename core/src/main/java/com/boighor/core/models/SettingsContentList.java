package com.boighor.core.models;

import java.util.List;

import javax.inject.Inject;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SettingsContentList {
	@ValueMapValue(name = "title")
	private String title;
	@ValueMapValue(name = "subTitle")
	private String subTitle;
	@ValueMapValue(name = "isSettingMenu")
	private String isSettingMenu;
	@Inject
	private List<SwitcherDropdownList> switcherDropdownList;
	public String getTitle() {
		return title;
	}
	public String getSubTitle() {
		return subTitle;
	}
	public String getIsSettingMenu() {
		return isSettingMenu;
	}
	public List<SwitcherDropdownList> getSwitcherDropdownList() {
		return switcherDropdownList;
	}
}
