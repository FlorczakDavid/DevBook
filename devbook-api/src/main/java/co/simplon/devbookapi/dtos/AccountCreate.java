package co.simplon.devbookapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AccountCreate(@NotBlank @Size(max = 225) String username, @NotBlank String password) {

    @Override
    public String toString() {
        return "{username=" + username + ", password=[PROTECTED]}";
    }

}
