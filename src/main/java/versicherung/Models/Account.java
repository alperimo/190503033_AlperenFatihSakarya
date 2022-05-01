package versicherung.Models;

import java.util.Date;

public class Account{
    private boolean isLoggedIn = false;
    private VerwaltungsPersonal person;

    private String username;
    private String password;

    public Account() {
    }

    public void anMelden(String username, String password){
        isLoggedIn = true;
        this.username = username;
        this.password = password;
    }

    public void abMelden(){
        isLoggedIn = false;
    }

    public void setPerson(VerwaltungsPersonal person) {
        this.person = person;
    }

    public VerwaltungsPersonal getPerson(){
        return person;
    }

}
