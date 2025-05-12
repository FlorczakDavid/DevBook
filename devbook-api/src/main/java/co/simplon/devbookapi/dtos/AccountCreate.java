package co.simplon.devbookapi.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountCreate(@NotBlank @Email @Size(max = 255) String username, @NotBlank @Size(max=72) String password) {

    @Override
    public String toString() {
        return "{username=" + username + ", password=[PROTECTED]}";
    }

}
