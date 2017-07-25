package ma.octo.smap.services;

import ma.octo.smap.persistance.domains.WebData;
import ma.octo.smap.persistance.repositories.MonitoringRepository;

import java.util.List;

/**
 * Created by adib on 17/04/17.
 */
public class MonitoringServiceImpl implements MonitoringService {

    private final MonitoringRepository monitoringRepository;

    public MonitoringServiceImpl(MonitoringRepository monitoringRepository) {
        this.monitoringRepository = monitoringRepository;
    }

    @Override
    public void Save(WebData webData) {
        monitoringRepository.save(webData);
    }

    @Override
    public List<WebData> findAll(String term_ar, String term_fr) {
        return monitoringRepository.findAll(term_ar, term_fr);
    }

    @Override
    public List<WebData> findAll() {
        return monitoringRepository.findAll();
    }

    @Override
    public WebData find(String url, String lang) {
        return monitoringRepository.find(url, lang);
    }
}
