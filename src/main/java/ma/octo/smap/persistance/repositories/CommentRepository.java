package ma.octo.smap.persistance.repositories;

import ma.octo.smap.persistance.domains.Comment;
import ma.octo.smap.persistance.domains.CommentByUser;
import ma.octo.smap.persistance.domains.Influencer;

import java.util.List;

/**
 * Created by adib on 08/04/17.
 */
public interface CommentRepository {

    Comment save(Comment comment);
    CommentByUser save(CommentByUser commentByUser);
    List<Influencer> Top5Influencers();
    List<CommentByUser> findUserComments(String id_client, String user_id);
    List<Comment> findCientComments(String id_client);

}
