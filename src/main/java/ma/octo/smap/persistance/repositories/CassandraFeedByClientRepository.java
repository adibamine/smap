package ma.octo.smap.persistance.repositories;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import ma.octo.smap.persistance.domains.FeedByClient;
import ma.octo.smap.utils.AppConstants;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by adib on 04/04/17.
 */
@Repository
public class CassandraFeedByClientRepository implements FeedByClientRepository{

    private final CassandraTemplate cassandraTemplate;

    public CassandraFeedByClientRepository(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public List<FeedByClient> findByTerm(String client, String term) {
        Select select = QueryBuilder.select().from(AppConstants.FEEDS_BY_CLIENT);
        select.where(QueryBuilder.eq(AppConstants.ID_CLIENT, client));
        select.where(QueryBuilder.eq(AppConstants.TERM, term));
        return this.cassandraTemplate.select(select, FeedByClient.class);
    }

    @Override
    public FeedByClient findLatestTweetByTerm(String client, String term) {
        Select select = QueryBuilder.select().from(AppConstants.FEEDS_BY_CLIENT);
        select.where(QueryBuilder.eq(AppConstants.ID_CLIENT, client));
        select.where(QueryBuilder.eq(AppConstants.TERM, term));
        select.limit(1);
        return this.cassandraTemplate.selectOne(select, FeedByClient.class);
    }

    @Override
    public FeedByClient save(FeedByClient feedByClient) {
        return this.cassandraTemplate.insert(feedByClient);
    }

}
