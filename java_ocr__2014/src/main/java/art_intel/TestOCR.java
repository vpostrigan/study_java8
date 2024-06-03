package art_intel;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;

import net.sourceforge.javaocr.ocrPlugins.mseOCR.CharacterRange;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.OCRScanner;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImage;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImageLoader;
import net.sourceforge.javaocr.scanner.PixelImage;
/*
import com.aspose.ocr.ILanguage;
import com.aspose.ocr.ImageStream;
import com.aspose.ocr.Language;
import com.aspose.ocr.OcrEngine;*/

public class TestOCR {

    /**
     * Load demo training images.
     *
     * @param trainingImageDir
     *            The directory from which to load the images.
     */
    public static void loadTrainingImages(OCRScanner scanner, String trainingImageDir) {
        boolean debug = true;
        if (debug) {
            System.err.println("loadTrainingImages(" + trainingImageDir + ")");
        }
        if (!trainingImageDir.endsWith(File.separator)) {
            trainingImageDir += File.separator;
        }
        try {
            scanner.clearTrainingImages();
            TrainingImageLoader loader = new TrainingImageLoader();
            HashMap<Character, ArrayList<TrainingImage>> trainingImageMap = new HashMap<Character, ArrayList<TrainingImage>>();

            if (debug) {
                //System.err.println("ascii.png");
            }
            //loader.load(trainingImageDir + "ascii.png", new CharacterRange('!', '~'), trainingImageMap);
            if (debug) {
                //System.err.println("hpljPica.jpg");
            }
            //loader.load(trainingImageDir + "hpljPica.jpg", new CharacterRange('!', '~'), trainingImageMap);
            if (debug) {
                //System.err.println("digits.jpg");
            }
            loader.load(trainingImageDir + "digits.jpg", new CharacterRange('0', '9'), trainingImageMap);
            loader.load(trainingImageDir + "t1.gif", new CharacterRange('A', 'D'), trainingImageMap);

            if (debug) {
                System.err.println("adding images");
            }
            scanner.addTrainingImages(trainingImageMap);
            if (debug) {
                System.err.println("loadTrainingImages() done");
            }
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(2);
        }
    }

    public static void main(String[] args) throws IOException {
        OCRScanner scanner = new OCRScanner();
        loadTrainingImages(scanner, "D:/workspaces/workspace/art_intel/src/main/java/art_intel/");

        Image image = ImageIO.read(new File(
                "D:/workspaces/workspace/art_intel/src/main/java/art_intel/mfg_busindex_11_25_14.gif"));

        PixelImage pixelImage = new PixelImage(image);
        pixelImage.toGrayScale(true);
        pixelImage.filter();

        long t = System.currentTimeMillis();
        System.out.println("" + ":");
        String text = scanner.scan(image, 0, 0, 0, 0, null);
        System.out.println("[" + text + "]");
        System.out.println(System.currentTimeMillis() - t);

        // //
/*
        boolean enabled = false;
        if (enabled) {
            // Set the paths
            String imagePath = "D:/workspaces/workspace/art_intel/src/main/java/art_intel/mfg_busindex_11_25_14_2.gif";
            String resourcesFolderPath = "./Aspose.OCR.Resources.zip";

            // Create an instance of OcrEngine
            OcrEngine ocr = new OcrEngine();
            // Set Resources for OcrEngine
            ocr.setResource(new FileInputStream(resourcesFolderPath));

            // Set image file
            ocr.setImage(ImageStream.fromFile(imagePath));

            // Add language
            ILanguage language = Language.load("english");
            ocr.getLanguages().addLanguage(language);

            // Perform OCR and get extracted text
            try {
                if (ocr.process()) {
                    System.out.println("\ranswer -> " + ocr.getText());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/
    }

}
