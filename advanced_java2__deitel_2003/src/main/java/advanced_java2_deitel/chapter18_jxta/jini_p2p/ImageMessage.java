package advanced_java2_deitel.chapter18_jxta.jini_p2p;

// Java extension packages
import javax.swing.ImageIcon;

public class ImageMessage extends Message {

    private ImageIcon image;

    public ImageMessage(String messageSenderName, String imageFileName) {
        super(messageSenderName, "image: " + imageFileName);
        image = new ImageIcon(imageFileName);
    }

    public ImageIcon getImage() {
        return image;
    }
}