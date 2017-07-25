package ma.octo.smap.services;

import ma.octo.smap.persistance.domains.Retweet;
import ma.octo.smap.persistance.domains.Tweet;
import twitter4j.Status;

import java.util.List;

/**
 * Created by adib on 16/04/17.
 */
public interface TweetService {

    Tweet save(Tweet t);
    Retweet save(Retweet t);

}
