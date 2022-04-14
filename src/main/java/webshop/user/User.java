package webshop.user;

import webshop.product.Product;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class User {
    private int ID;
    private String name;
    private String emailAddress;
    private long password;
    private boolean loggedIn;
    private List<Product> shoppingCart = new LinkedList<>();

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

    public boolean login(User user) {
        if (user.emailAddress.equals(this.emailAddress) && user.password == this.password) {
            return true;
        }
        return false;
    }

    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
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

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", emailAddress='" + emailAddress + '\'' +
                '}';
    }
}
