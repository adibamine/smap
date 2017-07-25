package ma.octo.smap.crawlers;

import ma.octo.smap.persistance.domains.FeedByClient;
import ma.octo.smap.persistance.domains.WebFeed;
import ma.octo.smap.persistance.repositories.CassandraWebFeedRepository;
import ma.octo.smap.persistance.repositories.FeedByClientRepository;
import ma.octo.smap.utils.AppConstants;
import ma.octo.smap.utils.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Created by adib on 21/04/17.
 */
@Service
public class TelquelParser {

    @Autowired
    private CassandraWebFeedRepository webFeedRepository;

    @Autowired
    private FeedByClientRepository feedByClientRepository;

    private final String URL = "http://telquel.ma/?s=";
    private final String url = "http://telquel.ma/page/%s?s=%s";
    private final String CSS_QUERY_PARSE_LINK = "div.page-pager";
    private final String CSS_QUERY_PARSE_ARTICLE_CONTENT = "div.article-small-block";
    private final String CSS_QUERY_PARSE_MAIN_ARTICLE_CONTENT = "div.main-article-content";
    private final String TELQUEL = "telquel";


    private String term;



    public void parse_link(String term) {
        this.term = term;
        System.out.println("starting hespressParser");
        String current_link = URL + term;
        parse_page(current_link);
        System.out.println("ending hespressParser");
    }

    public void parse_page(String link) {
        if(link == "")
            return;
        System.out.println("starting parsing :"+link);
        Document doc = null;
        try {
            doc = Jsoup.connect(link).get();
            Elements main_article_content = doc.select(CSS_QUERY_PARSE_ARTICLE_CONTENT);
            Elements next_page = doc.select(CSS_QUERY_PARSE_LINK);
            for (Element article : main_article_content) {
                parseArticle(article.childNode(1).childNode(1).childNode(1).attr("href"));
            }
            System.out.println("finishing parsing "+url);
            for (Element nextPage : next_page) {
                parse_page(nextPage.attr(AppConstants.HREF));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void parseArticle(String url) {
        Document doc = null;
        try {
            doc = Jsoup.connect(url).get();
            Elements main_article_content = doc.select(CSS_QUERY_PARSE_MAIN_ARTICLE_CONTENT);
            for (Element article : main_article_content) {
                String title = article.childNode(1).childNode(0).toString();
                String date1 = article.childNode(5).childNode(1).childNode(1).childNode(0).toString();
                String date2 = article.childNode(5).childNode(1).childNode(3).childNode(1).childNode(0).toString();
                String date3 = article.childNode(5).childNode(1).childNode(3).childNode(3).childNode(0).toString();
                String date = date1 + " " +date3 + " " + date2;
                Date createdAt = getDate(date.trim());
                String image;
                String body;
                try {
                    image = article.childNode(3).childNode(3).attr(AppConstants.SRC);
                }catch (IndexOutOfBoundsException e) {
                    try {
                        image = article.childNode(3).childNode(1).attr(AppConstants.SRC);
                    }catch (IndexOutOfBoundsException i) {
                        image = null;
                    }
                }
                try {
                    body = article.childNode(7).childNode(3).childNode(0).toString();
                }catch (IndexOutOfBoundsException e) {
                    try {
                        body = article.childNode(9).childNode(1).toString();
                    } catch (IndexOutOfBoundsException in) {
                        body = article.childNode(7).childNode(1).childNode(0).toString();
                    }
                }
                String author = article.childNode(5).childNode(3).childNode(3).childNode(1).childNode(3).childNode(1).childNode(0).toString();
                WebFeed webFeed = new WebFeed();
                webFeed.setTitle(title);
                webFeed.setCreatedAt(createdAt);
                webFeed.setAuthor(author);
                webFeed.setBody(body);
                webFeed.setClient_id("attijari");
                webFeed.setLink(url);
                webFeed.setTerm(term);
                webFeed.setImage(image);
                webFeed.setCreatedAt(createdAt  );
                webFeed.setPlateform(TELQUEL);
                webFeedRepository.save(webFeed);
                feedByClientRepository.save(new FeedByClient(webFeed));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Date getDate(String date) {
        DateFormat df = new SimpleDateFormat("MMM d yyyy hh:mm", Locale.FRENCH);
        Date result = null;
        try {
            result = df.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return result;
    }

}
