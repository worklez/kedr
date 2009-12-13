package org.treblefrei.kedr.gui.qt;

import com.trolltech.qt.gui.*;

public class QKedrAboutDialog extends QDialog {

    public QKedrAboutDialog(QWidget parent) {
        super(parent);
        setModal(true);
        setWindowTitle("About");
        QVBoxLayout layout = new QVBoxLayout();
        QLabel about = new QLabel("KEDR работает на электрон-позитронном коллайдере ВЭПП-4М", this);
        QPushButton okButton = new QPushButton("OK", this);
        layout.addWidget(about);
        layout.addWidget(okButton);
        setLayout(layout);
        okButton.clicked.connect(this, "close()");
    }
}
 
