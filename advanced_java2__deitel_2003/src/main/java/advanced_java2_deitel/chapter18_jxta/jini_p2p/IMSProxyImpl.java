package advanced_java2_deitel.chapter18_jxta.jini_p2p;

// Java core packages

import java.io.Serializable;

// Java extension packages
import java.rmi.*;

// IMSProxyImpl.java
// IMSProxyImpl is a proxy for the IM Jini service.
public class IMSProxyImpl implements IMSProxy, Serializable {
    // stores link to the service using RMI
    private IMService serviceInterface;

    public IMSProxyImpl(IMService service) {
        serviceInterface = service;
    }

    // get a RMI reference to remote peer
    public IMPeer connect(IMPeer peer) throws RemoteException {
        // get IMPeer instance from service
        try {
            return serviceInterface.connect(peer);
        } catch (RemoteException remoteException) { // handle exception communicating with service
            remoteException.printStackTrace();
        }
        return null;
    }

}

