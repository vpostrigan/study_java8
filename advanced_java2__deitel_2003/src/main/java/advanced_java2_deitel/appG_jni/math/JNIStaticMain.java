package advanced_java2_deitel.appG_jni.math;

// JNIStaticMain.java
// Loads the native library, creates a new instance of the
// Java wrapper class and calls to print the static members
// of the MathConstants class.

public class JNIStaticMain {

    public static void main(String args[]) {
        JNIStaticWrapper wrapper = new JNIStaticWrapper();

        // access static members from MathConstants
        wrapper.printStaticMembers(new MathConstants());
    }
}