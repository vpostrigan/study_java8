package advanced_java2_deitel.appG_jni.math;

// JNIStaticWrapper.java
// Allows access to native printStaticMembers function

public class JNIStaticWrapper {

    // load library JNIMathLibrary into JVM
    static {
        System.loadLibrary("JNIMathLibrary");
    }

    // native C++ method
    public native void printStaticMembers(MathConstants constants);

}
