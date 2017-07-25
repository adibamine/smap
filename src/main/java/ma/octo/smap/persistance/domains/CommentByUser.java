package ma.octo.smap.persistance.domains;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

/**
 * Created by adib on 08/04/17.
 */
@Table(value = "fb_comments_by_user")
public class CommentByUser {

    @PrimaryKeyColumn(name="user_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String user_id;

    @PrimaryKeyColumn(name="client_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String client_id;

    @PrimaryKeyColumn(name="comment_id", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private String comment_id;

    private String id_post;
    private String page_id;
    private String user_name;
    private String page_name;
    private String comment_text;
    private String language;
    private String sentiment;

    public CommentByUser() {}

    public CommentByUser(Comment c) {
        this.client_id = c.getId_client();
        this.user_id = c.getUser_id();
        this.page_id = c.getId_page();
        this.comment_id = c.getId_comment();
        this.user_name = c.getUser_name();
        this.id_post = c.getId_post();
        this.page_name = c.getPage_name();
        this.comment_text = c.getText_comment();
        this.language = c.getLanguage();
        this.sentiment = c.getSentiment();
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPage_id() {
        return page_id;
    }

    public void setPage_id(String page_id) {
        this.page_id = page_id;
    }

    public String getComment_id() {
        return comment_id;
    }

    public void setComment_id(String comment_id) {
        this.comment_id = comment_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPage_name() {
        return page_name;
    }

    public void setPage_name(String page_name) {
        this.page_name = page_name;
    }

    public String getComment_text() {
        return comment_text;
    }

    public void setComment_text(String comment_text) {
        this.comment_text = comment_text;
    }

    public String getId_post() {
        return id_post;
    }

    public void setId_post(String id_post) {
        this.id_post = id_post;
    }

    public String getSentiment() {
        return sentiment;
    }

    public void setSentiment(String sentiment) {
        this.sentiment = sentiment;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
