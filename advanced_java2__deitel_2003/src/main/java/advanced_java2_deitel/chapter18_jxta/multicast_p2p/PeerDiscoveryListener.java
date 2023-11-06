package advanced_java2_deitel.chapter18_jxta.multicast_p2p;

// PeerDiscoveryListener.java
// Interface for listening to peerAdded or peerRemoved events
public interface PeerDiscoveryListener {

    // add peer with given name and ip address
    public void peerAdded(String name, String peerStubAddress);

    // remove peer with given name
    public void peerRemoved(String name);

}
