package co.simplon.devbookapi.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.ZonedDateTime;
import java.util.Set;

@Entity
@Table(name = "t_rss_providers")
public class RssProvider {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String subtitle;
    private String link;
    private ZonedDateTime lastUpdate;

//    @OneToMany(mappedBy = "provider", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonManagedReference
//    @OrderBy("id ASC")
//    private Set<Article> items;

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
//
//    public Set<Article> getItems() {
//        return items;
//    }
//
//    public void setItems(Set<Article> items) {
//        this.items = items;
//    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }
}
