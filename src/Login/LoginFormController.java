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


    public void BtnLogInOnAction(ActionEvent actionEvent) throws IOException {

        userName=txtUserName.getText();
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../Client/ClientForm.fxml"));
        Parent parent = loader.load();
        Stage window = (Stage) mainContext.getScene().getWindow();
        window.setScene(new Scene(parent));
        window.show();
    }



}
