package ma.octo.smap.persistance.repositories;

import ma.octo.smap.persistance.domains.Feed;
import org.springframework.data.cassandra.repository.CassandraRepository;

/**
 * Created by adib on 26/03/17.
 */
public interface FeedRepository extends CassandraRepository<Feed> {

}