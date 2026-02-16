package common;

import java.rmi.*;

/* src.common.ChatService.java
 * Remote methods that the server propose for any client
 */
public interface ChatService extends Remote {

    // When a client join the server made available in the rmi
    // it passes its endpoint by reference. So that the server
    // is able to communicate with the latter.
	public int join(ClientEndpoint client)  throws RemoteException;

    // Allows a client to broadcast a message over the server.
	public void sendMessage(int id, String msg) throws RemoteException;

    // Let the server know when client disconnects
	public int leave(int id) throws RemoteException;
}
