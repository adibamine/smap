package ma.octo.smap.services;

import ma.octo.smap.persistance.domains.Post;
import ma.octo.smap.persistance.repositories.PostRepository;

/**
 * Created by adib on 09/04/17.
 */
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post save(Post post) {
        return postRepository.save(post);
    }
}
