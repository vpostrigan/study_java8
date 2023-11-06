package advanced_java2_deitel.chapter18_jxta.jini_p2p;

//java core packages

import java.rmi.RemoteException;

// IMSProxy.java
// IMSProxy interface defines methods that IM services must implement
public interface IMSProxy {

    // get a RMI reference to remote peer
    public IMPeer connect(IMPeer peer) throws RemoteException;

}
