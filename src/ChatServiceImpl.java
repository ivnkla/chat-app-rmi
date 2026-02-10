
import java.rmi.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;

public  class ChatServiceImpl implements ChatService {

	//Using a Dictornary with only atomic functions to guarantee threadsafty
	private ConcurrentHashMap<Integer,ClientEndpoint> clients = new ConcurrentHashMap<Integer,ClientEndpoint>();;
	private List<Message> messages = Collections.synchronizedList(new ArrayList<>());; 
	private Random random = new Random();
	private Date date = new Date();
	private int range = 20000;
	private String welcome_message;
	
	public ChatServiceImpl(String s) {
		welcome_message = s;
	}

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
	public int join(ClientEndpoint client_stub) throws RemoteException {
		int newID = createID();
		System.out.println(String.format("[%s] Initalizing new client %d", date.toString(), newID));
		client_stub.history(messages);
		clients.put(newID,client_stub);
		//TODO ADD history callback
		
		return newID;
	}

	@Override
	public void sendMessage(int client_id, String msg){
		System.out.println(String.format("[%s] New Message from %d at : %s", date.toString(), client_id, msg));
		Message new_message = new Message(msg,date.toString(),client_id);
		messages.add(new_message);
		//TODO check for threadsafety
		//ToDo add client identifier, probably as own object
		for (Map.Entry<Integer, ClientEndpoint> client : clients.entrySet()) {
			if (client_id != client.getKey()){
				try {
					client.getValue().sendMessage(new_message);
				} catch (RemoteException remoteException) {
					System.err.println(remoteException);
				}
		}
			
		}
	}

	//!! has to be atomic
	@Override
	public int leave(int id) throws RemoteException {
		System.out.println(String.format("[%s] Removing client %d", date.toString(), id));
		clients.remove(id);
		return id;
	}

	}

