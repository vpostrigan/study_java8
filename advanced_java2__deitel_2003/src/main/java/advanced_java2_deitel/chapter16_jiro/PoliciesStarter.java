package advanced_java2_deitel.chapter16_jiro;

// Java core package

import java.rmi.*;

// Jiro packages
//import javax.fma.common.*;

// Deitel packages
//import com.deitel.advjhtp1.jiro.DynamicService.policy.*;

public class PoliciesStarter {
/*
    // PoliciesStarter constructor
    public PoliciesStarter(String domain) {

        OutofPaperPolicy paperPolicyProxy;
        LowTonerPolicy tonerPolicyProxy;

        // set security manager
        if (System.getSecurityManager() == null)
            System.setSecurityManager(new RMISecurityManager());

        // obtain station address
        StationAddress stationAddress = new StationAddress(domain, null, null, null, null, null, null, null);

        // get the proxies of management policies
        try {
            paperPolicyProxy = new OutofPaperPolicyImplProxy(stationAddress);
            tonerPolicyProxy = new LowTonerPolicyImplProxy(stationAddress);
        } catch (RemoteException exception) { // handle exception getting proxies and starting policies
            exception.printStackTrace();
        }
    }

    // method main
    public static void main(String args[]) {
        String domain = "";

        // get the domain name
        if (args.length != 1) {
            System.out.println("Usage: PoliciesStarter Domain");
            System.exit(1);
        } else
            domain = args[0];

        PoliciesStarter policiesStarter = new PoliciesStarter(domain);
    }
*/
}