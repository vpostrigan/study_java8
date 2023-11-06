package advanced_java2_deitel.appG_jni.tint;

// PixelTintException.java extends exception, called when a pixel cannot be fully tinted

public class PixelTintException extends Exception {

    public PixelTintException(String message) {
        super(message);
    }

}
