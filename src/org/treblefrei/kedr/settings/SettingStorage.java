package org.treblefrei.kedr.settings;

import java.util.Map;

public class SettingStorage implements AbstractSettingsStorage {
 
	private Map<String,AbstractSetting> settings;
	 
	private AbstractSetting abstractSetting;
	 
	/**
	 * @see org.treblefrei.kedr.settings.AbstractSettingsStorage#getSetting(java.lang.String)
	 */
	public AbstractSetting getSetting(String key) {
		return null;
	}
	 
}
 
