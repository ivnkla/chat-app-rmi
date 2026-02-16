package server;

import common.ChatService;
import common.Deserializer;

import java.rmi.server.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.rmi.registry.*;

public class ChatServer {

  public static void  main(String [] args) {

      // Recovering chat history
	  String file_path = "./serverdata";
	  ChatServiceImpl h = null;
	  try {
		h = Deserializer.deserialize(file_path);
	  } catch (FileNotFoundException fnf_exception) {
		System.out.println("No savefile found, starting with empty history");
		h = new ChatServiceImpl ("./serverdata");
	  } catch (IOException io_exception) {
		System.err.printf("Unexpected IO-Exception: %s", io_exception.getMessage());
		io_exception.printStackTrace();
		return;
      }

      // Making the server available in the rmi
	  try {
		  // Create a src.server.ChatServer remote object
	    ChatService h_stub = (ChatService) UnicastRemoteObject.exportObject(h, 0);

	    // Register the remote object in RMI registry with a given identifier
	    Registry registry = null;
	    if (args.length>0)
		    registry= LocateRegistry.getRegistry(Integer.parseInt(args[0])); 
	    else
		    registry = LocateRegistry.getRegistry();
	    registry.rebind("ChatServerService", h_stub);

	    System.out.println ("Server ready");

	  } catch (Exception e) {
		  System.err.println("Error on server :" + e) ;
		  e.printStackTrace();
	  }
  }
}
