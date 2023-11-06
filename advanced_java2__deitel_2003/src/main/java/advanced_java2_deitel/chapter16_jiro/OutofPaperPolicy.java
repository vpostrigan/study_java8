package advanced_java2_deitel.chapter16_jiro;

// Java core package

import java.rmi.*;
import java.util.*;

// Jini core package
//import net.jini.core.event.*;

// OutofPaperPolicy.java
// This class defines the interface for the dynamic service.
public interface OutofPaperPolicy /*extends RemoteEventListener*/ {

    public void stopPolicy() throws RemoteException;

}
