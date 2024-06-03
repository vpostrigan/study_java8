package image;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import net.sourceforge.javaocr.ocrPlugins.mseOCR.CharacterRange;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImage;
import net.sourceforge.javaocr.scanner.DocumentScanner;
import net.sourceforge.javaocr.scanner.DocumentScannerListenerAdaptor;
import net.sourceforge.javaocr.scanner.PixelImage;

public class JavaOcr_TrainingImageLoader2 extends DocumentScannerListenerAdaptor {
    private static final Logger LOG = Logger.getLogger(JavaOcr_TrainingImageLoader2.class.getName());

    private int charValue = 0;
    private HashMap<Character, ArrayList<TrainingImage>> dest;

    private DocumentScanner documentScanner = new DocumentScanner();

    public Map<char[], Character> results = new HashMap<char[], Character>();

    {
        results.put(new char[]{98, 28, 111, 46}, 'T');
        results.put(new char[]{114, 29, 126, 46}, 'h');
        results.put(new char[]{129, 33, 142, 47}, 'e');

        results.put(new char[]{153, 33, 164, 47}, 's');
        results.put(new char[]{166, 32, 178, 47}, 'e');
        results.put(new char[]{181, 32, 193, 47}, 'a');
    }

    /**
     * @return The <code>DocumentScanner</code> instance that is used to load the training images. This is useful if the
     *         caller wants to adjust some of the scanner's parameters.
     */
    public DocumentScanner getDocumentScanner() {
        return documentScanner;
    }

    /**
     * Load an image containing training characters, break it up into characters, and build a training set. Each entry in the
     * training set (a <code>Map</code>) has a key which is a <code>Character</code> object whose value is the character
     * code. Each corresponding value in the <code>Map</code> is an <code>ArrayList</code> of one or more
     * <code>TrainingImage</code> objects which contain images of the character represented in the key.
     *
     * @param imageFilename
     *            The filename of the image to load.
     * @param charRange
     *            A <code>CharacterRange</code> object representing the range of characters which is contained in this image.
     * @param dest
     *            A <code>Map</code> which gets loaded with the training data. Multiple calls to this method may be made with
     *            the same <code>Map</code> to populate it with the data from several training images.
     * @throws IOException
     */
    public void load(String imageFilename, CharacterRange charRange, HashMap<Character, ArrayList<TrainingImage>> dest)
            throws IOException {

        Image image = ImageIO.read(new File(imageFilename));

        if (image == null) {
            throw new IOException("Cannot find training image file: " + imageFilename);
        }
        load(image, charRange, dest, imageFilename);
    }

    public void load(Image image, CharacterRange charRange,
                     HashMap<Character, ArrayList<TrainingImage>> dest,
                     String imageFilename) throws IOException {

        PixelImage pixelImage = new PixelImage(image);
        pixelImage.toGrayScale(true);
        pixelImage.filter();

        this.dest = dest;
        documentScanner.scan(pixelImage, this, 0, 0, 0, 0);
    }

    @Override
    public void processChar(PixelImage pixelImage, int x1, int y1, int x2, int y2, int rowY1, int rowY2) {
        boolean debug = true;

        Character symbol = null;
        for (Map.Entry<char[], Character> result : results.entrySet()) {
            char[] c = result.getKey();
            if (c[0] == x1 && c[1] == y1 && c[2] == x2 && c[3] == y2) {
                symbol = result.getValue();
            }
        }
        if (symbol == null) {

            if (debug) {
                System.out.println("TrainingImageLoader.processChar: \'" + (char) charValue + "\' " + x1 + "," + y1 + "-"
                        + x2 + "," + y2);
            }

            return;
        }

        // //

        int w = x2 - x1;
        int h = y2 - y1;
        int[] pixels = new int[w * h];
        for (int y = y1, destY = 0; y < y2; y++, destY++) {
            System.arraycopy(pixelImage.pixels, (y * pixelImage.width) + x1, pixels, destY * w, w);
        }
        if (debug) {
            for (int y = 0, idx = 0; y < h; y++) {
                for (int x = 0; x < w; x++, idx++) {
                    System.out.print((pixels[idx] > 0) ? ' ' : '*');
                }
                System.out.println();
            }
            System.out.println();
        }

        ArrayList<TrainingImage> al = dest.get(symbol);
        if (al == null) {
            al = new ArrayList<>();
            dest.put(symbol, al);
        }
        al.add(new TrainingImage(pixels, w, h, y1 - rowY1, rowY2 - y2));
    }

}
