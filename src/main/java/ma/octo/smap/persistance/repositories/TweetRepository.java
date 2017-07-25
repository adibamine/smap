package ma.octo.smap.persistance.repositories;

import ma.octo.smap.persistance.domains.Retweet;
import ma.octo.smap.persistance.domains.Tweet;

/**
 * Created by adib on 16/04/17.
 */

public interface TweetRepository {

    Tweet save(Tweet t);
    Retweet saveRetweet(Retweet t);

}
