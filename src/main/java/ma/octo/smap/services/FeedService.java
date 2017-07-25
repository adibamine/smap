package ma.octo.smap.services;

import ma.octo.smap.persistance.domains.Feed;
import ma.octo.smap.persistance.domains.FeedByClient;
import ma.octo.smap.web.dto.TermDTO;

import java.io.IOException;
import java.util.List;

/**
 * Created by adib on 04/04/17.
 */
public interface FeedService {

    void save(Feed f);
    void searchAndSave(TermDTO term) throws IOException;
    List<FeedByClient> findByTerm(String term);
    FeedByClient findLatestTweetByTerm(String client, String term);

}
