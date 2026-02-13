import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/* ClientEndpoint.java
 * Provides interface for the server to communicate with clients.
 */

public interface ClientEndpoint extends Remote{
    public void sendMessage(Message msg) throws RemoteException;
    public void history(List<Message> messages, String welcome_message) throws RemoteException;
}
