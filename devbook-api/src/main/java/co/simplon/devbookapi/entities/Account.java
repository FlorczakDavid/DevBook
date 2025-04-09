package co.simplon.devbookapi.entities;

import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "t_accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "password")
    private String password;
    
    @Column(name = "statusemail")
    private boolean statusEmail;


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


    public boolean isStatusEmail() {
		return statusEmail;
	}

	public void setStatusEmail(boolean statusEmail) {
		this.statusEmail = statusEmail;
	}

	@Override
    public String toString() {
        return "{username=" + username + ", password= [PROTECTED], statusEmail=" + statusEmail;
    }
}