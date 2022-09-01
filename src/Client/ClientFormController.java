package Client;

import javafx.geometry.NodeOrientation;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ClientFormController extends Thread{
    public TextField txtMessage;
    public String username;
    public TextArea ClientContext;

    public BufferedReader reader;
    public PrintWriter writer;
    public Socket socket;

    public void initialize(){
        System.out.println("Initialized method " + username);
        try{
            socket = new Socket("localhost", 3000);
            System.out.println("Socket Connected.");
            reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            writer = new PrintWriter(socket.getOutputStream(), true);
            this.start();

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void btnSendMessage(MouseEvent keyEvent) throws IOException {
        String msg = txtMessage.getText().trim();
        writer.println(username + ": "+ msg);
        ClientContext.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        txtMessage.setText("");
        if(msg.equalsIgnoreCase("Bye") || (msg.equalsIgnoreCase("logout"))){
            System.exit(0);
        }

    }

    @Override
    public void run() {
        try{
            while (true){
                String msg = reader.readLine();
                String[] tokens = msg.split(" ");
                String cmd = tokens[0];
                StringBuilder fullMessage = new StringBuilder();
                for (int i = 1; i < tokens.length; i++) {
                    fullMessage.append(tokens[i]);
                }

                System.out.println(fullMessage);

                if(cmd.equalsIgnoreCase(username + ": ")){
                    continue;
                }else if (fullMessage.toString().equalsIgnoreCase("bye")){
                    break;
                }

                ClientContext.appendText(msg + "\n\n");
            }

            reader.close();
            writer.close();
            socket.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
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

