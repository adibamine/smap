package ma.octo.smap.persistance.repositories;

import ma.octo.smap.persistance.domains.Retweet;
import ma.octo.smap.persistance.domains.Tweet;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by adib on 16/04/17.
 */
@Repository
public class CassandraTweetRepository implements TweetRepository {

    private final CassandraTemplate cassandraTemplate;

    public CassandraTweetRepository(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }


    @Override
    public Tweet save(Tweet t) {
        return cassandraTemplate.insert(t);
    }

    @Override
    public Retweet saveRetweet(Retweet t) {
        return cassandraTemplate.insert(t);
    }
}
