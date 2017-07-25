package ma.octo.smap.persistance.domains;

import org.springframework.data.cassandra.mapping.Table;

import java.util.Date;

/**
 * Created by adib on 17/04/17.
 */
@Table(value = "webdata_ar")
public class WebData_ar extends WebData{

    public WebData_ar() {
    }

    public WebData_ar(String link, String title, String body, Date date, String lang) {
        super(link, title, body, date, lang);
    }



}
