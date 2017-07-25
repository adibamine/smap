package ma.octo.smap.web;

import ma.octo.smap.persistance.domains.Feed;
import ma.octo.smap.services.FeedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * Created by adib on 28/03/17.
 */
@RestController
@RequestMapping(value = "/api")
public class FeedsApiController {

    private final FeedService feedService;

    @Autowired
    public FeedsApiController(FeedService feedService) {
        this.feedService = feedService;
    }

    @GetMapping(value = "/feeds/{term}", produces = APPLICATION_JSON_VALUE)
    @ResponseStatus(OK)
    public ResponseEntity<List<Feed>> getFeedsOfTerm(@PathVariable String term) {
        return new ResponseEntity(feedService.findByTerm(term), OK);
    }

}
