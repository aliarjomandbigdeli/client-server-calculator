import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class Client {

    public static void main(String[] args) {
        try (Socket server = new Socket("127.0.0.1", 7654)) {
            System.out.println("Connected to server.");
            OutputStream out = server.getOutputStream();
            InputStream in = server.getInputStream();
            byte[] buffer = new byte[2048];
            String[] messages = {"Hello", "How-are-you?", "what's-up?"};
            for (String msg: messages) {
                out.write(msg.getBytes());
                System.out.println("SENT: " + msg);
                int read = in.read(buffer);
                System.out.println("RECV: " + new String(buffer, 0, read));
            }
            System.out.print("All messages sent.\nClosing ... ");
        } catch (IOException ex) {
            System.err.println(ex);
        }
        System.out.println("done.");
    }
}
