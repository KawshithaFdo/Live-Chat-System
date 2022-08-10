package Server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerFormController {
    Socket accept;

    public void initialize() throws IOException {

        new Thread(()->{
            try {
                //Client Message = record
                String record = "";
                ServerSocket serverSocket=new ServerSocket(3000);
                accept=serverSocket.accept();


                while (!(record.equals("exit"))){
                    InputStreamReader inputStreamReader = new InputStreamReader(accept.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    record = bufferedReader.readLine();
                    System.out.println(record);

                    PrintWriter printWriter=new PrintWriter(accept.getOutputStream());
                    printWriter.println(record);
                    printWriter.flush();

                }



            }catch (Exception e){
                e.printStackTrace();
            }
        }).start();
    }
}
