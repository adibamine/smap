package ma.octo.smap.web;

import facebook4j.FacebookException;
import ma.octo.smap.services.InsightService;
import ma.octo.smap.web.dto.PostDTO;
import ma.octo.smap.web.dto.PostLinkDTO;
import ma.octo.smap.web.dto.TweetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by adib on 14/04/17.
 */
@RestController
@RequestMapping(value = "/api")
public class InsightsApiController {

    private final InsightService insightService;

    @Autowired
    public InsightsApiController(InsightService insightService) {
        this.insightService = insightService;
    }


    @GetMapping(value = "/insights", produces = APPLICATION_JSON_VALUE)
    @ResponseBody
    public String getImpressions(@RequestParam(value = "metric", required = true) String mertric, @RequestParam(value = "startDate", required = true) Long startDate, @RequestParam(value = "endDate", required = true) Long endDate) throws FacebookException {
        if(startDate instanceof Long && endDate instanceof Long)
            return insightService.getImpression(startDate, endDate, mertric).toString();
        else
            return insightService.getImpression().toString();
    }

    @PostMapping(value = "/insights/createPost", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public String createPost(@RequestBody PostDTO postDTO) {
        return insightService.createPost(postDTO).toString();
    }

    @PostMapping(value = "/insights/createPostLink", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public String createPostLink(@RequestBody PostLinkDTO postLinkDTO) {
        return insightService.createPostLink(postLinkDTO).toString();
    }

    @PostMapping(value = "/insights/createTweet", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public String createTweet(@RequestBody TweetDTO tweetDTO) {
        return insightService.createTweet(tweetDTO).toString();
    }


}
