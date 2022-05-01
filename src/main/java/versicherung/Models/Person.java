package versicherung.Models;

import java.util.Date;

public class Person {
    public String id = "";
    public String ausweisNummer = "";
    public String vorName = "";
    public String nachName = "";
    public String telefonNummer = "";
    public String adresse = "";
    public Date geburstDatum;

    public Person() {
    }

    public Person(String id, String ausweisNummer, String vorName, String nachName, Date geburstDatum, String telefonNummer, String adresse) {
        this.id = id;
        this.ausweisNummer = ausweisNummer;
        this.vorName = vorName;
        this.nachName = nachName;
        this.geburstDatum = geburstDatum;
        this.telefonNummer = telefonNummer;
        this.adresse = adresse;
    }

    // Getters
    public String getId() {
        return id;
    }

    public String getAusweisNummer() {
        return ausweisNummer;
    }

    public String getVorName() {
        return vorName;
    }

    public String getNachName() {
        return nachName;
    }

    public String getTelefonNummer() {
        return telefonNummer;
    }

    public Date getGeburstDatum() {return geburstDatum;}

    public String getAdresse() {
        return adresse;
    }

    // Setters
    public void setId(String id) {
        this.id = id;
    }

    public void setAusweisNummer(String ausweisNummer) {
        this.ausweisNummer = ausweisNummer;
    }

    public void setVorName(String vorName) {
        this.vorName = vorName;
    }

    public void setNachName(String nachName) {
        this.nachName = nachName;
    }

    public void setTelefonNummer(String telefonNummer) {
        this.telefonNummer = telefonNummer;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
}
