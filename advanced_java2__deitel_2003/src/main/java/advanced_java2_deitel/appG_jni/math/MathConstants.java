package advanced_java2_deitel.appG_jni.math;

// MathConstants.java
// Contains static member variables
// and methods for native retrieval.

// container class containing static math constants
public class MathConstants {

    public static final double PI = Math.PI;
    public double E = Math.E;

    public static double getPI() {
        return PI;
    }
}
