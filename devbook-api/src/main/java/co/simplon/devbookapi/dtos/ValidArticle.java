package co.simplon.devbookapi.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ValidArticle(
<<<<<<< HEAD
    @Pattern(regexp = "^(http|https)://[a-zA-Z0-9.]{1,253}\\.[a-zA-Z0-9/]{2,63}", message = "These URL is not valid") String url,
=======
    @Pattern(regexp = "^(http|https)://[a-zA-Z0-9.]{1,253}\\.[a-zA-Z0-9/]{2,63}", message = "These URL is not valid")
    String url,
>>>>>>> shareArticle2
    @NotBlank String title,
    String imagePath,
    String description,
    String authors
) {
}
