package advanced_java2_deitel.chapter16_jiro;

// Java core package

import java.rmi.*;
import java.util.*;

// Jini core package
//import net.jini.core.event.*;

public interface LowTonerPolicy /*extends RemoteEventListener*/ {

    public void stopPolicy() throws RemoteException;

}