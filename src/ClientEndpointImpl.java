import java.rmi.RemoteException;

public class ClientEndpointImpl implements ClientEndpoint {

    @Override
    public void sendMessage(String msg) throws RemoteException {
        System.out.print(msg);
    }

    
}