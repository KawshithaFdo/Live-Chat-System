package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerInitializer {

    //add all the client Threads
    private static ArrayList<ServerFormController> thread = new ArrayList<ServerFormController>();

    public static void main(String[] args) throws IOException {
        //Start Server Socket
        ServerSocket serverSocket = new ServerSocket(3000);
        Socket socket;

            while(true){
                System.out.println("Server port  waiting to Clients..");
                // Accept The Client Request
                socket = serverSocket.accept();
                System.out.println("Client Connected..");
                ServerFormController serverThread = new ServerFormController(socket, thread);

                //Start the Thread
                thread.add(serverThread);
                serverThread.start();
            }
    }
}
