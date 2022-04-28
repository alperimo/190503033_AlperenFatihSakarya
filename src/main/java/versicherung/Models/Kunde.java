package versicherung.Models;

import java.util.Date;

public class Kunde extends Person{
    public Kunde() {
    }

    public Kunde(String id, String ausweisNummer, String vorName, String nachName, Date geburstDatum, String telefonNummer, String adresse) {
        super(id, ausweisNummer, vorName, nachName, geburstDatum, telefonNummer, adresse);
    }
}
