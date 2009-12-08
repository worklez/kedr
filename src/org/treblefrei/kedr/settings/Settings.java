package org.treblefrei.kedr.settings;

public class Settings implements SettingsManager {
 
	private AbstractSettingsStorage settingsStorage;
	 
	private AbstractSettingsStorage abstractSettingsStorage;
	 
	public void getProxyServer() {
	 
	}
	 
	public void getMusicDirectory() {
	 
	}
	 
	public void getMusicBrainzMirror() {
	 
	}
	 
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
 
