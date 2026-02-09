import java.rmi.*;

public interface ChatService extends Remote {
	public int join(ClientEndpoint client)  throws RemoteException;
	public void sendMessage(int id, String msg) throws RemoteException;
	public int leave(int id) throws RemoteException;
}
