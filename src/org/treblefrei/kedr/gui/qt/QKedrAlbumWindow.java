package org.treblefrei.kedr.gui.qt;

import com.trolltech.qt.core.QModelIndex;
import com.trolltech.qt.core.Qt;
import com.trolltech.qt.gui.*;
import org.musicbrainz.JMBWSException;
import org.treblefrei.kedr.audio.AudioDecoderException;
import org.treblefrei.kedr.core.Updatable;
import org.treblefrei.kedr.database.AlbumInfoFetcher;
import org.treblefrei.kedr.model.Album;
import org.treblefrei.kedr.model.Track;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import java.io.IOException;

public class QKedrAlbumWindow extends QWidget implements Updatable {
    private class QTrackListModel extends QAbstractTableModel {
        private Album album;

        public void setAlbum(Album album) {
            if (this.album != null) {
                beginRemoveRows(null, 0, this.album.getTracks().size()-1);
                this.album = null;
                endRemoveRows();
                }
            
            beginInsertRows(null, 0, album.getTracks().size()-1);
            this.album = album;
            endInsertRows();
        }
        @Override
        public Object data(QModelIndex index, int role) {
            if (index == null || album == null)
                return null;

            if (index.row() > album.getTracks().size() || index.row() < 0)
                return null;

            if (role == Qt.ItemDataRole.DisplayRole) {
                Track track = album.getTracks().get(index.row());

                switch(index.column()) {
                    case 0:
                        return String.valueOf(track.getTrackNumber());
                    case 1:
                        return track.getArtist();
                    case 2:
                        return track.getAlbum();
                    case 3:
                        return track.getTitle();
                    case 4:
                        return track.getGenre();
                    case 5:
                        return track.getYear();
                    case 6:
                        return String.format("%02d:%02d", (int)(track.getDuration() / 60 / 1000),
                            (int)(track.getDuration() / 1000)%60);

                    default:
                        return null;
                }
            }

            return null;
        }
        @Override
        public Object headerData(int section, Qt.Orientation orientation, int role) {

            if (role != Qt.ItemDataRole.DisplayRole)
                return null;

            if (orientation == Qt.Orientation.Horizontal) {

                switch(section) {
                    case 0:
                        return tr("Track No");
                    case 1:
                        return tr("Artist");
                    case 2:
                        return tr("Album");
                    case 3:
                        return tr("Title");
                    case 4:
                        return tr("Genre");
                    case 5:
                        return tr("Year");
                    case 6:
                        return tr("Duration");

                    default:
                        return null;
                }
            }

            return null;
        }
        @Override
        public int columnCount(QModelIndex qModelIndex) {
            return 7;
        }
        @Override
        public int rowCount(QModelIndex qModelIndex) {
            if (album == null)
                return 0;
            return  album.getTracks().size();
        }
    }

	private QAbstractButton queryButton = new QPushButton(tr("Tag it!"));
    private QTableView trackList;
    private QTrackListModel trackListModel;

	private QKedrMainWindow qKedrMainWindow;
	 
	private QAbstractButton qAbstractButton;
    private Album selectedAlbum;
    private QHBoxLayout trackListLayout = new QHBoxLayout();
    private QVBoxLayout verticalLayout = new QVBoxLayout();
    private QHBoxLayout buttonLayout = new QHBoxLayout();


    public QKedrAlbumWindow() {
        trackList = new QTableView(this);
        trackListModel = new QTrackListModel();

        trackListLayout.addWidget(trackList);
        buttonLayout.addWidget(queryButton);

        verticalLayout.addLayout(buttonLayout);
        verticalLayout.addLayout(trackListLayout);



        setLayout(verticalLayout);

        trackList.setModel(trackListModel);
        trackList.horizontalHeader().show();
        trackList.resizeColumnsToContents();

        queryButton.clicked.connect(this, "fetchAlbumInfo()");
    }

    public void setAlbum(Album album) {
        if (album != null)
            album.removeUpdatable(this);

        selectedAlbum = album;
        trackListModel.setAlbum(album);
        trackList.resizeColumnsToContents();
	    album.addUpdatable(this);
	}
	 
	private void fetchAlbumInfo() {
        try {
            if (null != selectedAlbum) {
                Album filledAlbum = AlbumInfoFetcher.fetchAlbumInfo(selectedAlbum);
                selectedAlbum.sync(filledAlbum);
            }
        } catch (IOException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (SAXException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (AudioDecoderException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (XPathExpressionException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (ParserConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (JMBWSException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }

    //private void update
	 
	/**
	 * @see org.treblefrei.kedr.core.Updatable#perfomed()
	 */
	public boolean perfomed() {
		return false;
	}

}
 
