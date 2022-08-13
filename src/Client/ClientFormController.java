package Client;

import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

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

                InputStreamReader inputStreamReader = new InputStreamReader(socket.getInputStream(),"UTF8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                record = bufferedReader.readLine();

                while (!(record.equals("exit"))){
                    System.out.println(username+" Said :"+record);
                    ClientContext.setStyle("-fx-font-size: 20px;-fx-font-family: 'Bookshelf Symbol 7'");
                    ClientContext.appendText("\n"+username+" : "+record+"\n");
                    inputStreamReader = new InputStreamReader(socket.getInputStream(),"UTF8");
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

    public void CameraOnAction(MouseEvent mouseEvent) {
        new Thread(()->{
            Socket socket = null;
            try {
                socket = new Socket("localhost", 13085);
                OutputStream outputStream = socket.getOutputStream();

                BufferedImage image = ImageIO.read(new File("D:\\GDSE57\\SEM_02\\INP\\socket\\Live_Chat_System\\src\\asserts\\desert.jpeg"));

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(image, "jpeg", byteArrayOutputStream);

                byte[] size = ByteBuffer.allocate(4).putInt(byteArrayOutputStream.size()).array();
                outputStream.write(size);
                outputStream.write(byteArrayOutputStream.toByteArray());
                outputStream.flush();
                System.out.println("Flushed: " + System.currentTimeMillis());

                Thread.sleep(120000);
                System.out.println("Closing: " + System.currentTimeMillis());
                socket.close();
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
            }

        }).start();

    }
}

