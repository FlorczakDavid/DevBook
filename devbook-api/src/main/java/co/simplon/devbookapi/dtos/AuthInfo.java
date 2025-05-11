package co.simplon.devbookapi.dtos;

public record AuthInfo(String token) {

	public AuthInfo(String token) {
		this.token= token;

	}

}