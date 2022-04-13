package webshop.user;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

public class User {
    private int ID;
    private String name;
    private String emailAddress;
    private long password;
    private boolean loggedIn;

    public User(int ID, String name, String emailAddress, long password) {
        this.ID = ID;
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    public User(String name, String emailAddress, long password) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return getPassword() == user.getPassword() && Objects.equals(getEmailAddress(), user.getEmailAddress());
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    @Override
    public int hashCode() {
        return Objects.hash(getEmailAddress(), getPassword());
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

    public long getPassword() {
        return password;
    }

    public void setPassword(long password) {
        this.password = password;
    }

}
