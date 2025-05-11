package co.simplon.devbookapi.dtos;

import jakarta.validation.constraints.NotBlank;

public record Authentication(@NotBlank String username, @NotBlank String password) {
	
	@Override
	public String toString() {
		return "AccountCreate [username=" + username + ", password= [PROTECTED]"  + "]";
	}

	
}