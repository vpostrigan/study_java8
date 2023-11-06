package advanced_java2_deitel.appG_jni.print;

// JNIPrintWrapper.java
// Allows access to native methods
public class JNIPrintWrapper {

    // load library JNIPrintMessage into JVM
    static {
        System.loadLibrary( "JNIPrintMessage" );
    }

    // native C++ method
    public native void printMessage( String message );

}
