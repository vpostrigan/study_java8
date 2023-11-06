package advanced_java2_deitel.chapter18_jxta.multicast_p2p;

import java.net.MulticastSocket;
import java.net.*;
import java.rmi.*;
import java.rmi.registry.*;
import java.io.*;

// MulticastSendingThread.java
// Sends a multicast periodically containing a remote reference to the IMServiceImpl object
public class MulticastSendingThread extends Thread implements IMConstants {

    // InetAddress of group for messages
    private InetAddress multicastNetAddress;

    // MulticastSocket for multicasting messages
    private MulticastSocket multicastSocket;

    // Datagram packet to be reused
    private DatagramPacket multicastPacket;

    // stub of local peer
    private IMService peerStub;

    // flag for terminating MulticastSendingThread
    private boolean keepSending = true;

    private String userName;

    public MulticastSendingThread(String myName) {
        // invoke superclass constructor to name Thread
        super("MulticastSendingThread");

        userName = myName;

        // create a registry on default port 1099
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            peerStub = new IMServiceImpl(userName);
            registry.rebind(BINDING_NAME, peerStub);
        } catch (RemoteException remoteException) {
            remoteException.printStackTrace();
        }

        try {
            // create MulticastSocket for sending messages
            multicastSocket = new MulticastSocket(MULTICAST_SENDING_PORT);

            // set TTL for Multicast Socket
            multicastSocket.setTimeToLive(MULTICAST_TTL);

            // use InetAddress reserved for multicast group
            multicastNetAddress = InetAddress.getByName(MULTICAST_ADDRESS);

            // create greeting packet
            String greeting = new String(HELLO_HEADER + userName);

            multicastPacket = new DatagramPacket(
                    greeting.getBytes(), greeting.getBytes().length,
                    multicastNetAddress, MULTICAST_LISTENING_PORT);
        } catch (java.net.UnknownHostException unknownHostException) {
            // MULTICAST_ADDRESS IS UNKNOWN HOST
            System.err.println("MULTICAST_ADDRESS is unknown");
            unknownHostException.printStackTrace();
        } catch (Exception exception) { // any other exception
            exception.printStackTrace();
        }
    }

    // deliver greeting message to peers
    @Override
    public void run() {
        while (keepSending) {
            // deliver greeting
            try {
                // send greeting packet
                multicastSocket.send(multicastPacket);

                Thread.sleep(MULTICAST_INTERVAL);
            } catch (IOException e) { // handle exception delivering message
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        multicastSocket.close();
    }

    // send goodbye message
    public void logout() {
        String goodbye = new String(GOODBYE_HEADER + userName);
        System.out.println(goodbye);
        multicastPacket = new DatagramPacket(
                goodbye.getBytes(), goodbye.getBytes().length,
                multicastNetAddress, MULTICAST_LISTENING_PORT);

        try {
            multicastSocket.send(multicastPacket);

            Naming.unbind(BINDING_NAME);
        } catch (IOException e) { // error multicasting
            System.err.println("Couldn't Say Goodbye");
            e.printStackTrace();
        } catch (Exception e) { // unbinding may cause many possible exceptions
            e.printStackTrace();
        }

        keepSending = false;
    }

}
