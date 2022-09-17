package Client;

import Login.LoginFormController;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.NodeOrientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ClientFormController extends Thread{
    public TextField txtMessage;
    public String username;
    public VBox vbox;
    public Label lblusername;

    BufferedReader reader;
     PrintWriter writer;
     Socket socket;

    private FileChooser fileChooser;
    private File filePath;

    public void initialize(){
        username= LoginFormController.userName;
        lblusername.setText(username);
        System.out.println(username + " is Connected. ");
        try{
            socket = new Socket("localhost", 3000);
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnSendMessage(MouseEvent keyEvent) {
       sendMessage();
    }

    @Override
    public void run() {
        try{
            while (true) {
                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];

                StringBuilder fullMessage = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMessage.append(tokens[i] + " ");
                }

                String[] msgToAr = msg.split(" ");
                String st = "";
                for (int i = 0; i < msgToAr.length - 1; i++) {
                    st += msgToAr[i + 1] + " ";
                }

                Text text = new Text(st);
                String firstChars = "";
                if (st.length() > 3) {
                    firstChars = st.substring(0, 3);

                }


                if (firstChars.equalsIgnoreCase("img")) {
                    //for the Images

                    st = st.substring(3, st.length() - 1);


                    File file = new File(st);
                    Image image = new Image(file.toURI().toString());

                    ImageView imageView = new ImageView(image);

                    imageView.setFitHeight(150);
                    imageView.setFitWidth(200);


                    HBox hBox = new HBox(10);
                    hBox.setAlignment(Pos.BOTTOM_RIGHT);


                    if (!cmd.equalsIgnoreCase(username)) {

                        vbox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);


                        Text text1 = new Text("  " + cmd + " :");
                        hBox.getChildren().add(text1);
                        hBox.getChildren().add(imageView);

                    } else {
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(imageView);
                        Text text1 = new Text(": Me ");
                        hBox.getChildren().add(text1);

                    }

                    Platform.runLater(() -> vbox.getChildren().addAll(hBox));


                } else {

                    TextFlow tempFlow = new TextFlow();

                    if (!cmd.equalsIgnoreCase(username + ":")) {
                        Text txtName = new Text(cmd + " ");
                        txtName.getStyleClass().add("txtName");
                        tempFlow.getChildren().add(txtName);
                    }

                    tempFlow.getChildren().add(text);
                    tempFlow.setMaxWidth(200); //200

                    TextFlow flow = new TextFlow(tempFlow);

                    HBox hBox = new HBox(12); //12

                    //=================================================


                    if (!cmd.equalsIgnoreCase(username + ":")) {

                        vbox.setAlignment(Pos.TOP_LEFT);
                        hBox.setAlignment(Pos.CENTER_LEFT);
                        hBox.getChildren().add(flow);

                    } else {

                        Text text2 = new Text("Me : " + fullMessage);
                        TextFlow flow2 = new TextFlow(text2);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(flow2);
                    }

                    Platform.runLater(() -> vbox.getChildren().addAll(hBox));

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void CameraOnAction(MouseEvent mouseEvent) {
        Stage stage = (Stage) ((Node) mouseEvent.getSource()).getScene().getWindow();
        fileChooser = new FileChooser();
        fileChooser.setTitle("Open");
        this.filePath = fileChooser.showOpenDialog(stage);
        writer.println(username + " " + "img" + filePath.getPath());

    }

    public void sendOnAction(ActionEvent actionEvent) {
        sendMessage();
    }

    public void sendMessage(){
        String msg = txtMessage.getText();
        //System.out.println(msg);


        if(msg.equalsIgnoreCase("bye") || (msg.equalsIgnoreCase("exit"))) {
            System.out.println(username + " is Disconnected.");
            System.exit(0);
        }
        writer.println(username + ": " + txtMessage.getText());
        txtMessage.clear();
    }
}

