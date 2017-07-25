package ma.octo.smap.persistance.domains;

import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

/**
 * Created by adib on 09/04/17.
 */
@Table(value = "fb_posts")
public class Post {

    @PrimaryKey
    private String client_id;

    private String post_id;
    private Date createdTime;
    private String fromId;
    private String fromName;
    private String message;
    private String picture;
    private int sharesCount;
    private int reactionsCount;
    private String type;

    public Post() {
    }

    public String getClient_id() {
        return client_id;
    }

    public String getPost_id() {
        return post_id;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public String getFromId() {
        return fromId;
    }

    public String getFromName() {
        return fromName;
    }

    public String getMessage() {
        return message;
    }

    public String getPicture() {
        return picture;
    }

    public int getSharesCount() {
        return sharesCount;
    }

    public int getReactionsCount() { return reactionsCount; }

    public String getType() {
        return type;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public void setSharesCount(int sharesCount) {
        this.sharesCount = sharesCount;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setReactionsCount(int reactionsCount) { this.reactionsCount = reactionsCount; }

}
