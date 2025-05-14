package co.simplon.devbookapi.entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "t_email")
public class EmailConfirmation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name= "uuid_token", nullable = false, unique = true)
    private String uuidToken;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;

    @Column(name = "creation",nullable = false)
    private LocalDateTime creation;

    @Column(name = "expiration",nullable = false)
    private LocalDateTime expiration;

    public EmailConfirmation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUuidToken() {
        return uuidToken;
    }

    public void setUuidToken(String uuidToken) {
        this.uuidToken = uuidToken;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public LocalDateTime getCreation() {
        return creation;
    }

    public void setCreation(LocalDateTime creation) {
        this.creation = creation;
    }

    public LocalDateTime getExpiration() {
        return expiration;
    }

    public void setExpiration(LocalDateTime expiration) {
        this.expiration = expiration;
    }

    @Override
    public String toString() {
        return "EmailConfirmation{" +
                "id=" + id +
                ", uuidToken='" + uuidToken + '\'' +
                ", account=" + account +
                ", creation=" + creation +
                ", expiration=" + expiration +
                '}';
    }
}
