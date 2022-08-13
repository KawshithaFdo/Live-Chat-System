package Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

public class ServerInitializer {

    public static void main(String[] args) {
            new Thread(()->{
                try {
                    //Client Message = record
                    String record = "";
                    ServerSocket serverSocket=new ServerSocket(3000);
                    System.out.println("Server Started");

                    Socket accept=serverSocket.accept();


                    while (true){
                        record=new BufferedReader(new InputStreamReader(accept.getInputStream())).readLine();
//                        System.out.println(record);

                        PrintWriter printWriter=new PrintWriter(accept.getOutputStream());
                        printWriter.println(record);
                        printWriter.flush();

                    }

                }catch (Exception e){
                    System.out.println("Server Error " +  e.getStackTrace());
                }
            }).start();

        sim_sock ob=new sim_sock();
    }

}
