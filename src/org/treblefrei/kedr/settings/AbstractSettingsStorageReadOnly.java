package org.treblefrei.kedr.settings;

public interface AbstractSettingsStorageReadOnly {
 
	public abstract AbstractSettingReadyOnly getSetting(String key);
}
 
