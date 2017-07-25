package ma.octo.smap.persistance.domains;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

/**
 * Created by adib on 20/04/17.
 */
@Table(value = "web_feeds_by_client")
public class WebFeed {


    @PrimaryKeyColumn(name = "client_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String client_id;

    @PrimaryKeyColumn(name="term", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private String term;

    @PrimaryKeyColumn(name="plateform", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String plateform;

    @PrimaryKeyColumn(name="createdAt", ordinal = 2, type = PrimaryKeyType.CLUSTERED)
    private Date createdAt;

    @PrimaryKeyColumn(name="link", ordinal = 3, type = PrimaryKeyType.CLUSTERED)
    private String link;

    String title;
    String body;
    String author;
    String image;

    public WebFeed() {}

    public WebFeed(String client_id, String term, String plateform, Date createdAt, String link, String title, String body, String author, String image) {
        this.client_id = client_id;
        this.term = term;
        this.plateform = plateform;
        this.createdAt = createdAt;
        this.link = link;
        this.title = title;
        this.body = body;
        this.author = author;
        this.image = image;
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getPlateform() {
        return plateform;
    }

    public void setPlateform(String plateform) {
        this.plateform = plateform;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "WebFeed{" +
                "client_id='" + client_id + '\'' +
                ", term='" + term + '\'' +
                ", plateform='" + plateform + '\'' +
                ", createdAt=" + createdAt +
                ", link='" + link + '\'' +
                ", title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", author='" + author + '\'' +
                ", image='" + image + '\'' +
                '}';
    }
}
