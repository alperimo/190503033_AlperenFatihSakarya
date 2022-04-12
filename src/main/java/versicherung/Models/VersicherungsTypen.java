package versicherung.Models;

import java.util.Vector;

public class VersicherungsTypen {

    static class Typ{
        public String typ_id;
        public String typ_name;
    }

    private Vector<Typ> Typen;

    public VersicherungsTypen() {
        Typen = new Vector<>();
    }

    public void addTyp(Typ typ){
        if (!Typen.contains(typ))
        {
            Typen.add(typ);
        }
    }

    public void deleteTyp(int index){
        Typen.remove(index);
    }
}
