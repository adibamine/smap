package ma.octo.smap.utils;

import facebook4j.FacebookException;
import facebook4j.FacebookFactory;
import facebook4j.auth.AccessToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.impl.FacebookTemplate;
import org.springframework.social.twitter.api.Twitter;
import org.springframework.social.twitter.api.impl.TwitterTemplate;
import org.springframework.stereotype.Component;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;


/**
 * Created by adib on 28/03/17.
 */
@Component
public class SocialMediasApi {

    @Autowired
    private Environment env;

    private static final String FB_RAJAAPP_TEST = "facebook.rajaapp.accessToken";
    private static final String OCTO_APP_TEST = "octo_app_test";
    private static final String FACEBOOK_API_ACCESSTOKEN = "facebookApi.accessToken";
    private static final String TWITTER_CLIENT_KEY = "twitter.clientKey";


    public Facebook facebook;
    public facebook4j.Facebook facebookApi;
    public twitter4j.Twitter twitterApi;
    public Twitter twitter;


    @Bean
    public Facebook getFacebookTemplate() {
        facebook = new FacebookTemplate(env.getProperty(FB_RAJAAPP_TEST), OCTO_APP_TEST);
        return this.facebook;
    }

    @Bean
    public facebook4j.Facebook getFacebookApiTemplate() throws FacebookException {
        facebookApi = new FacebookFactory().getInstance();
        AccessToken access_token = new AccessToken(env.getProperty(FACEBOOK_API_ACCESSTOKEN));
        facebookApi.setOAuthAccessToken(access_token);
        return this.facebookApi;
    }

    @Bean
    public twitter4j.Twitter getTwitterApiTemplate() throws TwitterException {
        twitterApi = TwitterFactory.getSingleton();
        return this.twitterApi;
    }

    @Bean
    public Twitter getTwitterTemplate() {
        twitter = new TwitterTemplate(env.getProperty(TWITTER_CLIENT_KEY));
        return this.twitter;
    }

}

/*

    private static final long NUMBER_OF_VIDEOS_RETURNED = 25;
    private static YouTube youtube_service;
    private static final String PROPERTIES_FILENAME = "youtube.properties";

    @Bean
    public YouTube.Search.List getYoutubeTemplate() {
        Properties properties = new Properties();
        try {
            InputStream in = YouTube.Search.class.getResourceAsStream("/" + PROPERTIES_FILENAME);
            properties.load(in);

        } catch (IOException e) {
            System.err.println("There was an error reading " + PROPERTIES_FILENAME + ": " + e.getCause()
                    + " : " + e.getMessage());
            System.exit(1);
        }

        try {
            youtube_service = new YouTube.Builder(YoutubeAuth.HTTP_TRANSPORT, YoutubeAuth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("youtube-cmdline-search-sample").build();

            youtube = youtube_service.search().list("id,snippet");

            String apiKey = properties.getProperty("youtube.apikey");
            youtube.setKey(apiKey);

            youtube.setType("video");

            // To increase efficiency, only retrieve the fields that the
            // application uses.
            youtube.setFields("items(id/kind,id/videoId,snippet/title,snippet/channelTitle,snippet/thumbnails/default/url,snippet/publishedAt)");
            youtube.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            return this.youtube;
        }


    }
}
*/