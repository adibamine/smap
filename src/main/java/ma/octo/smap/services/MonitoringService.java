package ma.octo.smap.services;

import ma.octo.smap.persistance.domains.WebData;

import java.util.List;

/**
 * Created by adib on 17/04/17.
 */
public interface MonitoringService {

    void Save(WebData webData);
    List<WebData> findAll(String term_ar, String term_fr);
    List<WebData> findAll();
    WebData find(String url, String lang);

}
