package no.ntnu.idata2304.group11.client.tcp;

import java.io.*;  
import java.net.*;
import java.nio.charset.StandardCharsets;  


/**
 * Receive data from the analyzer application.<p>
 * 
 * Acts as a TCP-server receiving data from a client.
 * Unpacks the data and does handles it to the next station.
 * 
 * @since    15-10-2022
 * @version  10-11-2022
 * @author   jorgenfinsveen
 */
public class RaspberryPiServer {
    
    /** Port-number which the server is listening on. */
    private static final int PORT = 8888;
    /** Constraint-variable for server activity time. */
    private boolean listening = true;




    /**
     * Run server and initialize a listener on a specified port.
     * 
     * <p>Receive data and print it to STDOUT.
     */
    public void run() throws IOException {

        /* Variables. */
        Socket socket;
        DataInputStream ds;
        String message;
        int counter = 0;
        int limit = 10;
        ServerSocket serverSocket = new ServerSocket(PORT);


        while (listening) {

            /* Receive data from a client. */
            socket = serverSocket.accept();
            /* Extract the DataInputStream from the socket. */
            ds = new DataInputStream(socket.getInputStream());
            /* Converts the DataInputStream to a String. */
            message = new String(ds.readAllBytes(), StandardCharsets.UTF_8);

            /* Print the received message. */
            printMessage(message);

            /* Manage server timeout condition. */
            counter++;
            if (counter >= limit) {
                listening = false;
            }
        }
        serverSocket.close();
    }



    /**
     * Print a message.
     * 
     * @param message to print.
     */
    private void printMessage(String message) {
        System.out.println("  -  " + message);
    }
}