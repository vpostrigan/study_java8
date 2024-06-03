package image;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.PixelGrabber;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

import javax.imageio.ImageIO;

public class HardcodedOcr {
    private static final PixelImage[] TRAINED = new PixelImage[1];
    static {
        byte[] image1 = IoUtil.getResource(HardcodedOcr.class, "FixedGraph.aspx.jpeg");
        PixelImage pixelImage = PixelImage.buildPixelImage(image1);

        TRAINED[0] = new PixelImage(pixelImage, 153, 17, 153 + 8, 17 + 10).setSymbolByThisImage('T');
    }

    private HardcodedOcr() {
    }

    public static String extractText(byte[] imageBytes) {
        if (imageBytes == null) {
            return "";
        }

        int size10Percent = (int) ((10.0 * imageBytes.length) / 100.0);
        imageBytes = Arrays.copyOf(imageBytes, size10Percent);

        PixelImage pixelImage = PixelImage.buildPixelImage(imageBytes);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < pixelImage.pixels.length; i++) {
            for (int k = 0; k < TRAINED.length; k++) {
                PixelImage test = TRAINED[k];
                if (pixelImage.isIntersect(i, test)) {
                    sb.append(test.symbolByThisImage);
                    break;
                }
            }
        }
        return sb.toString();
    }

    private static class PixelImage {
        public final int[] pixels;
        public final int width;
        public final int height;

        private char symbolByThisImage;

        public PixelImage(PixelImage pixelImage, int x1, int y1, int x2, int y2) {
            int w = x2 - x1;
            int h = y2 - y1;

            width = w;
            height = h;
            pixels = new int[w * h];

            for (int y = y1, destY = 0; y < y2; y++, destY++) {
                System.arraycopy(pixelImage.pixels, (y * pixelImage.width) + x1, pixels, destY * w, w);
            }

            // System.out.println(Arrays.toString(pixels));
        }

        public static PixelImage buildPixelImage(byte[] imageBytes) {
            InputStream is = new ByteArrayInputStream(imageBytes);
            try {
                BufferedImage image = ImageIO.read(is);
                PixelImage pixelImage = new PixelImage(image);
                return pixelImage;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        public PixelImage(Image image) {
            width = image.getWidth(null);
            height = image.getHeight(null);
            pixels = new int[width * height];

            PixelGrabber grabber = new PixelGrabber(image, 0, 0, width, height, pixels, 0, width);
            try {
                grabber.grabPixels();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        public boolean isIntersect(int i, PixelImage trainedSymbol) {
            if (trainedSymbol.height * (i + trainedSymbol.width) >= pixels.length) {
                return false;
            }

            for (int k = 0, kForI = 0; k < trainedSymbol.pixels.length; k++, kForI++) {
                if (trainedSymbol.pixels[k] != pixels[i + kForI]) {
                    return false;
                }

                if ((k + 1) % trainedSymbol.width == 0) {
                    i += width;
                    kForI = -1;
                }
            }

            return true;
        }

        public PixelImage setSymbolByThisImage(char symbolByThisImage) {
            this.symbolByThisImage = symbolByThisImage;
            return this;
        }
    }

}
