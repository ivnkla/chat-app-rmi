import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ClientEndpoint extends Remote{
    public void sendMessage(Message msg) throws RemoteException;
    public void history(List<Message> messages) throws RemoteException;
}
