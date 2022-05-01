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

    @Override
    public void start(Stage stage) throws IOException {
        globalStage = stage;
        stage.setTitle("Pek-Versicherung V1.0");
        loadLogin();
    }

    public static void main(String[] args) {
        DatabaseConnection db = new DatabaseConnection();
        Connection connection = db.getConnection();

        account = new Account();

        testDB(connection);

        launch();
    }

    public Account getAccount(){
        return account;
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

    public static void testDB(Connection connection){
        String insertSql = "INSERT INTO kunden (ausweisnummer, vorName, nachName, geburstDatum, telefonNummer, adresse) VALUES "
                + "('123', 'Alp', 'Baba', '2016-01-01', '095255215', 'lannnnnnn');";

        ResultSet resultSet = null;
        try(PreparedStatement statement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS))
        {
            statement.execute();
            // Retrieve the generated key from the insert.
            resultSet = statement.getGeneratedKeys();

            // Print the ID of the inserted row.
            while(resultSet.next()){
                System.out.println("Generated: " + resultSet.getString(1));
            }
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}