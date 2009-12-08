package org.treblefrei.kedr.gui.qt;

import com.trolltech.qt.gui.QAbstractButton;
import com.trolltech.qt.gui.QWidget;
import org.treblefrei.kedr.core.Updatable;
import org.treblefrei.kedr.model.Album;

public class QKedrAlbumWindow extends QWidget implements Updatable {
 
	private QAbstractButton queryButton;
	 
	private QKedrMainWindow qKedrMainWindow;
	 
	private QAbstractButton qAbstractButton;
	 
	public void setAlbum(Album album) {
	 
	}
	 
	private void fetchAlbumInfo() {
	 
	}
	 
	/**
	 * @see org.treblefrei.kedr.core.Updatable#perfomed()
	 */
	public boolean perfomed() {
		return false;
	}

}
 
