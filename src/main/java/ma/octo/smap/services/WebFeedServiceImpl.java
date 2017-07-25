package ma.octo.smap.services;


import ma.octo.smap.persistance.domains.WebFeed;
import ma.octo.smap.persistance.repositories.CassandraWebFeedRepository;
import ma.octo.smap.persistance.repositories.WebFeedRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by adib on 20/04/17.
 */
@Service
public class WebFeedServiceImpl {

    @Autowired
    private CassandraWebFeedRepository webFeedRepository;

    public WebFeedServiceImpl(CassandraWebFeedRepository webFeedRepository) {
        this.webFeedRepository = webFeedRepository;
    }

    //@Override
    public WebFeed save(WebFeed webFeed) {
        return webFeedRepository.save(webFeed);
    }
}
