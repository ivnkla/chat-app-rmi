
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.rmi.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

/* ChatServiceImpl.java
 * The actual implementation of ChatService, remote
 * methods callable by clients. Btw client has no
 * idea of the implementation
 */
public class ChatServiceImpl implements ChatService, Serializable{

	//Using a Dictornary with only atomic functions to guarantee threadsafty
	private ConcurrentHashMap<Integer,ClientEndpoint> clients = new ConcurrentHashMap<Integer,ClientEndpoint>();
	private List<Message> messages = Collections.synchronizedList(new ArrayList<>());
	private Random random = new Random();
	private Date date = new Date();
	private final int range = 20000;
	private final String file_path;

	public ChatServiceImpl(String file_path){
		this.file_path = file_path;
	}

	private final String welcome_message = """
	Welcome to the ChatService Application! 
	> Your Client ID is %d.
	> To send messages enter a message and press [Enter]. 
	> To end the programm press [Enter] without typing a message.
	""";

	//get a random free ID for a new client
	private int createID(){
		if(clients.size() > range/2) {
			throw new RuntimeException("HashMap too full");
		}
		int newID;
		do {
			newID = random.nextInt(range);
		} while (clients.get(newID) != null);
		return newID;
	}

	//Interface to join the Chatplatform
	// !! has to be atomic
	public synchronized int join(ClientEndpoint client_stub) throws RemoteException {
		int newID = createID();
		System.out.println(String.format("[%s] Initalizing new client %d", date.toString(), newID));
		client_stub.history(messages, String.format(welcome_message, newID));
		clients.put(newID,client_stub);
		//TODO ADD history callback
		
		return newID;
	}

	@Override
	public synchronized void sendMessage(int client_id, String msg){
		System.out.println(String.format("[%s] New Message from %d at : %s", date.toString(), client_id, msg));
		Message new_message = new Message(msg,date.toString(),client_id);
		messages.add(new_message);
		serialize();
		
		//TODO check for threadsafety
		for (Map.Entry<Integer, ClientEndpoint> client : clients.entrySet()) {
			if (client_id != client.getKey()){
				try {
					client.getValue().sendMessage(new_message);
				} catch (ConnectException c_exception) {
					System.out.printf("Client %d is unreachable => removing %d from client list\n", client.getKey(), client.getKey());
					//TODO is here a synchronization risk?
					clients.remove(client.getKey());
				} catch (RemoteException remoteException) {
					System.err.printf("Unexpected Remote Exception in ChatServiceImpl: %s\n" ,remoteException);
				}
		}
			
		}
	}

	//!! has to be atomic		
	@Override
	public synchronized int leave(int id) throws RemoteException {
		System.out.println(String.format("[%s] Removing client %d", date.toString(), id));
		clients.remove(id);
		return id;
	}

	private void serialize(){
		try (FileOutputStream file_out = new FileOutputStream(file_path);
			ObjectOutputStream object_out = new ObjectOutputStream(file_out);
		){
			object_out.writeObject(this);	
		} catch (IOException io_exception) {
			System.err.printf("Serialize failed due to %s\n",io_exception);
	}
	}



	}