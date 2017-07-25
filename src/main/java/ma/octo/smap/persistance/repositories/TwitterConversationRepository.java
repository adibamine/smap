package ma.octo.smap.persistance.repositories;

import ma.octo.smap.persistance.domains.TwitterConversationMessage;

import java.util.List;

/**
 * Created by adib on 25/04/17.
 */
public interface TwitterConversationRepository {

    TwitterConversationMessage save(TwitterConversationMessage t);
    List<TwitterConversationMessage> getConversation(String user1Id, String user2Id);
    List<TwitterConversationMessage> getConversations(String userId);


}
