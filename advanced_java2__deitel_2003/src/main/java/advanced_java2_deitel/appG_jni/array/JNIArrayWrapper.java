package advanced_java2_deitel.appG_jni.array;

// JNIArrayWrapper.java
// Allows access to native methods

public class JNIArrayWrapper {

    // load library JNIArrayLibrary into JVM
    static {
        System.loadLibrary("JNIArrayLibrary");
    }

    // native c++ method
    public native int[] newRandomArray(int size);

    // Java method that uses native method
    public void outputRandomNumbers(int amount) {
        int randomNumbers[] = newRandomArray(amount);

        for (int i = 0; i < amount; i++)
            System.out.println(randomNumbers[i] + " random number " + i);
    }

}
