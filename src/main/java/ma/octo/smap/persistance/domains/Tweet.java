package ma.octo.smap.persistance.domains;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

/**
 * Created by adib on 16/04/17.
 */
@Table(value = "twitter_tweets")
public class Tweet {

    @PrimaryKeyColumn(name = "client_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String client_id;

    @PrimaryKeyColumn(name="createdAt", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private Date createdAt;

    @PrimaryKeyColumn(name="tweet_id", ordinal = 1, type = PrimaryKeyType.CLUSTERED)
    private String tweet_id;

    private String user_id;
    private String text;
    private String fromName;
    private boolean isRetweet;

    public Tweet() {
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

    public String getTweet_id() {
        return tweet_id;
    }

    public void setTweet_id(String tweet_id) {
        this.tweet_id = tweet_id;
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

    public boolean isRetweet() {
        return isRetweet;
    }

    public void setRetweet(boolean retweet) {
        isRetweet = retweet;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }
}
