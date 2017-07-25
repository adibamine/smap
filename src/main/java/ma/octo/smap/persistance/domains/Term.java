package ma.octo.smap.persistance.domains;

import org.springframework.cassandra.core.Ordering;
import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.UUID;

/**
 * Created by adib on 04/04/17.
 */
@Table(value = "terms")
public class Term {

    @PrimaryKeyColumn(name = "id_client", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String id_client;

    @PrimaryKeyColumn(name = "term", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String term;

    @PrimaryKeyColumn(name = "date", ordinal = 0, type = PrimaryKeyType.CLUSTERED, ordering = Ordering.DESCENDING)
    private UUID date;

    private String lang;

    public Term() {}

    public Term(String id_client, String term, String lang, UUID date) {
        this.id_client = id_client;
        this.term = term;
        this.lang = lang;
        this.date = date;
    }

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

    public UUID getDate() {
        return date;
    }

    public void setDate(UUID date) {
        this.date = date;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
