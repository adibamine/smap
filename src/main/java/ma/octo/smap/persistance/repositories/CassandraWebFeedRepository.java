package ma.octo.smap.persistance.repositories;

import ma.octo.smap.persistance.domains.WebFeed;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by adib on 20/04/17.
 */
@Repository
public class CassandraWebFeedRepository{

    private final CassandraTemplate cassandraTemplate;

    public CassandraWebFeedRepository(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    public WebFeed save(WebFeed webfeed) {
        return cassandraTemplate.insert(webfeed);
    }
}
