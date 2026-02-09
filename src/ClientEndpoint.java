import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ClientEndpoint extends Remote{
    public void sendMessage(String msg) throws RemoteException; 
}
