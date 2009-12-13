package org.treblefrei.kedr.gui.qt;

import com.trolltech.qt.gui.*;

public class QKedrAboutDialog extends QDialog {

    public QKedrAboutDialog(QWidget parent) {
        super(parent);
        setModal(true);
        setWindowTitle(tr("About"));
        QVBoxLayout layout = new QVBoxLayout();
        QLabel about = new QLabel(tr("KEDR runs on the electron-positron collider VEPP-4M"), this);
        QPushButton okButton = new QPushButton("OK", this);
        layout.addWidget(about);
        layout.addWidget(okButton);
        setLayout(layout);
        okButton.clicked.connect(this, "close()");
    }
}
 
