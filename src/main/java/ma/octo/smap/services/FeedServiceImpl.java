package ma.octo.smap.services;

import ma.octo.smap.crawlers.HespressParser;
import ma.octo.smap.crawlers.TelquelParser;
import ma.octo.smap.persistance.domains.Feed;
import ma.octo.smap.persistance.domains.FeedByClient;
import ma.octo.smap.persistance.repositories.FeedByClientRepository;
import ma.octo.smap.persistance.repositories.FeedRepository;
import ma.octo.smap.utils.AppConstants;
import ma.octo.smap.utils.ObjectMapper;
import ma.octo.smap.utils.SocialMediasApi;
import ma.octo.smap.web.dto.TermDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.twitter.api.SearchResults;

import java.io.IOException;
import java.util.*;

/**
 * Created by adib on 28/03/17.
 */
public class FeedServiceImpl implements FeedService{

    private final FeedRepository feedRepository;
    private final FeedByClientRepository feedByClientRepository;

    @Autowired
    private HespressParser parserHespress;

    @Autowired
    private TelquelParser telquelParser;

    @Autowired
    private SocialMediasApi smApi;

    SearchResults twitterResult;

    public FeedServiceImpl(FeedRepository feedRepository, FeedByClientRepository feedByClientRepository) {
        this.feedRepository = feedRepository;
        this.feedByClientRepository = feedByClientRepository;
    }

    @Override
    public void save(Feed f) {
        feedRepository.save(f);
        feedByClientRepository.save(new FeedByClient(f));
    }

    @Override
    public void searchAndSave(TermDTO term) throws IOException {
        long startTime = System.nanoTime();
        twitterResult = smApi.twitter.searchOperations().search(term.getTerm());
        twitterResult.getTweets().forEach(tweet -> save(ObjectMapper.mapFeedFromTwitter(tweet, term.getTerm(), AppConstants.TWITTER)));
        if(term.getLang().equals("ar"))
            parserHespress.parse_link(1,term.getTerm());
        else
            telquelParser.parse_link(term.getTerm());
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println("duration: " + duration );
    }

    @Override
    public List<FeedByClient> findByTerm(String term) {
        return feedByClientRepository.findByTerm("attijari", term);
    }

    @Override
    public FeedByClient findLatestTweetByTerm(String client, String term) {
        return feedByClientRepository.findLatestTweetByTerm(client, term);
    }

}

/*

    //List<SearchResult> youtubeResult;

    Thread monitorTwitter = new Thread(new Runnable() {
        public void run()
        {
            twitterResult = smApi.twitter.searchOperations().search(term);
            twitterResult.getTweets().forEach(tweet -> save(ObjectMapper.mapFeedFromTwitter(tweet, term, AppConstants.TWITTER)));
        }
    });

    Thread monitorYoutube = new Thread(new Runnable() {
        public void run()
        {
            try {
                youtubeResult = smApi.youtube.setQ(term).execute().getItems();
                youtubeResult.forEach(youtubeVideo -> save(ObjectMapper.mapFeedFromYoutube(youtubeVideo, term, AppConstants.YOUTUBE)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    });
*/