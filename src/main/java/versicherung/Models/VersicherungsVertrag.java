package versicherung.Models;

import java.util.Date;

public class VersicherungsVertrag {

    enum VertragStatus{
        Pending,
        Approved,
        Rejected
    }

    enum PersonTyp{
        Kunde,
        Mitarbeiter,
        VerwaltungsPersonal;
    }

    public String vertrag_id;
    public String versicherungstyp_id;
    public Person person;
    public PersonTyp person_typ;
    public Date startDatum;
    public Date endDatum;
    public VertragStatus status;

    public String getVertrag_id() {
        return vertrag_id;
    }

    public void setVertrag_id(String vertrag_id) {
        this.vertrag_id = vertrag_id;
    }

    public String getVersicherungstyp_id() {
        return versicherungstyp_id;
    }

    public void setVersicherungstyp_id(String versicherungstyp_id) {
        this.versicherungstyp_id = versicherungstyp_id;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public PersonTyp getPerson_typ() {
        return person_typ;
    }

    public void setPerson_typ(PersonTyp person_typ) {
        this.person_typ = person_typ;
    }

    public Date getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(Date startDatum) {
        this.startDatum = startDatum;
    }

    public Date getEndDatum() {
        return endDatum;
    }

    public void setEndDatum(Date endDatum) {
        this.endDatum = endDatum;
    }

    public VertragStatus getStatus() {
        return status;
    }

    public void setStatus(VertragStatus status) {
        this.status = status;
    }
}
