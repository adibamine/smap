package ma.octo.smap.persistance.repositories;

import ma.octo.smap.persistance.domains.FeedByClient;

import java.util.List;

/**
 * Created by adib on 04/04/17.
 */
public interface FeedByClientRepository {

    List<FeedByClient> findByTerm(String client, String term);
    FeedByClient save(FeedByClient feedByClient);
    FeedByClient findLatestTweetByTerm(String client, String term);

}
