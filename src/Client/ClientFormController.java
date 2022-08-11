package Client;

import javafx.event.ActionEvent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.lang.reflect.Array;
import java.net.Socket;

public class ClientFormController {
    public TextField txtMessage;
    public String username;
    public TextArea ClientContext;
    Socket socket=null;

    public void initialize(){
        new Thread(()->{
            try {
                String record = "";
                int i=0;
                socket=new Socket("localhost",3000);

                while (!(record.equals("exit"))){
                    InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    record = bufferedReader.readLine();
                    System.out.println(record);

                    ClientContext.setPrefRowCount(i++);
                    ClientContext.setStyle("-fx-font-size: 20px;-fx-font-family: 'Bookshelf Symbol 7'");
                    ClientContext.appendText("\n"+username+" : "+record+"\n");
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

