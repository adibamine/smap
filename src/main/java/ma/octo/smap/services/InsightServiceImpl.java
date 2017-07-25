package ma.octo.smap.services;

import facebook4j.*;
import facebook4j.auth.AccessToken;
import facebook4j.internal.http.RequestMethod;
import facebook4j.internal.org.json.JSONException;
import facebook4j.internal.org.json.JSONObject;
import ma.octo.smap.utils.SocialMediasApi;
import ma.octo.smap.web.dto.PostDTO;
import ma.octo.smap.web.dto.PostLinkDTO;
import ma.octo.smap.web.dto.TweetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import twitter4j.*;
import twitter4j.Paging;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;

/**
 * Created by adib on 12/04/17.
 */
public class InsightServiceImpl implements InsightService{

    @Autowired
    private SocialMediasApi smApi;

    public final String RQ_PAGE_IMPRESSIONS = "424324641100156/insights?until=%s&since=%s&period=day&metric=%s&pretty=0";
    public final String ID = "id";

    public InsightServiceImpl() {}


    @Override
    public JSONObject getImpression() {
        return getResults("424324641100156/insights/page_impressions/day");
    }

    @Override
    public JSONObject getImpression(Long startDate, Long endDate, String metric) {
        String request = String.format(RQ_PAGE_IMPRESSIONS,endDate,startDate, metric);
        return getResults(request);
    }

    @Override
    public JSONObject createPost(PostDTO post) {
        PostUpdate newPost = null;
        try {
            newPost = new PostUpdate(new URL(post.getLink()))
                    .picture(new URL(post.getPicture()))
                    .name(post.getName())
                    .caption(post.getCaption())
                    .description(post.getDescription());
            return new JSONObject().put("id", smApi.facebookApi.postFeed(newPost));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JSONObject createPostLink(PostLinkDTO postLink) {
        try {
            return new JSONObject().put(ID, smApi.facebookApi.postLink(new URL(postLink.getLink()), postLink.getTitle()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public JSONObject createTweet(TweetDTO tweetDTO) {
        try {
            return new JSONObject().put(ID, smApi.twitterApi.updateStatus(tweetDTO.getText()).getId());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    public JSONObject getResults(String request) {
        JSONObject responseList = null;
        BatchRequests<BatchRequest> batch = new BatchRequests<BatchRequest>();
        batch.add(new BatchRequest(RequestMethod.GET, request));
        List<BatchResponse> results = null;
        try {
            results = smApi.facebookApi.executeBatch(batch);
            System.out.println(results.get(0).asString());
            responseList = results.get(0).asJSONObject();
        } catch (FacebookException e) {
            e.printStackTrace();
        }
        return responseList;
    }


}
