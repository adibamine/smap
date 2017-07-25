package ma.octo.smap.persistance.domains;

import com.datastax.driver.core.utils.UUIDs;
import ma.octo.smap.utils.AppConstants;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by adib on 03/04/17.
 */
@Table(value = "feeds_by_client")
public class FeedByClient {

    @PrimaryKey
    private FeedByClientKey feedByClientKey;

    private String text_feed;

    private String plateform;

    private String fromUser;

    private String link;

    private String image;

    private String id_feed;

    public FeedByClient() {}

    public FeedByClient(Feed feed) {
        FeedByClientKey feedByClientKey = new FeedByClientKey();
        feedByClientKey.setId_client(feed.getId_client());
        feedByClientKey.setUuid_feed(feed.getUuid_feed());
        feedByClientKey.setTerm(feed.getTerm());
        feedByClientKey.setDate(feed.getDate());
        this.setId_feed(feed.getId_feed());
        this.setFeedByClientKey(feedByClientKey);
        this.setFromUser(feed.getFromUser());
        this.setPlateform(feed.getPlateform());
        this.setText_feed(feed.getText_feed());
        this.setLink(AppConstants.TWITTER_LINK + feed.getId_feed());
        this.setImage(feed.getImage());
    }

    public FeedByClient(WebFeed webFeed) {
        FeedByClientKey feedByClientKey = new FeedByClientKey();
        feedByClientKey.setId_client(webFeed.getClient_id());
        feedByClientKey.setUuid_feed(UUIDs.timeBased());
        feedByClientKey.setTerm(webFeed.getTerm());
        feedByClientKey.setDate(webFeed.getCreatedAt());
        this.setFeedByClientKey(feedByClientKey);
        this.setFromUser(webFeed.getAuthor());
        this.setPlateform(webFeed.getPlateform());
        this.setText_feed(webFeed.getTitle());
        this.setLink(webFeed.getLink());
        this.setImage(webFeed.getImage());
    }

    public String getFromUser() {
        return fromUser;
    }

    public void setFromUser(String fromUser) {
        this.fromUser = fromUser;
    }

    public FeedByClientKey getFeedByClientKey() {
        return feedByClientKey;
    }

    public void setFeedByClientKey(FeedByClientKey feedByClientKey) {
        this.feedByClientKey = feedByClientKey;
    }

    public String getText_feed() {
        return text_feed;
    }

    public void setText_feed(String text_feed) {
        this.text_feed = text_feed;
    }

    public String getPlateform() {
        return plateform;
    }

    public void setPlateform(String plateform) {
        this.plateform = plateform;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getId_feed() {
        return id_feed;
    }

    public void setId_feed(String id_feed) {
        this.id_feed = id_feed;
    }
}
