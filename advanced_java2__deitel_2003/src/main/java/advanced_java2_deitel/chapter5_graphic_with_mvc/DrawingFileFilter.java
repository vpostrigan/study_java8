// DrawingFileFilter.java
// DrawingFileFilter is a FileFilter subclass for selecting 
// DeitelDrawing files in a JFileChooser dialog.
package advanced_java2_deitel.chapter5_graphic_with_mvc;

import javax.swing.filechooser.FileFilter;
import java.io.File;

public class DrawingFileFilter extends FileFilter {

    // String to use in JFileChooser description
    private String DESCRIPTION = "DeitelDrawing Files (*.dd)";
    private String EXTENSION = ".dd";

    public String getDescription() {
        return DESCRIPTION;
    }

    public boolean accept(File file) {
        return (file.getName().toLowerCase().endsWith(EXTENSION));
    }

}
