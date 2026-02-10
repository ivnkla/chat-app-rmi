import java.rmi.RemoteException;
import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

import java.util.Scanner; 

public class ChatClient {
  public static void main(String [] args) {
	
	try {
	  if (args.length < 2) {
	   System.out.println("Usage: java ChatClient <rmiregistry host> <rmiregistry port>");
	   return;}

	String host = args[0];
	int port = Integer.parseInt(args[1]);

	Registry registry = LocateRegistry.getRegistry(host, port); 
	ChatService h = (ChatService) registry.lookup("ChatServerService");

	ClientEndpointImpl clientEndpoint = new ClientEndpointImpl();
	ClientEndpoint client_stub = (ClientEndpoint) UnicastRemoteObject.exportObject(clientEndpoint, 0);

	// Remote method invocation	
	int id = h.join(client_stub);

	Scanner scanner = new Scanner(System.in);
	String msg;
	msg = scanner.nextLine();
	while (msg != "")  {
		h.sendMessage(id,msg);
		msg = scanner.nextLine();
	} 
	scanner.close();
	if (h.leave(id) == id) {
		System.out.println("Gracefully terminating");
	} else {
		throw new RemoteException("Leaving failed");
	}

	} catch (Exception e)  {
//		System.err.println("Error on client: " + e);
		e.printStackTrace();
	}
	System.exit(0);
  }
}
