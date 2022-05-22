package versicherung;

import versicherung.Models.Kunde;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DatabaseKunden {
    public DatabaseKunden() {}

    public static boolean erstelleNeuKunden(Kunde kunde) {
        System.out.println("erstelleNeuKunden from DatabaseKunden");
        Connection connection = Main.getConnection();

        Date geburstDatum = new Date(kunde.getGeburstDatum().getTime()); // NOTE: this should be java.sql.Date! not java.util.Date!!!

        String insertSql = "INSERT INTO kunden (ausweisnummer, vorName, nachName, geburstDatum, telefonNummer, adresse) VALUES "
                        + "('"+kunde.getAusweisNummer()+"', '"+kunde.getVorName()+"', '"+kunde.getNachName()+"', '"+geburstDatum+"', '"+kunde.getTelefonNummer()+"', '"+kunde.getAdresse()+"');";

        boolean isSuccesful = false;
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                System.out.println("erstelleNeuKunden, Generated Kunden Idx: " + resultSet.getString(1));
                isSuccesful = true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return isSuccesful;
    }

    public static boolean loescheKunde(Kunde kunde) {
        Connection connection = Main.getConnection();

        String deleteSql = "DELETE FROM kunden WHERE id = '"+kunde.getId()+"';";

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

    public static ArrayList<Kunde> getAlleKunden() throws SQLException{
        Connection connection = Main.getConnection();
        ResultSet resultSet = null;

        Statement statement = connection.createStatement();
        String selectSql = "SELECT * from kunden";
        resultSet = statement.executeQuery(selectSql);
        ArrayList<Kunde> kundenList = new ArrayList<>();
        while(resultSet.next()){
            Kunde kunde = new Kunde(String.valueOf(resultSet.getInt("id")), resultSet.getString("ausweisNummer"), resultSet.getString("vorName"),
                    resultSet.getString("nachName"), resultSet.getDate("geburstDatum"), resultSet.getString("telefonNummer"), resultSet.getString("adresse"));
            kundenList.add(kunde);
        }

        return kundenList;
    }
}
