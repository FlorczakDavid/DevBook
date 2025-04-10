package co.simplon.devbookapi.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_accounts")
public class Account {

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;


    public Account() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "{username=" + username + ", password= [PROTECTED]";
    }
}