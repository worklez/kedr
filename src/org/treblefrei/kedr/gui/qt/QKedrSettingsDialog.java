package org.treblefrei.kedr.gui.qt;

import com.trolltech.qt.gui.QDialog;
import org.treblefrei.kedr.gui.SettingsChanger;
import org.treblefrei.kedr.settings.AbstractSettingReadyOnly;

public class QKedrSettingsDialog extends QDialog implements SettingsChanger {
 
	private QProxyServerSettings qProxyServerSettings;
	 
	private QMusicBrainzMirrorSettings qMusicBrainzMirrorSettings;
	 
	private QMusicDirectorySettings qMusicDirectorySettings;
	 
	private QKedrMainWindow qKedrMainWindow;
	 
	/**
	 * @see org.treblefrei.kedr.gui.SettingsChanger#getChanges()
	 */
	public AbstractSettingReadyOnly getChanges() {
		return null;
	}
	 
}
 
