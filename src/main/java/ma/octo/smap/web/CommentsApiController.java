package ma.octo.smap.web;

import ma.octo.smap.config.NaiveBayesClassifier;
import ma.octo.smap.persistance.domains.Comment;
import ma.octo.smap.persistance.domains.CommentByUser;
import ma.octo.smap.services.CommentService;
import ma.octo.smap.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.json.Json;
import javax.json.JsonArray;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by adib on 08/04/17.
 */
@RestController
@RequestMapping(value = "/api/comments")
public class CommentsApiController {

    private final CommentService commentService;

    @Autowired
    public CommentsApiController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping(value = "/topInfluencers", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<CommentByUser>> topInfluencers() {
        return new ResponseEntity(commentService.Top5Influencers(), OK);
    }

    @GetMapping(value = "/{id_client}/{user_id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<CommentByUser>> getFeedsOfTerm(@PathVariable String id_client, @PathVariable String user_id) {
        return new ResponseEntity(commentService.findUserComments(id_client, user_id), OK);
    }

    @GetMapping(value = "/blockUser/{user_id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<Object> blockUser(@PathVariable String user_id) {
        commentService.blockUser(user_id);
        return new ResponseEntity( OK);
    }

    @GetMapping(value = "/insights/{user_id}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<String> findClientComments(@PathVariable String user_id) {
        ///////////////////////////
        // VB Code to refactor! //
        //////////////////////////
        Map<String, Long> sentimentCount = commentService.findCientComments(user_id).stream().collect(
                Collectors.groupingBy(Comment::getSentiment, Collectors.counting()));
        System.out.println(sentimentCount);
        Map<String, Long> sentimentByLangCount = commentService.findCientComments(user_id).stream().collect(
                Collectors.groupingBy(Comment::getLanguage, Collectors.counting()));
        sentimentByLangCount.forEach((sentiment, count) -> sentimentCount.put(sentiment, count));

        System.out.println(sentimentCount);
        System.out.println(sentimentByLangCount);

        JsonArray json = Json.createArrayBuilder()
                .add(Json.createObjectBuilder()
                        .add(AppConstants.NAME, AppConstants.POSITIFS)
                        .add(AppConstants.VALUE, sentimentCount.get(NaiveBayesClassifier.POSITIVE)))
                .add(Json.createObjectBuilder()
                        .add(AppConstants.NAME, AppConstants.NEGATIFS)
                        .add(AppConstants.VALUE, sentimentCount.get(NaiveBayesClassifier.NEGATIVE)))
                .add(Json.createObjectBuilder()
                        .add(AppConstants.NAME, AppConstants.AUTRES)
                        .add(AppConstants.VALUE, sentimentCount.get(NaiveBayesClassifier.NEUTRAL)))
                .add(Json.createObjectBuilder()
                        .add(AppConstants.NAME, AppConstants.FR)
                        .add(AppConstants.VALUE, sentimentCount.get(AppConstants.FR)))
                .add(Json.createObjectBuilder()
                        .add(AppConstants.NAME, AppConstants.MA)
                        .add(AppConstants.VALUE, sentimentCount.get(AppConstants.MA)))
                .add(Json.createObjectBuilder()
                        .add(AppConstants.NAME, AppConstants.AR)
                        .add(AppConstants.VALUE, sentimentCount.get(AppConstants.AR)))
                .build();


        return new ResponseEntity( json.toString(), OK);
    }

}
