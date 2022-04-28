package versicherung.Models;

import java.util.Date;

public class Mitarbeiter extends Person{
    public Mitarbeiter() {
    }

    public Mitarbeiter(String id, String ausweisNummer, String vorName, String nachName, Date geburstDatum, String telefonNummer, String adresse) {
        super(id, ausweisNummer, vorName, nachName, geburstDatum, telefonNummer, adresse);
    }
}
