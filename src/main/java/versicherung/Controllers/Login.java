package versicherung.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import versicherung.Main;

import java.io.IOException;

public class Login {

    @FXML
    private TextField field_id;

    @FXML
    private PasswordField field_password;

    @FXML
    private Button btnAnmelden;

    @FXML
    void handeLoginClick(ActionEvent event) throws IOException {
        if (field_id.getText().equals("root") && field_password.getText().equals("12345")){
            // Login has been confirmed!
            Main main = new Main();
            main.loadDashboard();
            main.getAccount().anMelden();
        }
        else{
            // Wrong id or password!
        }

    }

    /* Other Stuffs */
    private Stage myStage;
    public void setMyStage(Stage stage){
        myStage = stage;
    }

}
