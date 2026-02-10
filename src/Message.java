/*
The datatype representing messages accross Server and Clients.
@parameter content: The String containing the actual message
@parameter timestamp_sent: The time of sending
@parameter sender_id: The id the sender received when initializing
@parameter history: Wether it is a previously recorded or new message
*/

import java.io.Serializable;

public class Message implements Serializable{
    public final String content;
    public final String timestamp_sent;
    public final int sender_id;

    Message(String content, String timestamp_sent, int sender){
        this.content = content;
        this.timestamp_sent = timestamp_sent;
        this.sender_id = sender;
    }
}
