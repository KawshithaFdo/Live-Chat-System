package Client;

import javafx.event.ActionEvent;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientFormController {
    public TextField txtMessage;
    public AnchorPane ClientContext;

    public String username;
    Socket socket=null;

    public void initialize(){
        new Thread(()->{
            try {
                String record = "";
                socket=new Socket("localhost",3000);

                while (!(record.equals("exit"))){
                    InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    record = bufferedReader.readLine();
                    System.out.println(record);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }).start();

    }

    public void btnSendMessage(ActionEvent actionEvent) throws IOException {
        PrintWriter printWriter=new PrintWriter(socket.getOutputStream());
        printWriter.println(txtMessage.getText());
        printWriter.flush();
        txtMessage.setText("");
    }
}

