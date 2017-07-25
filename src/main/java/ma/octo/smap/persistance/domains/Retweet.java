package ma.octo.smap.persistance.domains;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

/**
 * Created by adib on 16/04/17.
 */
@Table(value = "twitter_retweets")
public class Retweet {

    @PrimaryKeyColumn(name = "client_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String client_id;

    @PrimaryKeyColumn(name="createdAt", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private Date createdAt;

    @PrimaryKeyColumn(name="retweet_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String retweet_id;

    private String user_id;
    private String text;
    private String fromName;
    private String parentTweet_id;
    private String parentUser_name;
    private String parentUser_id;

    public Retweet() {
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getRetweet_id() {
        return retweet_id;
    }

    public void setRetweet_id(String retweet_id) {
        this.retweet_id = retweet_id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public String getParentTweet_id() {
        return parentTweet_id;
    }

    public void setParentTweet_id(String parentTweet_id) {
        this.parentTweet_id = parentTweet_id;
    }

    public String getParentUser_name() {
        return parentUser_name;
    }

    public void setParentUser_name(String parentUser_name) {
        this.parentUser_name = parentUser_name;
    }

    public String getParentUser_id() {
        return parentUser_id;
    }

    public void setParentUser_id(String parentUser_id) {
        this.parentUser_id = parentUser_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
