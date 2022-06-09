package versicherung;

import versicherung.Models.VerwaltungsPersonal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseLogin {
    public DatabaseLogin() {}

    public static Integer checkLogin(String username, String password){
        String selectSql = "SELECT * from account WHERE username = '"+username+"' and password = '"+password+"'";
        Connection connection = Main.getConnection();
        ResultSet resultSet;
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectSql)){
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                return resultSet.getInt("verwaltungspersonal_id");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return 0;
    }

    public static VerwaltungsPersonal getPersonalDaten(Integer personalId)
    {
        String selectSql = "SELECT * from verwaltungspersonal WHERE id = '"+personalId+"'";
        Connection connection = Main.getConnection();
        ResultSet resultSet;
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectSql)){
            resultSet = preparedStatement.executeQuery();
            while(resultSet.next())
            {
                VerwaltungsPersonal personal = new VerwaltungsPersonal(String.valueOf(personalId), resultSet.getString("ausweisNummer"), resultSet.getString("vorName"), 
                    resultSet.getString("nachName"), resultSet.getDate("geburstDatum"), resultSet.getString("telefonNummer"), resultSet.getString("adresse"));
                return personal;
            }
        }catch(SQLException e)
        {
            e.printStackTrace();
        }

        return null;
    }
                
}
