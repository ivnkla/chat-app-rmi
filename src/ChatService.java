import java.rmi.*;

public interface ChatService extends Remote {
	public String join(ClientEndpoint client)  throws RemoteException;
}
