package advanced_java2_deitel.chapter15_jmx;

// Fig. 24.4: PrinterEventListener.java
// The listener interface for printer events.
public interface PrinterEventListener {

    public void outOfPaper();

    public void lowToner();

    public void paperJam();

}
