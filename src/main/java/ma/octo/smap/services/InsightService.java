package ma.octo.smap.services;

import facebook4j.FacebookException;
import facebook4j.ResponseList;
import facebook4j.internal.org.json.JSONObject;
import ma.octo.smap.web.dto.PostDTO;
import ma.octo.smap.web.dto.PostLinkDTO;
import ma.octo.smap.web.dto.TweetDTO;
import org.mortbay.util.ajax.JSON;

import java.util.List;

/**
 * Created by adib on 12/04/17.
 */
public interface InsightService {

    JSONObject getImpression() throws FacebookException;
    JSONObject getImpression(Long startDate, Long EndDate, String metric);
    JSONObject createPost(PostDTO post);
    JSONObject createPostLink(PostLinkDTO postLinkDTO);
    JSONObject createTweet(TweetDTO tweetDTO);

}
