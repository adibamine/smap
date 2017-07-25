package ma.octo.smap.persistance.domains;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.annotation.Transient;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

/**
 * Created by adib on 17/04/17.
 */
public class WebData {

    @PrimaryKeyColumn(name = "link", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String link;

    @PrimaryKeyColumn(name="createdAt", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date createdAt;

    private String title;
    private String body;
    private String lang;

    public WebData() {
    }

    public WebData(String link, String title, String body, Date date, String lang) {
        this.link = link;
        this.title = title;
        this.body = body;
        this.createdAt = date;
        this.lang = lang;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }


}
