package ma.octo.smap.persistance.repositories;

import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import ma.octo.smap.persistance.domains.Term;
import ma.octo.smap.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by adib on 04/04/17.
 */
@Repository
public class CassandraTermRepository implements TermRepository {

    @Autowired
    private final CassandraTemplate cassandraTemplate;

    public CassandraTermRepository(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    @Override
    public Term save(Term t) {
        return this.cassandraTemplate.insert(t);
    }

    @Override
    public List<Term> findAll() {
        Select select = QueryBuilder.select().from(AppConstants.TERMS);
        return this.cassandraTemplate.select(select, Term.class);
    }
}
