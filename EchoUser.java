import java.io.DataInputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.Socket;
import java.net.UnknownHostException;


public class EchoUser {
    // The user socket
    private static Socket userSocket = null;
    // The output stream
    private static PrintStream output_stream = null;
    // The input stream
    private static BufferedReader input_stream = null;

    private static BufferedReader inputLine = null;

    public static void main(String[] args) {

        // The default port.
        int portNumber = 58787;
        // The default host.
        String host = "csa2.bu.edu";
        String responseLine;
        String serverLine;

        if (args.length < 2) {
            System.out.println("Usage: java User <host> <portNumber>\n"
                    + "Now using host=" + host + ", portNumber=" + portNumber);
        } else {
            host = args[0];
            portNumber = Integer.valueOf(args[1]).intValue();
        }
        /*
         * Open a socket on a given host and port. Open input and output streams.
         */
        try {
            //create user socket
            userSocket = new Socket(host, portNumber);
            //create
            input_stream = new BufferedReader(new InputStreamReader(System.in));
            output_stream = new PrintStream(userSocket.getOutputStream());
            inputLine = new BufferedReader(new InputStreamReader(userSocket.getInputStream()));


        } catch (UnknownHostException e) {
            System.err.println("Don't know about host " + host);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for the connection to the host "
                    + host);
        }
        /*
         * If everything has been initialized then we want to send message to the
         * socket we have opened a connection to on the port portNumber.
	 * When we receive the echo, print it out.
        */
        try {
            responseLine = input_stream.readLine();
            output_stream.println(responseLine +'\n');
            serverLine = inputLine.readLine();
            System.out.println("From Server:" + serverLine);
            // YOUR CODE
                /*
                 * Close the output stream, close the input stream, close the socket.
                */
            output_stream.close();
            input_stream.close();
            userSocket.close();
        } catch (IOException e) {
            System.err.println("IOException:  " + e);
        }

    }
}