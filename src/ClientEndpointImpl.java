import java.rmi.RemoteException;
import java.util.Date;
import java.util.List;

/* ClientEndpointImpl.java
 * Actually implements the interface.
 * It actually behave like a server
 */
public class ClientEndpointImpl implements ClientEndpoint {

    //TODO add debug level

    @Override
    public void sendMessage(Message msg) throws RemoteException {
        System.out.printf("[%s] Received new Message from %5d: %s\n", new Date().toString(),msg.sender_id, msg.content);
    }

    @Override
    public void history(List<Message> messages, String welcome_message) {
        System.out.printf(welcome_message);
        System.out.println("--- Begin of History ---");
        for(Message msg: messages) {
            System.out.printf("[%s] %5d sent: %s\n", msg.timestamp_sent, msg.sender_id, msg.content);
        }
        System.out.println("--- End of History ---");
    }
}