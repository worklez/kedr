package org.treblefrei.kedr.gui.qt;

import com.trolltech.qt.gui.QWidget;
import org.treblefrei.kedr.gui.SettingsChanger;
import org.treblefrei.kedr.settings.AbstractSettingReadyOnly;

public class QMusicDirectorySettings extends QWidget implements SettingsChanger {
 
	private String directory;
	 
	private QKedrSettingsDialog qKedrSettingsDialog;
	 
	/**
	 * @see org.treblefrei.kedr.gui.SettingsChanger#getChanges()
	 */
	public AbstractSettingReadyOnly getChanges() {
		return null;
	}
	 
}
 
