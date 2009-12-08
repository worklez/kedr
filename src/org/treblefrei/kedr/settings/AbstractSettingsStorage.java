package org.treblefrei.kedr.settings;

public interface AbstractSettingsStorage extends AbstractSettingsStorageReadOnly {
 
	// private Settings settings;
	 
	public abstract AbstractSetting getSetting(String key);
}
 
