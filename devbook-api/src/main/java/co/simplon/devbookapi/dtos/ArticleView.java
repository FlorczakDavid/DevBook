package co.simplon.devbookapi.dtos;

public record ArticleView(
    String url,
    String title,
    String imagePath,
    String description,
    String author
) {
}
