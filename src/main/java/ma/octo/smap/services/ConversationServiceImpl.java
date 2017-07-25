package ma.octo.smap.services;

import facebook4j.*;
import ma.octo.smap.persistance.domains.TwitterConversationMessage;
import ma.octo.smap.persistance.repositories.CommentRepository;
import ma.octo.smap.persistance.repositories.TwitterConversationRepository;
import ma.octo.smap.utils.SocialMediasApi;
import ma.octo.smap.web.dto.ConversationDTO;
import ma.octo.smap.web.dto.FbMessageDTO;
import ma.octo.smap.web.dto.TwitterMessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.*;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.groupingBy;

/**
 * Created by adib on 24/04/17.
 */
public class ConversationServiceImpl implements ConversationService {

    private final TwitterConversationRepository twitterConversationRepository;

    @Autowired
    private SocialMediasApi smApi;

    public ConversationServiceImpl(TwitterConversationRepository twitterConversationRepository) {
        this.twitterConversationRepository = twitterConversationRepository;
    }


    @Override
    public TwitterConversationMessage save(TwitterConversationMessage t) {
        return twitterConversationRepository.save(t);
    }

    @Override
    public InboxResponseList<Conversation> getFacebookConversations() {
        try {
            return smApi.facebookApi.conversations().getConversations();
        } catch (FacebookException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PagableList<Message> getFacebookConversation(ConversationDTO c) {
        try {
            return smApi.facebookApi.conversations().getConversation(c.getId()).getMessages();
        } catch (FacebookException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public PagableList<Message> getFacebookConversation(FbMessageDTO m) {
        try {
            return smApi.facebookApi.conversations().getConversation(m.getConversationId()).getMessages();
        } catch (FacebookException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String sendFacebookMessage(FbMessageDTO m) {
        try {
            return smApi.facebookApi.conversations().answerConversation(m.getConversationId(), m.getMessage());
        } catch (FacebookException e) {
            e.printStackTrace();
        }
        return "error";
    }

    /*
    @Override
    public Map<Long, List<DirectMessage>> getTwitterConversations() {
        try {
            return smApi.twitterApi.getDirectMessages().stream().collect(
                    groupingBy(
                            DirectMessage::get
                    ));
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return null;
        return null;
    }
*/

    private boolean checkEquality(DirectMessage directMessage, String name) {
        return (directMessage.getSenderScreenName().equals(name)) || (directMessage.getRecipientScreenName().equals(name));
    }

    @Override
    public DirectMessage sendTwitterMessage(TwitterMessageDTO m) {
        try {
            return smApi.twitterApi.directMessages().sendDirectMessage(m.getConversationId(), m.getMessage());
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<TwitterConversationMessage> getConversation(String user1Id, String user2Id) {
        return twitterConversationRepository.getConversation(user1Id, user2Id);
    }

    @Override
    public Map<String, List<DirectMessage>> getConversations() {
        ///////////////////////////
        // VB Code to refactor! //
        //////////////////////////

        try {
            return smApi.twitterApi.getDirectMessages().stream().collect(
                    groupingBy(
                            DirectMessage::getSenderScreenName
                    ));
        } catch (TwitterException e) {
            e.printStackTrace();
        }
        return null;
    }


}
