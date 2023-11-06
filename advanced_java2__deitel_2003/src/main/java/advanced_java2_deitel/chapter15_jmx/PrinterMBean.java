package advanced_java2_deitel.chapter15_jmx;

// Fig. 24.3: PrinterMBean.java
// This interface specifies the methods that will be implemented
// by Printer, which will function as an MBean.
public interface PrinterMBean {

    // is it printing?
    public Boolean isPrinting();

    // is it online?
    public Boolean isOnline();

    // is paper jammed?
    public Boolean isPaperJam();

    // returns paper amount in tray
    public Integer getPaperTray();

    // returns ink level in toner cartridge
    public Integer getToner();

    // returns ID of print job that is currently printing
    public String getCurrentPrintJob();

    // returns array of all queued up print jobs
    public String[] getPendingPrintJobs();

    // sets availability status of printer
    public void setOnline(Boolean online);

    // fills up paper tray again with paper
    public void replenishPaperTray();

    // cancel pending print jobs
    public void cancelPendingPrintJobs();

    // start printing process
    public void startPrinting();

}
