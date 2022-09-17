package Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ServerFormController extends Thread {
    private Socket socket;
    private ArrayList<ServerFormController> thread;
    private PrintWriter output;
    private BufferedReader reader;

     public ServerFormController(Socket socket, ArrayList<ServerFormController> thread){

         try {

             this.socket = socket;
             this.thread = thread;
             this.reader=new BufferedReader(new InputStreamReader(socket.getInputStream()));
             this.output=new PrintWriter(socket.getOutputStream(),true);

         } catch (IOException e) {
             e.printStackTrace();
         }
     }

    public void run() {
        try {
            String value;
            while ((value = reader.readLine()) != null) {
                if (value.equalsIgnoreCase( "exit")) {
                    return;
                }
                for (ServerFormController se : thread) {
                    se.output.println(value);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                reader.close();
                output.close();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


