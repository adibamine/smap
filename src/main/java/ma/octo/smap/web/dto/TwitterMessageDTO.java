package ma.octo.smap.web.dto;

/**
 * Created by adib on 24/04/17.
 */
public class TwitterMessageDTO {

    private String conversationId;
    private String message;

    public String getConversationId() {
        return conversationId;
    }

    public void setConversationId(String conversationId) {
        this.conversationId = conversationId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

}
