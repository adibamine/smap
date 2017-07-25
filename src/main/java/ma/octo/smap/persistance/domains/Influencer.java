package ma.octo.smap.persistance.domains;

import com.datastax.driver.core.Row;

/**
 * Created by adib on 08/04/17.
 */
public class Influencer {

    private String client_id;
    private String user_id;
    private String user_name;
    private Long count;

    public Influencer(Row row) {
        this.client_id = row.getString("client_id");
        this.user_id = row.getString("user_id");
        this.user_name = row.getString("user_name");
        this.count = row.getLong("count");
    }

    public String getClient_id() {
        return client_id;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public Long getCount() {
        return count;
    }
}
