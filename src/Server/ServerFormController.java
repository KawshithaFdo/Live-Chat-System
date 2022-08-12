package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ServerFormController extends Thread {
    private Socket accept;
    private PrintWriter printWriter;
    private ArrayList<ServerFormController> thread;

    public ServerFormController(Socket socket, ArrayList<ServerFormController> thread){
        this.accept = socket;
        this.thread = thread;
    }

    public void run() {
            try {
                InputStreamReader inputStreamReader = new InputStreamReader(accept.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                printWriter=new PrintWriter(accept.getOutputStream(),true);
                while (true){
                    String outputString = bufferedReader.readLine();

                    if(outputString.equals("exit")){
                        break;
                    }
                    for (ServerFormController s : thread) {
                        s.printWriter.println(outputString);
                    }
                    System.out.println("Server Success. " + outputString);
                }

            }catch (Exception e){
                System.out.println("Server Error" + e.getStackTrace());
            }
    }
}
