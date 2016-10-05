import java.io.*;
import java.net.Socket;
import java.net.ServerSocket;
import java.util.*;



/*
 * An echo server that simply echoes messages back.
 * Created by Jack on 9/29/2016.
 */
public class EchoServer {
    // Create a socket for the server
    private static ServerSocket serverSocket = null;
    // Create a socket for the user
    private static Socket userSocket = null;
    private static BufferedReader input_stream = null;
    private static PrintStream output_stream = null;



    public static void main(String args[]) {

        // The default port number.
        int portNumber = 58999;
        if (args.length < 1) {
            System.out.println("Usage: java Server <portNumber>\n"
                    + "Now using port number=" + portNumber + "\n");
        } else {
            portNumber = Integer.valueOf(args[0]).intValue();
        }


        /*
         * Open a server socket on the portNumber (default 8000).
         */
        String clientSentence;
        String capSentence;
        /*
         * Create a user socket for accepted connection
         */
        while (true) {
            try {
                // YOUR CODE
                serverSocket = new ServerSocket(portNumber);
                userSocket = serverSocket.accept();
                input_stream = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));
                output_stream = new PrintStream(userSocket.getOutputStream());
                clientSentence = input_stream.readLine();
                capSentence = clientSentence.toUpperCase() + '\n';
                output_stream.println(capSentence);
            	/*
             	* Close the output stream, close the input stream, close the socket.
             	*/
                input_stream.close();
                output_stream.close();
                userSocket.close();
                serverSocket.close();
            }
            catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}
