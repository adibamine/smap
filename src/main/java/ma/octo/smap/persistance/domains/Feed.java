package ma.octo.smap.persistance.domains;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 * Created by adib on 26/03/17.
 */
@Table(value = "feeds")
public class Feed {

    @PrimaryKey()
    private UUID uuid_feed;
    private String id_client;
    private String id_feed;
    private String term;
    private String plateform;
    private String text_feed;
    private Date date;
    private String fromUser;
    private int fromUserID;
    private int retweetCount;
    private String source_device;
    private String image;

    public Feed() {}

    public UUID getUuid_feed() {
        return uuid_feed;
    }

    public void setUuid_feed(UUID uuid_feed) {
        this.uuid_feed = uuid_feed;
    }

    public String getId_client() {
        return id_client;
    }

    public void setId_client(String id_client) {
        this.id_client = id_client;
    }

    public String getPlateform() {
        return plateform;
    }

    public void setPlateform(String plateform) {
        this.plateform = plateform;
    }

    public String getId_feed() {
        return id_feed;
    }

    public void setId_feed(String id_feed) {
        this.id_feed = id_feed;
    }

    public String getText_feed() {
        return text_feed;
    }

    public void setText_feed(String text_feed) {
        this.text_feed = text_feed;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public int getFromUserID() {
        return fromUserID;
    }

    public void setFromUserID(int fromUserID) {
        this.fromUserID = fromUserID;
    }

    public int getRetweetCount() {
        return retweetCount;
    }

    public void setRetweetCount(int retweetCount) {
        this.retweetCount = retweetCount;
    }

    public String getSource_device() {
        return source_device;
    }

    public void setSource_device(String source_device) {
        this.source_device = source_device;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }

    public String getImage() { return image; }

    public void setImage(String image) { this.image = image; }
}
