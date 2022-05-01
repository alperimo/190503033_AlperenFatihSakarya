package versicherung.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import versicherung.DatabaseLogin;
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
        //if (field_id.getText().equals("root") && field_password.getText().equals("12345")){
        String username = field_id.getText();
        String password = field_password.getText();
        if (DatabaseLogin.checkLogin(username, password)){
            // Login has been confirmed!
            Main main = new Main();
            main.loadDashboard();
            main.getAccount().anMelden(username, password);
            /* TODO get person which belongs to account from database and set it
            Verwaltungspersonal person = DatabaseLogin.getPersonalDaten();
            main.getAccount().setPerson(person);
            */
        }
        else{
            // Wrong id or password!
            System.out.println("Wrong id or password!");
        }

    }

    /* Other Stuffs */
    private Stage myStage;
    public void setMyStage(Stage stage){
        myStage = stage;
    }

}
