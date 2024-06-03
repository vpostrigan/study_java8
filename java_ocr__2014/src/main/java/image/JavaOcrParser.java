package image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import net.sourceforge.javaocr.ocrPlugins.mseOCR.OCRScanner;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImage;
import net.sourceforge.javaocr.ocrPlugins.mseOCR.TrainingImageLoader;
import net.sourceforge.javaocr.scanner.PixelImage;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class JavaOcrParser {

    private static class Train {
        HashMap<Character, ArrayList<TrainingImage>> trainingImages = new HashMap<Character, ArrayList<TrainingImage>>();
        private final PixelImage pixelImage;

        public Train(String imageFilename) throws IOException {
            Image image = ImageIO.read(new File(imageFilename));

            pixelImage = new PixelImage(image);
            // pixelImage.toGrayScale(true);
            // pixelImage.filter();
        }

        public Map<int[], Character> results = new HashMap<int[], Character>();

        {
            results.put(new int[]{0, 0, 9, 11, 0, 11}, 'O');
            results.put(new int[]{10, 0, 10 + 7, 0 + 11, 0, 11}, 'c');
            results.put(new int[]{17, 0, 17 + 4, 0 + 11, 0, 11}, 't');
            results.put(new int[]{21, 0, 21 + 7, 0 + 11, 0, 11}, 'o');
            results.put(new int[]{29, 0, 29 + 7, 0 + 11, 0, 11}, 'b');
            results.put(new int[]{36, 0, 36 + 7, 0 + 11, 0, 11}, 'e');
            results.put(new int[]{44, 0, 44 + 5, 0 + 11, 0, 11}, 'r');

            results.put(new int[]{97, 0, 97 + 6, 0 + 11, 0, 11}, 'a');
            results.put(new int[]{104, 0, 104 + 4, 0 + 11, 0, 11}, 't');

            // results.put(new int[] { 93, 20, 105, 46, 12, 45 }, ' ');
            // results.put(new int[] { 30, 6, 36, 14, 3, 16 }, 's');
            // results.put(new int[] { 37, 6, 42, 14, 3, 16 }, 'e');

            // results.put(new int[] { 153, 33, 164, 47 }, 's');
            // results.put(new int[] { 166, 32, 178, 47 }, 'e');
            // results.put(new int[] { 181, 32, 193, 47 }, 'a');
        }

        public void train() {
            for (Map.Entry<int[], Character> result : results.entrySet()) {
                int[] cords = result.getKey();
                t(result.getValue(), cords[0], cords[1], cords[2], cords[3], cords[4], cords[5]);
            }
        }

        private void t(Character symbol, int x1, int y1, int x2, int y2, int rowY1, int rowY2) {
            int w = x2 - x1;
            int h = y2 - y1;
            int[] pixels = new int[w * h];
            for (int y = y1, destY = 0; y < y2; y++, destY++) {
                System.arraycopy(pixelImage.pixels, (y * pixelImage.width) + x1, pixels, destY * w, w);
            }

            Image img = getImageFromArray(pixelImage.pixels, w, h);
            // File imageFile = new File("capturedImage.png");
            // ImageIO.write(img, "png", imageFile);

            {
                BufferedImage pixelImage = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                pixelImage.setRGB(0, 0, w, h, pixels, 0, w);

                try {
                    ImageIO.write(pixelImage, "png", new File("out.png"));
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            {
                BufferedImage bimg = null;
                PixelGrabber pg = new PixelGrabber(img, 0, 0, w, h, pixels, 0, w);
                try {
                    pg.grabPixels();
                } catch (InterruptedException ie) {
                    ie.printStackTrace();
                }

                bimg = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);
                bimg.setRGB(0, 0, w, h, pixels, 0, w);

                // Encode as a JPEG
                FileOutputStream fos;
                try {
                    fos = new FileOutputStream("out.jpg");
                    JPEGImageEncoder jpeg = JPEGCodec.createJPEGEncoder(fos);
                    jpeg.encode(bimg);
                    fos.close();
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            for (int y = 0, idx = 0; y < h; y++) {
                for (int x = 0; x < w; x++, idx++) {
                    // System.out.print((pixels[idx] > 0) ? ' ' : '*');
                    System.out.print((pixels[idx] == 253) ? ' ' : '*');
                }
                System.out.println();
            }
            System.out.println();

            ArrayList<TrainingImage> al = trainingImages.get(symbol);
            if (al == null) {
                al = new ArrayList<TrainingImage>();
                trainingImages.put(symbol, al);
            }
            al.add(new TrainingImage(pixels, w, h, y1 - rowY1, rowY2 - y2));
        }

        public static Image getImageFromArray(int[] pixels, int width, int height) {
            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            WritableRaster raster = (WritableRaster) image.getData();
            raster.setPixels(0, 0, width, height, pixels);
            return image;
        }

    }

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

            Train train = new Train(
                    "D:\\workspace_study\\study_java8\\java_ocr__2014\\FixedGraph.aspx_train.png");
            train.train();
            scanner.addTrainingImages(train.trainingImages);

            // //

            TrainingImageLoader loader = new TrainingImageLoader();
            HashMap<Character, ArrayList<TrainingImage>> trainingImageMap = new HashMap<>();

            // loader.load(trainingImageDir + "FixedGraph.aspx2.png", new CharacterRange('-', '9'), trainingImageMap);

            if (debug) {
                System.err.println("ascii.png");
            }
            // loader.load(trainingImageDir + "ascii.png", new CharacterRange('!', '~'), trainingImageMap);
            if (debug) {
                System.err.println("hpljPica.jpg");
            }
            // loader.load(trainingImageDir + "hpljPica.jpg", new CharacterRange('!', '~'), trainingImageMap);
            if (debug) {
                System.err.println("digits.jpg");
            }
            // loader.load(trainingImageDir + "digits.jpg", new CharacterRange('0', '9'), trainingImageMap);

            // loader.load(trainingImageDir + "FixedGraph_t5_1.png", new CharacterRange('-', '9'), trainingImageMap);
            // loader.load(trainingImageDir + "FixedGraph_t5_2.png", new CharacterRange('A', 'Z'), trainingImageMap);
            // loader.load(trainingImageDir + "FixedGraph_t5_3.png", new CharacterRange('A', 'Z'), trainingImageMap);
            // loader.load(trainingImageDir + "FixedGraph_t5_4.png", new CharacterRange('A', 'Z'), trainingImageMap);
            // loader.load(trainingImageDir + "t1.gif", new CharacterRange('A', 'D'), trainingImageMap);
            // loader.load(trainingImageDir + "FixedGraph_t.png", new CharacterRange('a', 'f'), trainingImageMap);

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
        loadTrainingImages(scanner,
                "D:\\workspace_study\\study_java8\\java_ocr__2014");

        Image image = ImageIO.read(new File(
                "D:\\workspace_study\\study_java8\\java_ocr__2014\\FixedGraph.aspx.jpeg"));

        PixelImage pixelImage = new PixelImage(image);
        // pixelImage.toGrayScale(true);
        // pixelImage.filter();

        long t = System.currentTimeMillis();

        System.out.println("" + ":");
        String text = scanner.scan(image, 0, 0, 0, 0, null);
        System.out.println("[" + text + "]");
        System.out.println((System.currentTimeMillis() - t) + " ms");
    }

}
