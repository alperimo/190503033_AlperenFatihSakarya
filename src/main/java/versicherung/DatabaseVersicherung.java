package versicherung;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import versicherung.Models.Person;
import versicherung.Models.VersicherungsTyp;
import versicherung.Models.VersicherungsVertrag;

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

    public static boolean erstelleNeueVersicherungsVertraege(VersicherungsVertrag newVertrag)
    {
        Connection connection = Main.getConnection();

        Date startDatum = new Date(newVertrag.getStartDatum().getTime());
        Date endDatum = new Date(newVertrag.getEndDatum().getTime());

        String insertSql = "INSERT INTO versicherungsvertraege (versicherungstyp_id, person_id, person_typ, startDatum, endDatum) VALUES ('"+newVertrag.getVersicherungstyp_id()+"', '"+newVertrag.getPerson().getId()+"', '"+newVertrag.getPerson_typ()+"', '"+startDatum+"', '"+endDatum+"');";

        boolean isSuccesful = false;

        try(PreparedStatement preparedStatement = connection.prepareStatement(insertSql))
        {
            preparedStatement.executeUpdate();
            isSuccesful = true;
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return isSuccesful;
    }

    public static ArrayList<VersicherungsVertrag> getAllVersicherungsVertraege() throws SQLException{
        ArrayList<VersicherungsVertrag> versicherungsVertraege = new ArrayList<>();
        try {
            Connection connection = Main.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM versicherungsvertraege");
            while (resultSet.next()) {
                Person person = new Person();
                person.setId(resultSet.getString("person_id"));

                versicherungsVertraege.add(new VersicherungsVertrag(String.valueOf(resultSet.getInt("id")), resultSet.getString("versicherungstyp_id"), person, VersicherungsVertrag.PersonTyp.valueOf(resultSet.getString("person_typ")), resultSet.getDate("startDatum"), resultSet.getDate("endDatum"), null));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return versicherungsVertraege;
    }

}
