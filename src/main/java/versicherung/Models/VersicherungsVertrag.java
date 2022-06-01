package versicherung.Models;

import java.util.Date;

public class VersicherungsVertrag {

    enum VertragStatus{
        Pending,
        Approved,
        Rejected
    }

    //TODO: place this PersonTyp as a seperated class within model
    public enum PersonTyp{
        Kunde("Kunde"),
        Mitarbeiter("Mitarbeiter"),
        VerwaltungsPersonal("VerwaltungsPersonal");

        private String label;
        
        PersonTyp(String label){
            this.label = label;
        }
        
        public String toString(){
            return this.label;
        }
    }

    public String vertrag_id;
    public String versicherungstyp_id;
    public Person person;
    public PersonTyp person_typ;
    public Date startDatum;
    public Date endDatum;
    public VertragStatus status = null;

    // generate a constructor with all attributes
    public VersicherungsVertrag(String vertrag_id, String versicherungstyp_id, Person person, PersonTyp person_typ, Date startDatum, Date endDatum, VertragStatus status) {
        this.vertrag_id = vertrag_id;
        this.versicherungstyp_id = versicherungstyp_id;
        this.person = person;
        this.person_typ = person_typ;
        this.startDatum = startDatum;
        this.endDatum = endDatum;
        this.status = status;
    }

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
