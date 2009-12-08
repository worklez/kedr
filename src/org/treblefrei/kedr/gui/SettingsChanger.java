package org.treblefrei.kedr.gui;

import org.treblefrei.kedr.settings.AbstractSettingReadyOnly;

public interface SettingsChanger {
 
	public abstract AbstractSettingReadyOnly getChanges();
}
 
