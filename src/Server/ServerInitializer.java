package Server;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.util.ArrayList;

public class ServerInitializer {

    public static void main(String[] args) throws IOException {
            new Thread(()->{
                try {
                    //Client Message = record
                    String record = "";
                    ServerSocket serverSocket=new ServerSocket(3000);
                    System.out.println("Server Started");

                    Socket accept=serverSocket.accept();


                    while (true){
                        record=new BufferedReader(new InputStreamReader(accept.getInputStream())).readLine();
//                        System.out.println(record);

                        PrintWriter printWriter=new PrintWriter(accept.getOutputStream());
                        printWriter.println(record);
                        printWriter.flush();

                    }

                }catch (Exception e){
                    System.out.println("Server Error " +  e.getStackTrace());
                }
            }).start();

            new Thread(()->{
                ServerSocket serverSocket2 = null;
                try {
                    serverSocket2 = new ServerSocket(13085);
                    Socket socket = serverSocket2.accept();
                    InputStream inputStream = socket.getInputStream();

                    System.out.println("Reading: " + System.currentTimeMillis());

                    byte[] sizeAr = new byte[4];
                    inputStream.read(sizeAr);
                    int size = ByteBuffer.wrap(sizeAr).asIntBuffer().get();

                    byte[] imageAr = new byte[size];
                    inputStream.read(imageAr);

                    BufferedImage image = ImageIO.read(new ByteArrayInputStream(imageAr));

                    System.out.println("Received " + image.getHeight() + "x" + image.getWidth() + ": " + System.currentTimeMillis());
                    ImageIO.write(image, "jpeg", new File("D:\\GDSE57\\SEM_02\\INP\\socket\\Live_Chat_System\\src\\asserts\\desert2.jpeg"));

                    serverSocket2.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }).start();



    }

}
