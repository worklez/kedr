package org.treblefrei.kedr.gui.qt;

import com.trolltech.qt.QSignalEmitter;
import org.treblefrei.kedr.core.Updatable;
import org.treblefrei.kedr.gui.WorkspaceItem;
import org.treblefrei.kedr.model.Album;

public class QKedrWorkspace implements Updatable {
 
	public QSignalEmitter.Signal1<Album> selectedAlbumChanged;
	 
	private QKedrMainWindow qKedrMainWindow;
	 
	private WorkspaceItem workspaceItem;
	 
	/**
	 * @see org.treblefrei.kedr.core.Updatable#perfomed()
	 */
	public boolean perfomed() {
		return false;
	}

}
 
