package advanced_java2_deitel.chapter13_rmi.messenger;

// Java core packages

import java.net.*;
import java.rmi.*;
import java.rmi.activation.*;
import java.rmi.registry.*;
import java.util.*;

public class ChatServerImpl extends Activatable implements ChatServer, StoppableChatServer {

    // Set of ChatClient references
    private Set clients = new HashSet();

    // server object's name
    private String serverObjectName;

    // ChatServerImpl constructor
    public ChatServerImpl(ActivationID id, MarshalledObject data) throws RemoteException {
        super(id, 0); // register activatable object and export on anonymous port
    }

    // register ChatServerImpl object with RMI registry.
    public void register(String rmiName) throws RemoteException, IllegalArgumentException, MalformedURLException {
        // ensure registration name was provided
        if (rmiName == null)
            throw new IllegalArgumentException("Registration name cannot be null");

        serverObjectName = rmiName;

        // bind ChatServerImpl object to RMI registry
        try {
            // create RMI registry
            System.out.println("Creating registry ...");
            Registry registry = LocateRegistry.createRegistry(1099);

            // bind RMI object to default RMI registry
            System.out.println("Binding server to registry ...");
            registry.rebind(serverObjectName, this);
        } catch (RemoteException remoteException) {
            // if registry already exists, bind to existing registry
            System.err.println("Registry already exists. Binding to existing registry ...");
            Naming.rebind(serverObjectName, this);
        }

        System.out.println("Server bound to registry");
    }

    // register new ChatClient with ChatServer
    public void registerClient(ChatClient client) throws RemoteException {
        // add client to Set of registered clients
        synchronized (clients) {
            clients.add(client);
        }

        System.out.println("Registered Client: " + client);
    }

    // unregister client with ChatServer
    public void unregisterClient(ChatClient client) throws RemoteException {
        // remove client from Set of registered clients
        synchronized (clients) {
            clients.remove(client);
        }

        System.out.println("Unregistered Client: " + client);
    }

    // post new message to chat server
    public void postMessage(ChatMessage message) throws RemoteException {
        Iterator iterator;

        // get Iterator for Set of registered clients
        synchronized (clients) {
            iterator = new HashSet(clients).iterator();
        }

        // send message to every ChatClient
        while (iterator.hasNext()) {

            // attempt to send message to client
            ChatClient client = (ChatClient) iterator.next();
            try {
                client.deliverMessage(message);
            } catch (Exception exception) {
                // unregister client if exception is thrown
                System.err.println("Unregistering absent client.");
                unregisterClient(client);
            }
        }
    }

    // notify each client that server is shutting down and terminate server application
    public void stopServer() throws RemoteException {
        System.out.println("Terminating server ...");

        Iterator iterator = null;

        // get Iterator for Set of registered clients
        synchronized (clients) {
            iterator = new HashSet(clients).iterator();
        }

        // send message to every ChatClient
        while (iterator.hasNext()) {
            ChatClient client = (ChatClient) iterator.next();
            client.serverStopping();
        }

        // create Thread to terminate application after
        // stopServer method returns to caller
        // sleep for 5 seconds, print message and terminate
        Thread terminator = new Thread(
                () -> {
                    // sleep
                    try {
                        Thread.sleep(5000);
                    }

                    // ignore InterruptedExceptions
                    catch (InterruptedException exception) {
                    }

                    System.err.println("Server terminated");
                    System.exit(0);
                }
        );

        terminator.start(); // start termination thread
    }

}