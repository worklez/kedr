package org.treblefrei.kedr.gui.qt;

import com.trolltech.qt.core.QDir;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.*;
import org.treblefrei.kedr.model.Album;

public class QKedrMainWindow extends QMainWindow {

	private QKedrAboutDialog aboutDialog;
	private QKedrSettingsDialog settingsDialog;
	private QKedrWorkspace workspace;
	private QKedrAlbumWindow albumWindow;
	private QKedrAboutDialog qKedrAboutDialog;
	private QKedrSettingsDialog qKedrSettingsDialog;
	private QKedrWorkspace qKedrWorkspace;
	private QKedrAlbumWindow qKedrAlbumWindow;

    private QAction aboutAction;
    private QAction exitAction;
    private QAction addNewAlbumAction;

    private QDir directory;

	public void setSelectedAlbum(Album album) {

	}

	public void offerNewAlbumAdding() {
        String path = QFileDialog.getExistingDirectory(this, tr("Directory"), directory.path());

        if (path.isEmpty() == false) {
            directory.setPath(path);
            //TODO: get file list and pass to AlbumLoader
            }
	}

	public void fetchAlbumInfo(Album album) {

	}

    public QKedrMainWindow(){
        setMenuBar(new QMenuBar());

        createActions();
        createMenu();

        directory = new QDir();
    }

    private boolean createActions() {
        aboutAction = new QAction(null, tr("&About"), this);
        aboutAction.setStatusTip(tr("Show KEDR's about dialog"));
        aboutAction.triggered.connect(this, "showAboutDialog()");

        addNewAlbumAction = new QAction(null, tr("Add &new album directory"), this);
        addNewAlbumAction.setStatusTip(tr("Add new album directory to workspace"));
        addNewAlbumAction.triggered.connect(this, "offerNewAlbumAdding()");

        exitAction = new QAction(null, tr("&Exit"), this);
        exitAction.setShortcut(new QKeySequence(tr("Ctrl+Q")));
        exitAction.setStatusTip(tr("Exit the application"));

        return true;
    }


    private boolean createMenu() {
        QMenu fileMenu = menuBar().addMenu(tr("&File"));
        QMenu helpMenu = menuBar().addMenu(tr("&Help"));

        fileMenu.addAction(exitAction);
        fileMenu.addAction(addNewAlbumAction);
        helpMenu.addAction(aboutAction);

        return true;
    }

    private boolean showAboutDialog() {
        QKedrAboutDialog dialog = new QKedrAboutDialog(this);
        dialog.show();
        
        return true;
    }


    public void run(String []args) {

        show();


    }

    public static void main(String []args) {
        QApplication.initialize(args);
        QKedrMainWindow mainWindow = new QKedrMainWindow();

        mainWindow.show();
        QApplication.exec();
    }

}


