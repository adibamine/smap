package ma.octo.smap.persistance.repositories;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import ma.octo.smap.persistance.domains.FeedByClient;
import ma.octo.smap.persistance.domains.TwitterConversationMessage;
import ma.octo.smap.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by adib on 25/04/17.
 */
@Repository
public class CassandraTwitterConversationRepository implements TwitterConversationRepository {

    @Autowired
    private final CassandraTemplate cassandraTemplate;

    public CassandraTwitterConversationRepository(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public TwitterConversationMessage save(TwitterConversationMessage t) {
        return cassandraTemplate.insert(t);
    }

    @Override
    public List<TwitterConversationMessage> getConversation(String user1Id, String user2Id) {
        Select select = QueryBuilder.select().from(AppConstants.TWITTER_CONVERSTIONS_MESSAGES);
        select.where(QueryBuilder.in(AppConstants.SENDER_ID, user1Id,user2Id)).and(
          QueryBuilder.in(AppConstants.RECEIVER_ID, user1Id,user2Id)
        );
        return this.cassandraTemplate.select(select, TwitterConversationMessage.class);
    }

    @Override
    public List<TwitterConversationMessage> getConversations(String user1Id) {

        return null;
    }

}
