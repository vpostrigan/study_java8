package advanced_java2_deitel.appG_jni.tint;

// ImageSizeException.java is used when the image to be processed is too large.

public class ImageSizeException extends Exception {

    // calls parent constructor
    public ImageSizeException(String message) {
        super(message);
    }
}
