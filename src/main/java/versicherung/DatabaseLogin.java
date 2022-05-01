package versicherung;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabaseLogin {
    public DatabaseLogin() {}

    public static boolean checkLogin(String username, String password){
        String selectSql = "SELECT * from account WHERE username = '"+username+"' and password = '"+password+"'";
        Connection connection = Main.getConnection();
        ResultSet resultSet;
        try(PreparedStatement preparedStatement = connection.prepareStatement(selectSql)){
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        }catch (SQLException e){
            e.printStackTrace();
        }

        return false;
    }
}
