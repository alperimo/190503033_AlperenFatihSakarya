package versicherung;

import versicherung.Main;
import versicherung.Models.Person;
import versicherung.Models.VersicherungsVertrag.PersonTyp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DatabasePerson {
    public DatabasePerson() {}

    public static Person getPersonFrom(String ausweisnummer, PersonTyp personTyp) {
        Connection connection = Main.getConnection();

        String tableName = "";
        switch (personTyp) {
            case Kunde:
                tableName = "kunden";
                break;
            case Mitarbeiter:
                tableName = "mitarbeiter";
                break;
            case VerwaltungsPersonal:
                tableName = "verwaltungspersonal";
                break;
        }

        String selectSql = "SELECT * FROM " + tableName + " WHERE ausweisnummer = '" + ausweisnummer + "';";
        
        String personId = null;
        Person person = null;

        try(PreparedStatement preparedStatement = connection.prepareStatement(selectSql))
        {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                personId = resultSet.getString("id");
                person = new Person();
                person.setId(personId);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }

        return person;
    }
}
