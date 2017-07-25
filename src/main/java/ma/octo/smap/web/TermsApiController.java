package ma.octo.smap.web;

import com.datastax.driver.core.utils.UUIDs;
import facebook4j.FacebookException;
import ma.octo.smap.persistance.domains.Term;
import ma.octo.smap.persistance.domains.Feed;
import ma.octo.smap.persistance.domains.Test;
import ma.octo.smap.services.FeedService;
import ma.octo.smap.services.InsightService;
import ma.octo.smap.services.TermService;
import ma.octo.smap.web.dto.TermDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.social.facebook.api.*;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api")
public class TermsApiController {

    private final FeedService feedService;
    private final TermService termService;
    private final InsightService insightService;

    @Autowired
    public TermsApiController(TermService termService, FeedService feedService, InsightService insightService) {
        this.termService = termService;
        this.feedService = feedService;
        this.insightService = insightService;
    }

    @GetMapping(value = "/terms", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<Term>> getTerms() {
        return new ResponseEntity(
                termService.findAll(), OK);
    }

    @PostMapping(value = "/terms/save", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<Feed> addReportingTerm(@RequestBody TermDTO termDTO) throws IOException {
        String term = termDTO.getTerm();
        String lang = termDTO.getLang();
        termService.save(new Term("attijari", term, lang, UUIDs.timeBased()));
        feedService.searchAndSave(termDTO);
        return new ResponseEntity(OK);
    }

    @GetMapping(value = "/fb/all", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<PagedList<Comment>> fbAll() {
        String accessToken = "EAAYUvbVyNocBAJureNQZAW2rAnAojQiZBveeVfXL6ElshQ4hJ6x6kovZCJYtnyObZCtf3ozVxdx4ZBl4DUUcA9dAMWjxAxw9oT9IXpXITxCFeGOKjZCb0tAbJVMatYYQesXgy0Ix2sZBXKQn2mjaxkcjX5ZBn4oTtHxvcTXY3NZCqhsz8PQfZCJ0LqrI75E7AN4fJEtJSszXQbfQZDZD";
        String rajaApp_accessToken = "EAAYUvbVyNocBAHIuEIVBzRDUirvazbenSs478OL6h1zfCnDELl9ZClWLhzsQ7xsJCHqqmkZBh95qOG0gV7DjOZB40huYCJFzZAV8kxp14ib6myhZBHZAiPhAso4i7aSTy6YDJUicTMVRALUZC9zqKfxWAIK5N4ZB5sZARORvdzyeaMZCtSJHsBWI5zFUS4wzZC7nknAkHmGf6ZAFAQZDZD";
        Facebook facebook = new FacebookTemplate(rajaApp_accessToken, "octo_app_test");
        facebook.feedOperations().getPosts().forEach(post -> facebook.commentOperations().getComments(post.getId()).forEach(comment -> System.out.println(comment.getMessage() + " by " +comment.getFrom().getId())));
        return new ResponseEntity(null, OK);
    }


    @GetMapping(value = "/jedis", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<Test>> test() {
        List<Test> l = new ArrayList<Test>();
        Test t1 = new Test(1,"amine","https://www.w3schools.com/css/trolltunga.jpg");
        Test t2 = new Test(2,"lyna","https://www.planwallpaper.com/static/images/desktop-year-of-the-tiger-images-wallpaper.jpg");
        Test t3 = new Test(3,"rafael","http://allswalls.com/images/tiger-wallpaper-54.jpg");
        Test t4 = new Test(4,"badr","http://cdn.wallpapersafari.com/28/58/PLX4N6.jpg");
        Test t5 = new Test(5,"fatima","https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcTDiu3Io_QQfJmb-jxmIsS-quJz81Xxf5oYbZUw6HMsYIh-YurN");
        Test t6 = new Test(6,"ronaldo","http://www.wallpapersshock.com/uploads/large/tiger/free-hd-tiger-3d-wallpapers-download-pc.jpg");
        Test t7 = new Test(7,"messi","http://www.hdwallpapersphotos.com/wp-content/uploads/2014/12/3d-Tiger-Background-Photos.jpg");
        l.add(t1);
        l.add(t2);
        l.add(t3);
        l.add(t4);
        l.add(t5);
        l.add(t6);
        l.add(t7);

        return new ResponseEntity(l, OK);
    }


}
