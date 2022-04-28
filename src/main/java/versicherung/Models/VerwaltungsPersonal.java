package versicherung.Models;

import java.util.Date;

public class VerwaltungsPersonal extends Person{
    public VerwaltungsPersonal() {
    }

    public VerwaltungsPersonal(String id, String ausweisNummer, String vorName, String nachName, Date geburstDatum, String telefonNummer, String adresse) {
        super(id, ausweisNummer, vorName, nachName, geburstDatum, telefonNummer, adresse);
    }
}
