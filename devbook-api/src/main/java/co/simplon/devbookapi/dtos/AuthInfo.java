package co.simplon.devbookapi.dtos;

public record AuthInfo(String token, String role) {

	public AuthInfo(String token, String role) {
		this.token= token;
		this.role = role;

	}

}