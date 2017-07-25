package ma.octo.smap.persistance.domains;

import org.springframework.cassandra.core.PrimaryKeyType;
import org.springframework.data.cassandra.mapping.PrimaryKeyColumn;
import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

/**
 * Created by adib on 25/04/17.
 */
@Table(value = "twitter_conversations_messages")
public class TwitterConversationMessage {

    @PrimaryKeyColumn(name = "sender_id", ordinal = 0, type = PrimaryKeyType.PARTITIONED)
    private String sender_id;

    @PrimaryKeyColumn(name="receiver_id", ordinal = 1, type = PrimaryKeyType.PARTITIONED)
    private String receiver_id;

    @PrimaryKeyColumn(name="createdAt", ordinal = 0, type = PrimaryKeyType.CLUSTERED)
    private Date createdAt;


    private String sender_screen_name;

    private String receiver_screen_name;

    private String message;


    public TwitterConversationMessage() {
    }

    public String getSender_id() {
        return sender_id;
    }

    public void setSender_id(String sender_id) {
        this.sender_id = sender_id;
    }

    public String getReceiver_id() {
        return receiver_id;
    }

    public void setReceiver_id(String receiver_id) {
        this.receiver_id = receiver_id;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public String getSender_screen_name() {
        return sender_screen_name;
    }

    public void setSender_screen_name(String sender_screen_name) {
        this.sender_screen_name = sender_screen_name;
    }

    public String getReceiver_screen_name() {
        return receiver_screen_name;
    }

    public void setReceiver_screen_name(String receiver_screen_name) {
        this.receiver_screen_name = receiver_screen_name;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
