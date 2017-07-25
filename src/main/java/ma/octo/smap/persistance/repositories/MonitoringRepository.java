package ma.octo.smap.persistance.repositories;

import ma.octo.smap.persistance.domains.WebData;
import ma.octo.smap.persistance.domains.WebData_ar;

import java.util.List;

/**
 * Created by adib on 17/04/17.
 */
public interface MonitoringRepository {

    void save(WebData webData);
    List<WebData> findAll(String term_ar, String term_fr);
    List<WebData> findAll();
    WebData find(String url, String lang);

}
