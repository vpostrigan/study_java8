package advanced_java2_deitel.chapter13_rmi.messenger;

// Java core packages

import java.rmi.*;
import java.rmi.activation.*;
import java.util.*;

public class ChatServerAdministrator {

    // set up activatable server object
    private static void startServer(String policy, String codebase) throws Exception {
        // set up RMI security manager
        System.setSecurityManager(new RMISecurityManager());

        // set security policy for ActivatableGroup JVM
        Properties properties = new Properties();
        properties.put("java.security.policy", policy);

        // create ActivationGroupDesc for activatable object
        ActivationGroupDesc groupDesc = new ActivationGroupDesc(properties, null);

        // register activation group with RMI activation system
        ActivationGroupID groupID = ActivationGroup.getSystem().registerGroup(groupDesc);

        // create activation group
        ActivationGroup.createGroup(groupID, groupDesc, 0);

        // activation description for ChatServerImpl
        ActivationDesc description = new ActivationDesc(
                "com.deitel.messenger.rmi.server.ChatServerImpl",
                codebase, null);

        // register description with rmid
        ChatServer server = (ChatServer) Activatable.register(description);
        System.out.println("Obtained ChatServerImpl stub");

        // bind ChatServer in registry
        Naming.rebind("ChatServer", server);
        System.out.println("Bound object to registry");

        // terminate setup program
        System.exit(0);
    }

    // terminate server
    private static void terminateServer(String hostname) throws Exception {
        // lookup ChatServer in RMI registry
        System.out.println("Locating server ...");
        StoppableChatServer server = (StoppableChatServer)
                Naming.lookup("rmi://" + hostname + "/ChatServer");

        // terminate server
        System.out.println("Stopping server ...");
        server.stopServer();

        // remove ChatServer from RMI registry
        System.out.println("Server stopped");
        Naming.unbind("rmi://" + hostname + "/ChatServer");
    }

    // launch ChatServerAdministrator application
    public static void main(String args[]) throws Exception {
        // check for stop server argument
        if (args.length == 2) {
            if (args[0].equals("stop"))
                terminateServer(args[1]);
            else
                printUsageInstructions();
        } else if (args.length == 3) { // check for start server argument
            // start server with user-provided policy, codebase and Registry hostname
            if (args[0].equals("start"))
                startServer(args[1], args[2]);
            else
                printUsageInstructions();
        }
        // wrong number of arguments provided, so print instructions
        else printUsageInstructions();
    }

    // print instructions for running ChatServerAdministrator
    private static void printUsageInstructions() {
        System.err.println("\nUsage:\n" +
                "\tjava com.deitel.messenger.rmi.server." +
                "ChatServerAdministrator start <policy> <codebase>\n" +
                "\tjava com.deitel.messenger.rmi.server." +
                "ChatServerAdministrator stop <registry hostname>");
    }

}
