package ma.octo.smap.Tasks;

import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.service.exception.BadRequestException;
import facebook4j.FacebookException;
import facebook4j.Reading;
import ma.octo.smap.config.NaiveBayesClassifier;
import ma.octo.smap.crawlers.HespressParser;
import ma.octo.smap.crawlers.TelquelParser;
import ma.octo.smap.persistance.domains.Comment;
import ma.octo.smap.persistance.domains.CommentByUser;
import ma.octo.smap.persistance.domains.FeedByClient;
import ma.octo.smap.services.*;
import ma.octo.smap.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import twitter4j.Paging;
import twitter4j.Query;
import twitter4j.TwitterException;
import java.util.Date;
import java.util.List;


/**
 * Created by adib on 05/04/17.
 */
@Component
public class ScheduledTasks {

    private final TermService termService;
    private final FeedService feedService;
    private final CommentService commentService;
    private final PostService postService;
    private final TweetService tweetService;
    private final ConversationService conversationService;

    @Autowired
    private HespressParser parserHespress;

    @Autowired
    private TelquelParser telquelParser;

    @Autowired
    private SocialMediasApi smApi;

    @Autowired
    private NaiveBayesClassifier naiveBayesClassifier;

    @Autowired
    private NaturalLanguageUnderstandingApi naturalLanguageUnderstandingApi;

    String monitored_term;
    Long sinceID_tweets = 1L;
    Long sinceId_post_comments = 1L;
    Date since_latest_post = new Date(Long.MIN_VALUE);
    Date since_latest_retweets = new Date(Long.MIN_VALUE);


    @Autowired
    public ScheduledTasks(TermService termService, FeedService feedService, CommentService commentService, PostService postService, TweetService tweetService, ConversationService conversationService) {
        this.termService = termService;
        this.feedService = feedService;
        this.commentService = commentService;
        this.postService = postService;
        this.tweetService = tweetService;
        this.conversationService = conversationService;
    }


