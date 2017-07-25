package ma.octo.smap.config;

import ma.octo.smap.persistance.repositories.*;
import ma.octo.smap.services.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by adib on 04/04/17.
 */
@Configuration
public class RootConfiguration {

    @Bean
    public FeedService feedService(FeedRepository feedRepository,
                                   FeedByClientRepository feedByClientRepository) {
        return new FeedServiceImpl(feedRepository, feedByClientRepository);
    }

    @Bean
    public TermService termService(TermRepository termRepository) {
        return new TermServiceImpl(termRepository);
    }

    @Bean
    public CommentService commentService (CommentRepository commentRepository) {
        return new CommentServiceImpl(commentRepository);
    }

    @Bean
    public PostService postService (PostRepository postRepository) {
        return new PostServiceImpl(postRepository);
    }

    @Bean
    public InsightService insightService () {
        return new InsightServiceImpl();
    }

    @Bean
    public TweetService tweetService(TweetRepository tweetRepository) {
        return new TweetServiceImpl(tweetRepository);
    }

    @Bean
    public MonitoringService webDataService(MonitoringRepository webDataRepository) {
        return new MonitoringServiceImpl(webDataRepository);
    }

    @Bean
    public ConversationService conversationService(TwitterConversationRepository c) {
        return new ConversationServiceImpl(c);
    }

  /*  @Bean
    public Parser_Hespress parser_hespress(CassandraWebFeedRepository webFeedRepository) {
        return new Parser_Hespress(webFeedRepository);
    }
*/
}
