package advanced_java2_deitel.appG_jni.pi;

// JNIPIWrapper.java
// Allows for access to native function

public class JNIPIWrapper {

    // load JNIPILibrary into JVM
    static {
        System.loadLibrary("JNIPILibrary");
    }

    // Native c++ methods
    public native double getPI(PIContainer container);

    public native double getPI();

}
