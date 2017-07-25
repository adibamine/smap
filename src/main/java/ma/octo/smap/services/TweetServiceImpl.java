package ma.octo.smap.services;

import ma.octo.smap.persistance.domains.Retweet;
import ma.octo.smap.persistance.domains.Tweet;
import ma.octo.smap.persistance.repositories.TweetRepository;
import ma.octo.smap.utils.SocialMediasApi;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.Query;
import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.TwitterException;

import java.util.List;

/**
 * Created by adib on 16/04/17.
 */
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;


    public TweetServiceImpl(TweetRepository tweetRepository) {
        this.tweetRepository = tweetRepository;
    }

    @Override
    public Tweet save(Tweet t) {
        return tweetRepository.save(t);
    }

    @Override
    public Retweet save(Retweet t) {
        return tweetRepository.saveRetweet(t);
    }

}
