package ma.octo.smap.persistance.repositories;

import ma.octo.smap.persistance.domains.Post;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by adib on 09/04/17.
 */
@Repository
public class CassandraPostRepository implements PostRepository{

    private final CassandraTemplate cassandraTemplate;

    public CassandraPostRepository(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public Post save(Post post) {
        return cassandraTemplate.insert(post);
    }
}
