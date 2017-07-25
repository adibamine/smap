package ma.octo.smap.persistance.repositories;


import com.datastax.driver.core.querybuilder.QueryBuilder;
import com.datastax.driver.core.querybuilder.Select;
import ma.octo.smap.persistance.domains.WebData;
import ma.octo.smap.persistance.domains.WebData_ar;
import ma.octo.smap.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.cassandra.core.CassandraTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by adib on 17/04/17.
 */
@Repository
public class CassandraMonitoringRepository implements MonitoringRepository {


    private final String index_search_ar = "SELECT * FROM webdata_ar WHERE expr(webdata_ar_index, '{query: [{type: \"phrase\", field: \"body\", value: \"%s\"}]}');";

    private final String index_search_fr = "SELECT * FROM webdata_fr WHERE expr(webdata_fr_index, '{query: [{type: \"phrase\", field: \"body\", value: \"%s\"}]}');";

    private final String select_all_ar = "SELECT * from webdata_ar";
    private final String select_all_fr = "SELECT * from webdata_fr";


    private List<WebData> webData_ar = null;
    private List<WebData> webData_fr = null;
    private String term_ar, term_fr;

    @Autowired
    private final CassandraTemplate cassandraTemplate;

    public CassandraMonitoringRepository(CassandraTemplate cassandraTemplate) {
        this.cassandraTemplate = cassandraTemplate;
    }

    Thread monitorWeb_ar = new Thread(new Runnable() {
        public void run()
        {
            System.out.println(String.format(index_search_ar, term_ar));
            webData_ar = cassandraTemplate.select(String.format(index_search_ar, term_ar),WebData.class);
        }
    });

    Thread monitorWeb_fr = new Thread(new Runnable() {
        public void run()
        {
            System.out.println(String.format(index_search_fr, term_fr));
            webData_fr = cassandraTemplate.select(String.format(index_search_fr, term_fr),WebData.class);
        }
    });

    @Override
    public void save(WebData webData) {
        cassandraTemplate.insert(webData);
    }

    @Override
    public List<WebData> findAll(String term_ar, String term_fr) {
        this.term_ar = term_ar;
        this.term_fr = term_fr;
        Thread t_ar = new Thread(monitorWeb_ar);
        Thread t_fr = new Thread(monitorWeb_fr);
        t_ar.start();
        t_fr.start();
        try {
            t_ar.join();
            t_fr.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<WebData> webData = webData_fr;
        webData.addAll(webData_ar);
        return webData_fr;
    }

    @Override
    public List<WebData> findAll() {
        List<WebData> webData = cassandraTemplate.select(select_all_ar, WebData.class);
        List<WebData> webData_fr = cassandraTemplate.select(select_all_fr, WebData.class);
        webData.addAll(webData_fr);
        return webData;
    }

    @Override
    public WebData find(String url, String lang) {
        String db = (lang.equals("ar")) ? AppConstants.WEB_DATA_AR : AppConstants.WEB_DATA_FR;
        Select select = QueryBuilder.select().from(db);
        select.where(QueryBuilder.eq(AppConstants.LINK, url));
        select.limit(1);
        return this.cassandraTemplate.selectOne(select, WebData.class);
    }


}
