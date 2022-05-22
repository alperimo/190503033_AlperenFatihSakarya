package versicherung.Models;

import java.util.Date;

public class Mitarbeiter extends Person{
    public String arbeit_role;

    public Mitarbeiter() {
    }

    public Mitarbeiter(String id, String ausweisNummer, String vorName, String nachName, Date geburstDatum, String telefonNummer, String adresse, String arbeit_role) {
        super(id, ausweisNummer, vorName, nachName, geburstDatum, telefonNummer, adresse);
        setArbeit_role(arbeit_role);
    }

    public String getArbeit_role() {
        return arbeit_role;
    }

    public void setArbeit_role(String arbeit_role) {
        this.arbeit_role = arbeit_role;
    }
}
