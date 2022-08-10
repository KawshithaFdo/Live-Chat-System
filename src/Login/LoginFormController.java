package Login;

import Client.ClientFormController;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginFormController {
    public JFXTextField txtUserName;
    public JFXButton btnlogin;
    public AnchorPane LoginContext;

    public void LogInOnAction(ActionEvent actionEvent) throws IOException {

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../Client/ClientForm.fxml"));
        Parent parent = loader.load();
        ClientFormController controller = loader.<ClientFormController>getController();
        controller.username=txtUserName.getText();
        Stage window = (Stage) LoginContext.getScene().getWindow();
        window.setScene(new Scene(parent));
        window.show();
    }
}