    @Scheduled(fixedDelay = 5000)
    public void save_fb_comments() throws FacebookException {
        System.out.println("----> starting save_fb_comment");
        String page_id = smApi.facebookApi.getPage().getId();
        String page_name = smApi.facebookApi.getPage().getName();
        smApi.facebookApi.getPosts().forEach(
                post -> {
                    try {
                        smApi.facebookApi.posts().getPostComments(post.getId(), new Reading().since(new Date(sinceId_post_comments))).forEach(
                                comment -> {
                                    try {
                                    String sentimentResult = "";
                                    String language = "";
                                    Double result;
                                    String commentMessage = comment.getMessage();
                                    AnalyzeOptions parameters = new AnalyzeOptions.Builder()
                                            .text(commentMessage)
                                            .features(naturalLanguageUnderstandingApi.features)
                                            .build();
                                    try {
                                        AnalysisResults response = naturalLanguageUnderstandingApi.service
                                                .analyze(parameters)
                                                .execute();

                                        result = response.getSentiment().getDocument().getScore();

                                        if (response.getLanguage().equals((AppConstants.fr)) || response.getLanguage().equals((AppConstants.ar))) {
                                            language = response.getLanguage().toUpperCase();
                                            if (result > 0)
                                                sentimentResult = NaiveBayesClassifier.POSITIVE;
                                            else if (result < 0)
                                                sentimentResult = NaiveBayesClassifier.NEGATIVE;
                                            else
                                                sentimentResult = NaiveBayesClassifier.NEUTRAL;
                                        } else {
                                            language = AppConstants.MA;
                                                List<String> input = TextProcessor.removeStopWords(commentMessage, AppConstants.ALGO_VOWELS, false);
                                            sentimentResult = naiveBayesClassifier.bayes.classify(input).getCategory();
                                        }
                                    } catch (Exception e) {
                                        language = AppConstants.MA;
                                        List<String> input = TextProcessor.removeStopWords(commentMessage, AppConstants.ALGO_VOWELS, false);
                                        sentimentResult = naiveBayesClassifier.bayes.classify(input).getCategory();
                                    }

                                    System.out.println(commentMessage + " -> " + language + " -> " + sentimentResult);
                                    Comment c = ObjectMapper.mapFbCommentToComment(post.getId(), comment, page_id, page_name, language, sentimentResult);
                                    commentService.Save(c);
                                    commentService.Save(new CommentByUser(c));
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                        );
                    } catch (FacebookException e) {
                        e.printStackTrace();
                    }
                }
        );
        sinceId_post_comments = System.currentTimeMillis();
        System.out.println("----> ending save_fb_comment");
    }

//
//    @Scheduled(fixedRate = 10000)
//    public void webMonitoring() {
//        //todo: get articles after specific date
//        System.out.println("----> starting webMonitoring");
//
//        termService.findAll().forEach(term -> {
//            if(term.getLang() == "ar")
//                parserHespress.parse_link(1, term.getTerm());
//            else
//                telquelParser.parse_link(term.getTerm());
//        });
//
//        System.out.println("----> ending webMonitoring");
//    }
//
//    @Scheduled(fixedRate = 15000)
//    public void save_twitter_conversations_messages() throws TwitterException {
//        //Add threads here
//
//        smApi.twitterApi.directMessages().getSentDirectMessages().forEach(directMessage -> {
//            conversationService.save(ObjectMapper.mapDirectMessage(directMessage));
//        });
//
//
//        smApi.twitterApi.directMessages().getDirectMessages().forEach(directMessage -> {
//            conversationService.save(ObjectMapper.mapDirectMessage(directMessage));
//        });
//    }
//
//
//    @Scheduled(fixedRate = 17000)
//    public void saveRetweets() {
//        System.out.println("----> Starting saveRetweets");
//        try {
//            smApi.twitterApi.getUserTimeline().forEach(status -> {
//                try {
//                    smApi.twitterApi.tweets().getRetweets(status.getId()).forEach(retweet -> {
//                        if(retweet.getCreatedAt().after(since_latest_retweets)) {
//                            System.out.println(retweet.getUser().getName() + " : " +retweet.getText());
//                            tweetService.save(ObjectMapper.mapStatusToRetweet(retweet));
//                        }
//                    });
//                } catch (TwitterException e) {
//                    System.out.println("!!Rate limit exceeded!! No more saving retweets");
//                    //e.printStackTrace();
//                }
//
//            });
//
//        } catch (TwitterException e) {
//            e.printStackTrace();
//        }
//        since_latest_retweets = new Date();
//        System.out.println("----> Ending saveRetweets");
//    }
//
//    @Scheduled(fixedRate = 17000)
//    public void saveTweets() {
//        System.out.println("----> starting saving tweets");
//        try {
//            smApi.twitterApi.getUserTimeline(new Paging(sinceID_tweets)).forEach(status ->
//                    {
//                        System.out.println(status.getUser().getName()+","+status.getText());
//                        tweetService.save(ObjectMapper.mapStatusToTweet(status));
//                        sinceID_tweets = (status.getId() > sinceID_tweets) ? status.getId() : sinceID_tweets;
//                    }
//            );
//        } catch (TwitterException e) {
//            e.printStackTrace();
//        }
//
//        System.out.println("----> ending saving tweets");
//    }
//
//
//    @Scheduled(fixedRate = 5000)
//    public void twitterMonitoring() {
//        System.out.println("----> starting twitterMonitoring");
//        termService.findAll().forEach(term -> {
//            FeedByClient LatestFeed = feedService.findLatestTweetByTerm(term.getId_client(), term.getTerm());
//            if (LatestFeed != null && LatestFeed.getId_feed() != null) {
//            monitored_term = LatestFeed.getFeedByClientKey().getTerm();
//                try {
//                    Query query = new Query(monitored_term);
//                    System.out.println("**");
//                    System.out.println(LatestFeed.getId_feed());
//                    System.out.println(LatestFeed.toString());
//                    System.out.println("**");
//                    query.setSinceId(Long.parseLong(LatestFeed.getId_feed()));
//                    smApi.twitterApi.search(query).getTweets().forEach(
//                            status -> {
//                                System.out.println(status.getText());
//                                feedService.save(ObjectMapper.mapFeedFromTwitter(status, monitored_term, AppConstants.TWITTER));
//                            }
//                    );
//                } catch (TwitterException e) {
//                    System.out.println("error " + LatestFeed.getId_feed());
//
//                    e.printStackTrace();
//                }
//            }
//        });
//
//        System.out.println("----> ending twitterMonitoring");
//    }
//
//    @Scheduled(fixedRate = 17000)
//    public void save_fb_posts() {
//        System.out.println("----> starting save_fb_posts");
//        try {
//            smApi.facebookApi.posts().getFeed(new Reading().since(since_latest_post)).forEach(post -> {
//                System.out.println(post.getMessage());
//                postService.save(ObjectMapper.mapFbPostToPost(post));
//                since_latest_post = since_latest_post.before(post.getCreatedTime()) ? post.getCreatedTime() : since_latest_post;
//            });
//        } catch (FacebookException e) {
//            e.printStackTrace();
//        }
//        System.out.println("----> ending save_fb_posts");
//    }
}