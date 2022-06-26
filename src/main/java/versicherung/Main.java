package versicherung;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import versicherung.Controllers.Login;
import versicherung.Models.Account;

import java.io.IOException;
import java.sql.*;

public class Main extends Application {
    private static Stage globalStage;

    private static Account account;
    private static Connection connection;

    @Override
    public void start(Stage stage) throws IOException {
        globalStage = stage;
        stage.setTitle("Pek-Versicherung V4.0");
        loadLogin();
    }

    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        connection = db.getConnection();

        account = new Account();

        launch();
    }

    public static Account getAccount(){
        return account;
    }

    public static Connection getConnection(){
        return connection;
    }

    public void loadLogin() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Views/Login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 620);
        globalStage.setScene(scene);
        globalStage.show();

        Login controllerLogin = fxmlLoader.getController();
        controllerLogin.setMyStage(globalStage);
    }

    public void loadDashboard() throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Views/Dashboard.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 960, 620);
        globalStage.setScene(scene);
    }
}