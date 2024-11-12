import java.net.*;
import java.io.*;

public class Client3 {
    public static void main(String args[]) {
        Socket sock = null;
        BufferedReader keyRead = null;
        PrintWriter pwrite = null;
        BufferedReader socketRead = null;
        try {
            // Establish connection to server at localhost:4000
            sock = new Socket("127.0.0.1", 4000);

            // Reading the file name from user input
            keyRead = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter the file name to display the contents of file: ");
            String fname = keyRead.readLine();

            // Sending file name to the server
            pwrite = new PrintWriter(sock.getOutputStream(), true);
            pwrite.println(fname);

            // Receiving and displaying file content from server
            socketRead = new BufferedReader(new InputStreamReader(sock.getInputStream()));
            String str;
            while ((str = socketRead.readLine()) != null) {
                System.out.println(str);
            }

        } catch (IOException e) {
            System.out.println("An error occurred while communicating with the server: " + e.getMessage());
        } finally {
            // Closing all streams and socket
            try {
                if (pwrite != null)
                    pwrite.close();
                if (socketRead != null)
                    socketRead.close();
                if (keyRead != null)
                    keyRead.close();
                if (sock != null)
                    sock.close();
            } catch (IOException e) {
                System.out.println("Error closing resources: " + e.getMessage());
            }
        }
    }
}
