package versicherung.Models;

import java.util.Date;

public class Account extends VerwaltungsPersonal{
    private boolean isLoggedIn = false;

    public Account() {
    }

    public void anMelden(){
        isLoggedIn = true;
    }

    public void abMelden(){
        isLoggedIn = false;
    }

    public Account(String id, String ausweisNummer, String vorName, String nachName, Date geburstDatum, String telefonNummer, String adresse) {
        super(id, ausweisNummer, vorName, nachName, geburstDatum, telefonNummer, adresse);
    }
}
