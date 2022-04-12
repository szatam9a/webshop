package webshop.user;

import java.sql.PreparedStatement;
import java.sql.Statement;

public class User {
    private int ID;
    private String name;
    private String emailAddress;
    private int password;

    public User(int ID, String name, String emailAddress, int password) {
        this.ID = ID;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public User(String name, String emailAddress, int password) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

}
