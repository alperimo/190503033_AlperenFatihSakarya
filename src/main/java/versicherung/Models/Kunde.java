package versicherung.Models;

public class Kunde extends Person{
    public Kunde() {
    }

    public Kunde(String id, String ausweisNummer, String vorName, String nachName, String telefonNummer, String adresse) {
        super(id, ausweisNummer, vorName, nachName, telefonNummer, adresse);
    }
}
