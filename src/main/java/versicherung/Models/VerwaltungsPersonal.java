package versicherung.Models;

public class VerwaltungsPersonal extends Person{
    public VerwaltungsPersonal() {
    }

    public VerwaltungsPersonal(String id, String ausweisNummer, String vorName, String nachName, String telefonNummer, String adresse) {
        super(id, ausweisNummer, vorName, nachName, telefonNummer, adresse);
    }
}
