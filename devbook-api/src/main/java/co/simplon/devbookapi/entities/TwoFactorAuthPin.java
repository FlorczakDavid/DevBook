package co.simplon.devbookapi.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "t_2fa")
public class TwoFactorAuthPin {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
	
	@Column(name= "pin_code")
	private String pinCode;
	
	@Column(name= "uuid_token")
	private String uuidToken;
	
	@Column(name = "expiration")
	private LocalDateTime expiration;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Account user;

	public TwoFactorAuthPin() {
	}

	public Long getId() {
		return id;
	}

	protected void setId(Long id) {
		this.id = id;
	}

	public String getPinCode() {
		return pinCode;
	}

	public void setPinCode(String pinCode) {
		this.pinCode = pinCode;
	}

	public String getUuidToken() {
		return uuidToken;
	}

	public void setUuidToken(String uuidToken) {
		this.uuidToken = uuidToken;
	}

	public LocalDateTime getExpiration() {
		return expiration;
	}

	public void setExpiration(LocalDateTime expiration) {
		this.expiration = expiration;
	}

	public Account getUser() {
		return user;
	}

	public void setUser(Account user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TwoFactorAuthPin [id=" + id + ", pinCode=" + pinCode + ", uuidToken=" + uuidToken + ", expiration="
				+ expiration + ", user=" + user + "]";
	}
	
	
	
}
