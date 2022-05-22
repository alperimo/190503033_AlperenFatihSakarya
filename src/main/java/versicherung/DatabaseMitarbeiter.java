package versicherung;

import versicherung.Models.Mitarbeiter;

import java.sql.*;
import java.util.ArrayList;

public class DatabaseMitarbeiter {
    public DatabaseMitarbeiter() {}

    public static boolean erstelleNeuMitarbeiter(Mitarbeiter mitarbeiter) {
        System.out.println("erstelleNeuMitarbeiter from DatabaseMitarbeiter");
        Connection connection = Main.getConnection();

        Date geburstDatum = new Date(mitarbeiter.getGeburstDatum().getTime()); // NOTE: this should be java.sql.Date! not java.util.Date!!!

        String insertSql = "INSERT INTO mitarbeiter (ausweisnummer, vorName, nachName, role, geburstDatum, telefonNummer, adresse) VALUES "
                        + "('"+mitarbeiter.getAusweisNummer()+"', '"+mitarbeiter.getVorName()+"', '"+mitarbeiter.getNachName()+"', '"+mitarbeiter.getArbeit_role()+"', '"+geburstDatum+"', '"+mitarbeiter.getTelefonNummer()+"', '"+mitarbeiter.getAdresse()+"');";

        boolean isSuccesful = false;
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                System.out.println("erstelleNeuMitarbeiter, Generated Kunden Idx: " + resultSet.getString(1));
                isSuccesful = true;
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return isSuccesful;
    }

    public static boolean loescheMitarbeiter(Mitarbeiter mitarbeiter) {
        Connection connection = Main.getConnection();

        String deleteSql = "DELETE FROM mitarbeiter WHERE id = '"+mitarbeiter.getId()+"';";

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

    public static ArrayList<Mitarbeiter> getAlleMitarbeiter() throws SQLException{
        Connection connection = Main.getConnection();
        ResultSet resultSet = null;

        Statement statement = connection.createStatement();
        String selectSql = "SELECT * from mitarbeiter";
        resultSet = statement.executeQuery(selectSql);
        ArrayList<Mitarbeiter> mitarbeiterList = new ArrayList<>();
        while(resultSet.next()){
            Mitarbeiter mitarbeiter = new Mitarbeiter(String.valueOf(resultSet.getInt("id")), resultSet.getString("ausweisNummer"), resultSet.getString("vorName"),
                    resultSet.getString("nachName"), resultSet.getDate("geburstDatum"), resultSet.getString("telefonNummer"), resultSet.getString("adresse"), resultSet.getString("role"));
            mitarbeiterList.add(mitarbeiter);
        }

        return mitarbeiterList;
    }
}
