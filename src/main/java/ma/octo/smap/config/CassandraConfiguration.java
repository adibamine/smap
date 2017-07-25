package ma.octo.smap.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.cassandra.config.java.AbstractCassandraConfiguration;
import org.springframework.data.cassandra.repository.config.EnableCassandraRepositories;

import java.util.Arrays;
import java.util.List;

/**
 * Created by adib on 23/03/17.
 */
@Configuration
@EnableCassandraRepositories
public class CassandraConfiguration extends AbstractCassandraConfiguration {

    @Override
    protected List<String> getStartupScripts() {
        String[] script = {"CREATE KEYSPACE IF NOT EXISTS smap "
                + "WITH durable_writes = true "
                + "AND replication = { 'replication_factor' : 1, 'class' : 'SimpleStrategy' };",
                "CREATE TABLE IF NOT EXISTS smap.feeds ( uuid_feed UUID, id_client text, term text, plateform text, id_feed text, text_feed text,date timestamp,fromUser text,fromUserId int, retweetCount int,source_device varchar, image text, PRIMARY KEY (uuid_feed));",
                "CREATE TABLE IF NOT EXISTS smap.feeds_by_client (id_client text, term text, uuid_feed UUID, fromUser text, date timestamp, id_feed text, link text, plateform text, text_feed text, image text, PRIMARY KEY ((id_client), term, date, uuid_feed)) WITH CLUSTERING ORDER BY (term ASC, date DESC, uuid_feed DESC);",
                "CREATE TABLE IF NOT EXISTS smap.terms (id_client text, term text, date TIMEUUID, lang text, PRIMARY KEY((term, id_client), date)) WITH CLUSTERING ORDER BY (date DESC);",
                "CREATE TABLE IF NOT EXISTS smap.fb_comments (id_client text, language text, sentiment text, id_page text, id_post text, id_comment text, page_name text, text_comment text, date_comment timestamp, user_id text, user_name text, PRIMARY KEY((id_client), language, sentiment, id_comment)) WITH CLUSTERING ORDER BY (language DESC, sentiment DESC, id_comment DESC);",
                "CREATE TABLE IF NOT EXISTS smap.fb_comments_by_user (client_id text, user_id text, comment_id text, id_post text, page_id text, user_name text, page_name text, comment_text text, language text, sentiment text, PRIMARY KEY ((client_id, user_id), comment_id)) WITH CLUSTERING ORDER BY (comment_id DESC);",
                "CREATE TABLE IF NOT EXISTS smap.fb_posts (client_id text, post_id text, createdTime timestamp, fromId text, fromName text, message text, picture text, sharesCount int, reactionsCount int, type text, PRIMARY KEY((client_id), createdTime)) WITH CLUSTERING ORDER BY (createdTime DESC);",
                "CREATE TABLE IF NOT EXISTS smap.twitter_tweets (client_id text, createdAt timestamp, tweet_id text, user_id text, text text, fromName text, isRetweet boolean, PRIMARY KEY((client_id), createdAt, tweet_id )) WITH CLUSTERING ORDER BY (createdAt DESC, tweet_id DESC);",
                "CREATE TABLE IF NOT EXISTS smap.twitter_retweets (client_id text, createdAt timestamp, retweet_id text, user_id text, text text, fromName text, parentTweet_id text, parentUser_name text, parentUser_id text, PRIMARY KEY((client_id), createdAt, retweet_id )) WITH CLUSTERING ORDER BY (createdAt DESC, retweet_id DESC);",
                "CREATE TABLE IF NOT EXISTS smap.web_feeds_by_client ( client_id text, term text, plateform text, title text, body text,createdAt timestamp, author text, image text, link text, PRIMARY KEY ((client_id), term, plateform, createdAt, link)) WITH CLUSTERING ORDER BY (term ASC, plateform asc, createdAt DESC, link ASC);",
                "CREATE TABLE IF NOT EXISTS smap.twitter_conversations_messages (sender_id text, receiver_id text, createdAt timestamp, sender_screen_name text, receiver_screen_name text, message text, PRIMARY KEY((sender_id, receiver_id), createdAt)) WITH CLUSTERING ORDER BY (createdAt DESC);"
                //    "CREATE TABLE IF NOT EXISTS smap.webdata_fr (link text, createdAt timestamp, title text, body text, lang text, PRIMARY KEY((link), createdAt)) WITH CLUSTERING ORDER BY (createdAt DESC);",
              //  "CREATE TABLE IF NOT EXISTS smap.webdata_ar (link text, createdAt timestamp, title text, body text, lang text, PRIMARY KEY((link), createdAt)) WITH CLUSTERING ORDER BY (createdAt DESC);",
             //   "USE smap;",
             //   "DROP INDEX IF EXISTS webdata_fr_index;",
               // "DROP INDEX IF EXISTS webdata_ar_index;",
             //   "CREATE CUSTOM INDEX IF NOT EXISTS webdata_ar_index ON smap.webdata_ar () USING 'com.stratio.cassandra.lucene.Index' WITH OPTIONS = {'refresh_seconds': '1','schema': '{ fields: { title: {type: \"text\", analyzer: \"arabic\"}, body: {type: \"text\", analyzer: \"arabic\"}}}'};",
             //   "CREATE CUSTOM INDEX IF NOT EXISTS webdata_fr_index ON smap.webdata_fr () USING 'com.stratio.cassandra.lucene.Index' WITH OPTIONS = {'refresh_seconds': '1','schema': '{ fields: { title: {type: \"text\", analyzer: \"french\"}, body: {type: \"text\", analyzer: \"french\"}}}'};"
        };

        return Arrays.asList(script);
    }

    @Override
    protected String getKeyspaceName() {
        return "smap";
    }

    @Override
    protected List<String> getShutdownScripts() {
        String[] script = {
               // "DROP INDEX IF EXISTS webdata_ar_index;",
               // "DROP INDEX IF EXISTS webdata_fr_index;",
                "DROP KEYSPACE smap;"
        };
        return Arrays.asList(script);
    }

}