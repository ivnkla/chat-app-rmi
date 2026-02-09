
import java.rmi.*;

public  class ChatServiceImpl implements ChatService {

	private String message;
 
	public ChatServiceImpl(String s) {
		message = s ;
	}

	public String join(ClientEndpoint client_stub) throws RemoteException {
		client_stub.sendMessage("Welcome");
		return message;		
	}

}

