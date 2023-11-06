package advanced_java2_deitel.appG_jni.tint;

// JNITintWrapper.java loads library JNITint and wraps the native tintImage function.

public class JNITintWrapper {
   
   // load library JNITint into JVM
   static {
      System.loadLibrary( "JNITint" );
   }
   
   // tints the image based on tint values
   public native int[] tintImage(
      int[] image, int length, int rTint, int gTint, int bTint )
      throws ImageSizeException;
}
