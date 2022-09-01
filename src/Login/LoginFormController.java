package Login;

import Client.ClientFormController;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class LoginFormController {
    public AnchorPane mainContext;
    public TextField txtUserName;
    public static String userName;

    public static ArrayList<String> users = new ArrayList<>();

    public void BtnLogInOnAction(ActionEvent actionEvent) throws IOException {

        userName = txtUserName.getText().trim();
        boolean flag = false;
        if(users.isEmpty()){
            users.add(userName);
            flag = true;
        }


        for(String s : users){
            if (!s.equalsIgnoreCase(userName)) {
                flag = true;
                System.out.println(userName);
                break;
            }
        }

        if(flag){
            /*this.mainContext.getChildren().clear();
            this.mainContext.getChildren().add(FXMLLoader.load(this.getClass().
                    getResource("../Client/ClientForm.fxml")));*/
             FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../Client/ClientForm.fxml"));
        Parent parent = loader.load();
        ClientFormController controller = loader.<ClientFormController>getController();
        controller.username=userName;
        Stage window = (Stage) mainContext.getScene().getWindow();
        window.setScene(new Scene(parent));
        window.show();
        }


    }
}
