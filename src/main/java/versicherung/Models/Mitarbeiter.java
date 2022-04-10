package versicherung.Models;

public class Mitarbeiter extends Person{
    public Mitarbeiter() {
    }

    public Mitarbeiter(String id, String ausweisNummer, String vorName, String nachName, String telefonNummer, String adresse) {
        super(id, ausweisNummer, vorName, nachName, telefonNummer, adresse);
    }
}
