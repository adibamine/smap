package ma.octo.smap.utils;

import com.datastax.driver.core.utils.UUIDs;
import com.google.api.services.youtube.model.SearchResult;
import ma.octo.smap.persistance.domains.Comment;
import ma.octo.smap.persistance.domains.Feed;
import ma.octo.smap.persistance.domains.Retweet;
import ma.octo.smap.persistance.domains.TwitterConversationMessage;
import org.springframework.social.twitter.api.Tweet;
import twitter4j.DirectMessage;
import twitter4j.Status;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by adib on 26/03/17.
 */
public class ObjectMapper {


    private static final DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");

    public static final Feed mapFeedFromTwitter(Tweet t, String term, String plateform) {
        Feed f = new Feed();
        f.setUuid_feed(UUIDs.timeBased());
        f.setTerm(term);
        f.setFromUser(t.getFromUser());
        f.setFromUserID((int) t.getFromUserId());
        f.setId_client("attijari");
        f.setId_feed(t.getIdStr());
        f.setPlateform(plateform);
        f.setRetweetCount(t.getRetweetCount());
        f.setText_feed(t.getText());
        f.setSource_device("android");
        f.setDate(t.getCreatedAt());
        f.setImage(t.getProfileImageUrl());
        return f;
    }



    public static final Feed mapFeedFromTwitter(Status t, String term, String plateform) {
        Feed f = new Feed();
        f.setUuid_feed(UUIDs.timeBased());
        f.setTerm(term);
        f.setFromUser(t.getUser().getName());
        f.setFromUserID((int) t.getUser().getId());
        f.setId_client("attijari");
        f.setId_feed(String.valueOf(t.getId()));
        f.setPlateform(plateform);
        f.setRetweetCount(t.getRetweetCount());
        f.setText_feed(t.getText());
        f.setSource_device(t.getSource());
        f.setDate(t.getCreatedAt());
        return f;
    }

    public static final Feed mapFeedFromYoutube(SearchResult y, String term, String plateform) {
        Feed f = new Feed();
        f.setUuid_feed(UUIDs.timeBased());
        f.setTerm(term);
        f.setFromUser(y.getSnippet().getChannelTitle());
        //f.setFromUserID(y.getLink().getChannelId());
        f.setId_client("attijari");
        f.setId_feed(y.getId().getVideoId());
        f.setPlateform(plateform);
        f.setText_feed(y.getSnippet().getTitle());
        f.setSource_device("android");
        Date date = null;
        String dateStr = y.getSnippet().getPublishedAt().toString();
        try {
            System.out.println(dateStr);
            date = formatter.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        f.setDate(date);
        return f;
    }

    /*public static ma.octo.smap.persistance.domains.Post mapFbPostToPost(Post p) {
        ma.octo.smap.persistance.domains.Post post = new ma.octo.smap.persistance.domains.Post();
        post.setClient_id("attijari");
        post.setPost_id(p.getObjectId());
        post.setCreatedTime(p.getCreatedTime());
        post.setFromId(p.getFrom().getLink());
        post.setFromName(p.getFrom().getName());
        post.setLink(p.getLink());
        post.setMessage(p.getMessage());
        post.setPicture(p.getPicture());
        post.setSharesCount(p.getShares());
        post.setType(p.getType().name());
        return post;
    }*/

    public static ma.octo.smap.persistance.domains.Post mapFbPostToPost(facebook4j.Post p) {
        ma.octo.smap.persistance.domains.Post post = new ma.octo.smap.persistance.domains.Post();
        post.setClient_id("attijari");
        post.setPost_id(p.getObjectId());
        post.setCreatedTime(p.getCreatedTime());
        post.setFromId(p.getFrom().getId());
        post.setFromName(p.getFrom().getName());
        post.setMessage(p.getMessage());
        post.setPicture((p.getPicture() == null) ? null : p.getPicture().toString());
        post.setSharesCount((p.getSharesCount() == null) ? 0 : p.getSharesCount());
        post.setType(p.getType());
        post.setReactionsCount(0);
        post.setReactionsCount(p.getReactions().size());
        return post;
    }

    public static Comment mapFbCommentToComment(String post_id, facebook4j.Comment c, String page_id, String page_name, String language, String sentiment){
        Comment comment = new Comment();
        comment.setId_client("attijari");
        comment.setId_page(page_id);
        comment.setId_post(post_id);
        comment.setDate_comment(c.getCreatedTime());
        comment.setId_comment(c.getId());
        comment.setPage_name(page_name);
        comment.setText_comment(c.getMessage());
        comment.setUser_id(c.getFrom().getId());
        comment.setUser_name(c.getFrom().getName());
        comment.setLanguage(language);
        comment.setSentiment(sentiment);
        return comment;
    }

    public static ma.octo.smap.persistance.domains.Tweet mapStatusToTweet(Status s) {
        ma.octo.smap.persistance.domains.Tweet tweet = new ma.octo.smap.persistance.domains.Tweet();
        tweet.setClient_id("attijari");
        tweet.setCreatedAt(s.getCreatedAt());
        tweet.setUser_id(String.valueOf(s.getUser().getId()));
        tweet.setFromName(s.getUser().getName());
        tweet.setRetweet(s.isRetweet());
        tweet.setTweet_id(String.valueOf(s.getId()));
        tweet.setText(s.getText());
        return tweet;
    }

    public static Retweet mapStatusToRetweet(Status s) {
        Retweet retweet = new Retweet();
        retweet.setClient_id("attijari");
        retweet.setRetweet_id(String.valueOf(s.getId()));
        retweet.setUser_id(String.valueOf(s.getUser().getId()));
        retweet.setCreatedAt(s.getCreatedAt());
        retweet.setFromName(s.getUser().getName());
        retweet.setParentTweet_id(String.valueOf(s.getRetweetedStatus().getId()));
        retweet.setParentUser_name(s.getRetweetedStatus().getUser().getName());
        retweet.setText(s.getText());
        retweet.setParentUser_id(String.valueOf(s.getRetweetedStatus().getUser().getId()));
        return retweet;
    }

    public static final TwitterConversationMessage mapDirectMessage(DirectMessage directMessage) {
        TwitterConversationMessage twitterConversationMessage = new TwitterConversationMessage();
        twitterConversationMessage.setCreatedAt(directMessage.getCreatedAt());
        twitterConversationMessage.setMessage(directMessage.getText());
        twitterConversationMessage.setReceiver_id(String.valueOf(directMessage.getRecipientId()));
        twitterConversationMessage.setSender_id(String.valueOf(directMessage.getSenderId()));
        twitterConversationMessage.setReceiver_screen_name(directMessage.getRecipientScreenName());
        twitterConversationMessage.setSender_screen_name(directMessage.getSenderScreenName());
        return twitterConversationMessage;
    }

}
