package Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerInitializer {
    private static Socket socket;
    private static ServerSocket serverSocket;

    public static void main(String[] args) {

        //add all the clients Threads to list
        ArrayList<ServerFormController> threadArrayList = new ArrayList<>();

        try(ServerSocket serverSocket = new ServerSocket(3000)){

            System.out.println("Server Started...");

            while(true){
                Socket socket = serverSocket.accept();
                ServerFormController serverThread = new ServerFormController(socket, threadArrayList);

                //Starting the Thread
                threadArrayList.add(serverThread);
                serverThread.start();

            }
        } catch (IOException e) {
            System.out.println("Error : " + e.getStackTrace());
        }/*finally{
            try {
                socket.close();
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }*/
    }

}
