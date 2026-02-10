public class Message {
    public final String content;
    public final String timestamp;
    public final int sender;

    Message(String content, String timestamp, int sender){
        this.content = content;
        this.timestamp = timestamp;
        this.sender = sender;
    }
}
