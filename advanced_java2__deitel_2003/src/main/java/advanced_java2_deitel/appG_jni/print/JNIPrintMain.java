package advanced_java2_deitel.appG_jni.print;

// JNIPrintMain.java
// Loads the native library, creates a new instance of the
// Java wrapper class, and calls the native methods.
public class JNIPrintMain {

    // uses JNI to print a message
    public static void main(String args[]) {
        JNIPrintWrapper wrapper = new JNIPrintWrapper();

        // call to native methods through JNIWrapper
        wrapper.printMessage("Hello World\n");
    }

}