package co.simplon.devbookapi.entities;

import jakarta.persistence.*;



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

    @ManyToOne
    @JoinColumn(name = "id_role")
    private Role role;

    public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
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

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isStatusEmail() {
        return statusEmail;
    }

    public void setStatusEmail(boolean statusEmail) {
        this.statusEmail = statusEmail;
    }

    @Override
    public String toString() {
        return "{username=" + username + ", password= [PROTECTED], statusEmail=" + statusEmail + "}";
    }
}
