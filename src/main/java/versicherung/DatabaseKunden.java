package versicherung;

import versicherung.Models.Kunde;

import java.sql.*;

public class DatabaseKunden {
    public DatabaseKunden() {}

    public static void erstelleNeuKunden(Kunde kunde){
        System.out.println("erstelleNeuKunden from DatabaseKunden");
        Connection connection = Main.getConnection();
        /*String insertSql = "INSERT INTO kunden (ausweisnummer, vorName, nachName, geburstDatum, telefonNummer, adresse) VALUES "
                       + "('"+kunde.getAusweisNummer()+"', '"+kunde.getVorName()+"', '"+kunde.getNachName()+"', '"+kunde.getGeburstDatum()+"', '"+kunde.getTelefonNummer()+"', '"+kunde.getAdresse()+"');";
          */

        Date geburstDatum = new Date(kunde.getGeburstDatum().getTime()); // NOTE: this should be java.sql.Date! not java.util.Date!!!
        String insertSql = "INSERT INTO kunden (ausweisnummer, vorName, nachName, geburstDatum, telefonNummer, adresse) VALUES "
                        + "('"+kunde.getAusweisNummer()+"', '"+kunde.getVorName()+"', '"+kunde.getNachName()+"', '"+geburstDatum+"', '"+kunde.getTelefonNummer()+"', '"+kunde.getAdresse()+"');";
        
        ResultSet resultSet = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS))
        {
            preparedStatement.executeUpdate();
            resultSet = preparedStatement.getGeneratedKeys();

            while(resultSet.next()){
                System.out.println("erstelleNeuKunden, Generated Kunden Idx: " + resultSet.getString(1));
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }  
    }
}
