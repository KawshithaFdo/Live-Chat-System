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

public class LoginFormController {
    public AnchorPane mainContext;
    public TextField txtUserName;

    public void BtnLogInOnAction(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../Client/ClientForm.fxml"));
        Parent parent = loader.load();
        ClientFormController controller = loader.<ClientFormController>getController();
        controller.username=txtUserName.getText();
        Stage window = (Stage) mainContext.getScene().getWindow();
        window.setScene(new Scene(parent));
        window.show();
    }
}
