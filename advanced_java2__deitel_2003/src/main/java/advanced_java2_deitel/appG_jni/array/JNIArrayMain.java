package advanced_java2_deitel.appG_jni.array;

// JNIArrayMain.java
// Loads the native library, creates a new instance of the
// Java wrapper class, and calls for it to print 10 random numbers.

public class JNIArrayMain {

    // instantiate JNIArrayWrapper and call outputRandomNumbers
    public static void main(String args[]) {
        JNIArrayWrapper wrapper = new JNIArrayWrapper();

        // outputs ten random numbers
        wrapper.outputRandomNumbers(10);
    }

}
