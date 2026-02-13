import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

/* Deserializer.java
* Deserialise a file
* In our case, it allows the server to recover history if it exists
 */

public class Deserializer {
	public static ChatServiceImpl deserialize(String file_path) throws IOException{
        try (FileInputStream file_in = new FileInputStream(file_path);
            ObjectInputStream object_in = new ObjectInputStream(file_in);
        ) {
            return (ChatServiceImpl) object_in.readObject();
        } catch (ClassNotFoundException cnf_exception) {
            System.err.printf("Deserialize failed due to %s\n",cnf_exception.getMessage());
            return null;
        }
    }
}