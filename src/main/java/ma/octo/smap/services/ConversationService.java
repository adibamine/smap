package ma.octo.smap.services;

import facebook4j.Conversation;
import facebook4j.InboxResponseList;
import facebook4j.Message;
import facebook4j.PagableList;
import ma.octo.smap.persistance.domains.TwitterConversationMessage;
import ma.octo.smap.web.dto.ConversationDTO;
import ma.octo.smap.web.dto.FbMessageDTO;
import ma.octo.smap.web.dto.TwitterMessageDTO;
import twitter4j.DirectMessage;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Created by adib on 24/04/17.
 */
public interface ConversationService {

    TwitterConversationMessage save(TwitterConversationMessage t);
    InboxResponseList<Conversation> getFacebookConversations();
    PagableList<Message> getFacebookConversation(ConversationDTO c);
    PagableList<Message> getFacebookConversation(FbMessageDTO m);
    String sendFacebookMessage(FbMessageDTO c);
    //Map<Long, List<DirectMessage>> getTwitterConversations();
    //List<DirectMessage> getTwitterConversation(String name);
    DirectMessage sendTwitterMessage(TwitterMessageDTO c);
    List<TwitterConversationMessage> getConversation(String user1Id, String user2Id);
    Map<String, List<DirectMessage>> getConversations();


}
