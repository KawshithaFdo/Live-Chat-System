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
    public Socket socket;
    PrintWriter printWriter;
    BufferedReader bufferedReader;

    public void initialize(){
        new Thread(()->{
            try {

                socket=new Socket("localhost",3000);
                System.out.println(username+" is Connected.");

                    InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                    bufferedReader = new BufferedReader(inputStreamReader);
                    printWriter=new PrintWriter(socket.getOutputStream(),true);
                    System.out.println(bufferedReader.readLine());


            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }


    public void btnSendMessage(MouseEvent keyEvent) throws IOException {
        String record = bufferedReader.readLine();
        ClientContext.setStyle("-fx-font-size: 20px;-fx-font-family: 'Bookshelf Symbol 7'");
        ClientContext.appendText("\n"+username+" : "+record+"\n");
        printWriter.println(record);
        txtMessage.setText("");

    }
}

