package versicherung;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import versicherung.Models.VersicherungsTyp;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseVersicherung {
    // generate constructor
    public DatabaseVersicherung() {}

    public static boolean erstelleNeuVersicherungsTyp(VersicherungsTyp versicherungsTyp)
    {
        Connection connection = Main.getConnection();

        String insertSql = "INSERT INTO versicherungstypen (name) VALUES ('"+versicherungsTyp.getName()+"');";

        boolean isSuccesful = false;

        // create a resultSet which will be used to get the index of the versicherungsTyp which was just inserted
        ResultSet resultSet = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            // while the resultSet has a next row, get the idx of the versicherungsTyp which was just inserted
            while(resultSet.next()){
                System.out.println("erstelleNeuVersicherungsTyp, Generated versicherungsTyp Idx: " + resultSet.getString(1));
                isSuccesful = true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return isSuccesful;
    }

    public static boolean loescheVersicherungsTyp(VersicherungsTyp versicherungsTyp)
    {
        Connection connection = Main.getConnection();

        String deleteSql = "DELETE FROM versicherungstypen WHERE id = '"+versicherungsTyp.getId()+"';";

        boolean isSuccesful = false;

        try(PreparedStatement preparedStatement = connection.prepareStatement(deleteSql))
        {
            preparedStatement.executeUpdate();
            isSuccesful = true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return isSuccesful;
    }

    public static ArrayList<VersicherungsTyp> getAllVersicherungsTypen() throws SQLException{
        ArrayList<VersicherungsTyp> versicherungsTypen = new ArrayList<>();
        try {
            Connection connection = Main.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM versicherungstypen");
            while (resultSet.next()) {
                versicherungsTypen.add(new VersicherungsTyp(String.valueOf(resultSet.getInt("id")), resultSet.getString("name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return versicherungsTypen;
    }

}
