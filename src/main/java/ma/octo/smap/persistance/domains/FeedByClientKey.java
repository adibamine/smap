package ma.octo.smap.persistance.domains;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyClass;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

/**
 * Created by adib on 03/04/17.
 */
@PrimaryKeyClass
public class FeedByClientKey implements Serializable {

    @PrimaryKeyColumn(name="id_client", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id_client;

    @PrimaryKeyColumn(name="term", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.ASCENDING)
    private String term;

    @PrimaryKeyColumn(name="date", ordinal = 1, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private Date date;

    @PrimaryKeyColumn(name="uuid_feed", ordinal = 2, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID uuid_feed;

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public UUID getUuid_feed() {
        return uuid_feed;
    }

    public void setUuid_feed(UUID uuid_feed) {
        this.uuid_feed = uuid_feed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
