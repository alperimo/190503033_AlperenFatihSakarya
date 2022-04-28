package versicherung.Models;

import java.util.Date;

public class VersicherungsVertraege {

    enum VertragStatus{
        Pending,
        Approved,
        Rejected
    }

    static class VersicherungsVertrag{
        public String vertrag_id;
        public String versicherungstyp_id;
        public Date startDatum;
        public Date endDatum;
        public VertragStatus status;
    }

    public VersicherungsVertraege() {

    }
}
