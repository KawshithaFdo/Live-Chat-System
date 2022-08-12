package Client;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientFormController{
    public TextField txtMessage;
    public String username;
    public TextArea ClientContext;

    Socket socket;
    public void initialize(){

        new Thread(()->{
            try {
                String record="";
                socket=new Socket("localhost",3000);
                System.out.println(username+" is connected.");

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                record = bufferedReader.readLine();

                while (!(record.equals("exit"))){
                    System.out.println(username+" Said :"+record);
                    ClientContext.setStyle("-fx-font-size: 20px;-fx-font-family: 'Bookshelf Symbol 7'");
                    ClientContext.appendText("\n"+username+" : "+record+"\n");
                    inputStreamReader = new InputStreamReader(socket.getInputStream());
                    bufferedReader = new BufferedReader(inputStreamReader);
                    record = bufferedReader.readLine();
                }
                if (record.equals("exit")) {
                    System.out.println(username+" is Exit.");
                    System.exit(0);
                }

            } catch (Exception e) {
                System.out.println("Client Error " +  e.getStackTrace());
            }

        }).start();
    }


    public void btnSendMessage(MouseEvent keyEvent) throws IOException {
        PrintWriter printWriter=new PrintWriter(socket.getOutputStream());
        printWriter.println(txtMessage.getText());
        printWriter.flush();
        txtMessage.setText("");

    }
}

