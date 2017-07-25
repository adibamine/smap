package ma.octo.smap.persistance.repositories;

import ma.octo.smap.persistance.domains.Post;

/**
 * Created by adib on 09/04/17.
 */
public interface PostRepository {

    Post save(Post post);

}
