package org.treblefrei.kedr.settings;

public interface SettingsManager {
 
	AbstractSettingsStorage load();
	boolean save(AbstractSettingsStorageReadOnly settingsStorage);
}
 
