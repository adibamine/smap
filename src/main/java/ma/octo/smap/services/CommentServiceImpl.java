package ma.octo.smap.services;

import facebook4j.FacebookException;
import ma.octo.smap.persistance.domains.CommentByUser;
import ma.octo.smap.persistance.domains.Influencer;
import ma.octo.smap.persistance.repositories.CommentRepository;
import ma.octo.smap.persistance.domains.Comment;
import ma.octo.smap.utils.SocialMediasApi;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adib on 08/04/17.
 */
public class CommentServiceImpl implements CommentService {

    @Autowired
    private SocialMediasApi smApi;

    private final CommentRepository commentRepository;

    public CommentServiceImpl(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }

    @Override
    public void Save(Comment c) {
        commentRepository.save(c);
    }

    @Override
    public void Save(CommentByUser commentByUser) {
        commentRepository.save(commentByUser);
    }

    @Override
    public List<Influencer> Top5Influencers() {
        return commentRepository.Top5Influencers();
    }

    @Override
    public List<CommentByUser> findUserComments(String id_client, String user_id) {
        return commentRepository.findUserComments(id_client, user_id);
    }

    @Override
    public void blockUser(String userID) {
        try {
            smApi.facebookApi.block(Arrays.asList(userID));
        } catch (FacebookException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Comment> findCientComments(String id_client) {
        return commentRepository.findCientComments(id_client);
    }
}
