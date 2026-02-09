import java.rmi.registry.*;
import java.rmi.server.UnicastRemoteObject;

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
	String res = h.join(client_stub);
	System.out.println(res);

	} catch (Exception e)  {
//		System.err.println("Error on client: " + e);
		e.printStackTrace();
	}
  }
}
