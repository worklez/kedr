package org.treblefrei.kedr.settings.xml;

import org.treblefrei.kedr.settings.AbstractSettingsStorage;
import org.treblefrei.kedr.settings.AbstractSettingsStorageReadOnly;
import org.treblefrei.kedr.settings.SettingsManager;

public class XMLSettingsManager implements SettingsManager {
 
	/**
	 * @see org.treblefrei.kedr.settings.SettingsManager#load()
	 */
	public AbstractSettingsStorage load() {
		return null;
	}
	 
	/**
	 * @see org.treblefrei.kedr.settings.SettingsManager#save(org.treblefrei.kedr.settings.AbstractSettingsStorageReadOnly)
	 */
	public boolean save(AbstractSettingsStorageReadOnly settingsStorage) {
	    return false;
	}
	 
}
 
