package ma.octo.smap.services;

import ma.octo.smap.persistance.domains.Comment;
import ma.octo.smap.persistance.domains.CommentByUser;
import ma.octo.smap.persistance.domains.Influencer;

import java.util.List;

/**
 * Created by adib on 08/04/17.
 */
public interface CommentService {

    void Save(Comment comment);
    void Save(CommentByUser commentByUser);
    List<Influencer> Top5Influencers();
    List<CommentByUser> findUserComments(String id_client, String user_id);
    void blockUser(String userID);
    List<Comment> findCientComments(String id_client);

}
