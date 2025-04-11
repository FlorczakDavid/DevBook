package co.simplon.devbookapi.entities;

import jakarta.persistence.*;
import java.time.ZonedDateTime;

@Entity
@Table(name = "t_rss_providers")
public class RssProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String link;
    private String url;
    private String title;
    private String description;
    private String imageUrl;
    private ZonedDateTime lastUpdate;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public ZonedDateTime getLastUpdate() {
        return lastUpdate;
    }
    public void setLastUpdate(ZonedDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
