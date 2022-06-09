package versicherung.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import versicherung.DatabaseLogin;
import versicherung.Main;
import versicherung.Models.VerwaltungsPersonal;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login {

    @FXML
    private TextField field_id;

    @FXML
    private PasswordField field_password;

    @FXML
    private Button btnAnmelden;

    @FXML
    void handeLoginClick(ActionEvent event) throws IOException, SQLException {
        //if (field_id.getText().equals("root") && field_password.getText().equals("12345")){
        String username = field_id.getText();
        String password = field_password.getText();
        Integer verwaltungspersonalId = DatabaseLogin.checkLogin(username, password);
        if (verwaltungspersonalId != 0){
            // Login has been confirmed!
            Main main = new Main();
            
            VerwaltungsPersonal person = DatabaseLogin.getPersonalDaten(verwaltungspersonalId);
            Main.getAccount().setPerson(person);

            main.loadDashboard();
            main.getAccount().anMelden(username, password);
            
        }
        else{
            // Wrong id or password!
            
            // create a alert class and set the message "Wrong id or password" and show it
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Wrong id or password");
            alert.setContentText("Please try again!");
            alert.showAndWait();

        }

    }

    /* Other Stuffs */
    private Stage myStage;
    public void setMyStage(Stage stage){
        myStage = stage;
    }

}
