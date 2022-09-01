package Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerInitializer {

    private static Socket socket;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {

        //add all the client Threads
        ArrayList<ServerFormController> thread = new ArrayList<>();

        try(ServerSocket serverSocket = new ServerSocket(3000)){

            System.out.println("Server is waiting for the clients requests..!");

            while(true){
                Socket socket = serverSocket.accept();
                ServerFormController serverThread = new ServerFormController(socket, thread);

                //Start the Thread
                thread.add(serverThread);
                serverThread.start();

            }
        } catch (IOException e) {
            System.out.println("Error : " + e.getStackTrace());
        }finally{
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
