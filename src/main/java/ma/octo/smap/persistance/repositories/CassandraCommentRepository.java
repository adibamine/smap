package ma.octo.smap.persistance.repositories;

import com.datastax.driver.core.PreparedStatement;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import ma.octo.smap.persistance.domains.CommentByUser;
import ma.octo.smap.persistance.domains.Influencer;
import ma.octo.smap.utils.AppConstants;
import org.springframework.data.cassandra.core.CassandraOperations;
import org.springframework.data.cassandra.core.CassandraTemplate;
import ma.octo.smap.persistance.domains.Comment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by adib on 08/04/17.
 */
@Repository
public class CassandraCommentRepository implements CommentRepository{

    private final CassandraTemplate cassandraTemplate;
    private final CassandraOperations cassandraOperations;

    public CassandraCommentRepository(CassandraTemplate cassandraTemplate, CassandraOperations cassandraOperations) {
        this.cassandraTemplate = cassandraTemplate;
        this.cassandraOperations = cassandraOperations;
    }

    @Override
    public Comment save(Comment comment){
        return cassandraTemplate.insert(comment);
    }

    @Override
    public CommentByUser save(CommentByUser commentByUser) {
        return cassandraTemplate.insert(commentByUser);
    }

    @Override
    public List<Influencer> Top5Influencers() {
        ///////////////////////////
        // Code to refactor!    //
        //////////////////////////
        List<Influencer> users = new ArrayList<Influencer>();
        PreparedStatement statement = cassandraTemplate.getSession().prepare("select client_id, user_id, comment_id, comment_text, page_id, page_name, user_name, count(*) as count from fb_comments_by_user where client_id = 'attijari' group by client_id, user_id ALLOW FILTERING;");
        ResultSet results = cassandraTemplate.getSession().execute(statement.bind());
        results.forEach(row -> users.add(new Influencer(row)));
        users.sort(Comparator.comparing(Influencer::getCount).reversed());
        if(users.size()>4)
            return users.subList(0,5);
        else
            return users;
    }

    @Override
    public List<CommentByUser> findUserComments(String id_client, String user_id) {
        Select select = QueryBuilder.select().from(AppConstants.COMMENTS_BY_USER);
        select.where(QueryBuilder.eq(AppConstants.CLIENT_ID, id_client));
        select.where(QueryBuilder.eq(AppConstants.USER_ID, user_id));
        return this.cassandraTemplate.select(select, CommentByUser.class);
    }

    @Override
    public List<Comment> findCientComments(String id_client) {
        Select select = QueryBuilder.select().from(AppConstants.COMMENTS);
        select.where(QueryBuilder.eq(AppConstants.ID_CLIENT, id_client));
        return this.cassandraTemplate.select(select, Comment.class);
    }


}
