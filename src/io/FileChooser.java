package io;

import javax.swing.*;
import java.io.File;

/*
    Display a pop-up to choose a file/ or a write destination

 */

public class FileChooser extends JFileChooser {

    public File chooseFile() {
        if (showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return getSelectedFile();
        } else {

            return null;
        }
    }

    public File chooseSaveLocation() {
        if (showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            return getSelectedFile();
        } else {

            return null;
        }
    }
}
