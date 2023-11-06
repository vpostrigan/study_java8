package advanced_java2_deitel.appG_jni.pi;

// JNIPIMain.java
// Loads the native library, creates a new instance of the
// Java wrapper class and uses native code to call getPI.

public class JNIPIMain {

    public static void main(String args[]) {
        JNIPIWrapper wrapper = new JNIPIWrapper();

        // Native code retrieves PI from instance of PIContainer
        double pi = wrapper.getPI(new PIContainer());
        System.out.println("PI is " + pi);

        // Native code retrieves PI, creates its own PIContainer
        double pi2 = wrapper.getPI();
        System.out.println("PI2 is " + pi2);
    }
}
