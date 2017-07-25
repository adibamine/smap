package ma.octo.smap.crawlers;

import ma.octo.smap.persistance.domains.FeedByClient;
import ma.octo.smap.persistance.domains.WebFeed;
import ma.octo.smap.persistance.repositories.CassandraWebFeedRepository;
import ma.octo.smap.persistance.repositories.FeedByClientRepository;
import ma.octo.smap.utils.AppConstants;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Created by adib on 20/04/17.
 */
@Service
public class HespressParser {

    @Autowired
    private CassandraWebFeedRepository webFeedRepository;

    @Autowired
    private FeedByClientRepository feedByClientRepository;

    private String term;

    private static Locale arabicLocale = new Locale.Builder().setLanguageTag("ar-SA-u-nu-arab").build();
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE d MMMM uuuu - HH:mm",
            arabicLocale);

    private static final Map<String, String> MonthsTranslate;
    static
    {
        Map<String,String> MonthsTranslateInitialiser = new HashMap<String,String>();
        MonthsTranslateInitialiser.put("يناير","janvier");
        MonthsTranslateInitialiser.put("فبراير","february");
        MonthsTranslateInitialiser.put("مارس","march");
        MonthsTranslateInitialiser.put("أبريل","april");
        MonthsTranslateInitialiser.put("ماي","may");
        MonthsTranslateInitialiser.put("يونيو","june");
        MonthsTranslateInitialiser.put("يوليوز","july");
        MonthsTranslateInitialiser.put("غشت","august");
        MonthsTranslateInitialiser.put("شتنبر","september");
        MonthsTranslateInitialiser.put("أكتوبر","october");
        MonthsTranslateInitialiser.put("نونبر","november");
        MonthsTranslateInitialiser.put("دجنبر","december");
        MonthsTranslate = Collections.unmodifiableMap(MonthsTranslateInitialiser);
    }


    private final String url = "http://www.hespress.com/sport/index.%s.html";
    private final String CSS_QUERY_PARSE_LINK = "a[href^=http://www.hespress.com/sport/index.]:not([class])";
    private final String CSS_QUERY_PARSE_PAGE = "a[href^=/sport/]";
    private final String CSS_QUERY_PARSE_ARTICLE = "div#article_holder";
    private final String CSS_QUERY_PARSE_ARTICLE_P = "div#article_holder p";
    private final String HESPRESS_LINK = "http://www.hespress.com";
    private final String HESPRESS = "hespress";



    public void parse_link(int index, String term) {
        if(index>20 )
            return;
        this.term = term;
        System.out.println("start");
        Document doc = null;
        try {
            doc = Jsoup.connect(String.format(url,index)).get();
            Elements links_pages = doc.select(CSS_QUERY_PARSE_LINK);
            parse_page(String.format(url,index));
            for (Element link : links_pages) {
                parse_page(link.attr(AppConstants.HREF));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("end");
        parse_link(index+10, term);
    }

    public void parse_page(String url_) {
        System.out.println("Starting parsing "+url_);
        try {
            Document doc = Jsoup.connect(url_).get();
            Elements links_articles = doc.select(CSS_QUERY_PARSE_PAGE);
            for (Element link : links_articles) {
                if(link.text().contains(term))
                    parse_article(HESPRESS_LINK + link.attr(AppConstants.HREF));
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Finishing parsing "+url_);

    }

    public void parse_article(String url) {
        System.out.println("starting parsing article "+url);
        try {
            Document doc = Jsoup.connect(url).get();
            Elements article_holder = doc.select(CSS_QUERY_PARSE_ARTICLE);
            Elements article_holder_body = doc.select(CSS_QUERY_PARSE_ARTICLE_P);
            for (Element article : article_holder) {
                String title = article.childNode(1).childNode(0).toString();
                String image = article.childNode(3).childNode(1).attr(AppConstants.SRC);
                String author = article.childNode(5).childNode(1).childNode(1).childNode(0).toString();
                String date = article.childNode(5).childNode(3).childNode(1).childNode(0).toString();
                String body = "";
                for(Node node:article_holder_body){
                    body += "\n"+node.childNode(0).toString();
                }
                WebFeed webFeed = new WebFeed("attijari",term,HESPRESS,getDate(date), url, title, body, author, image);
                webFeedRepository.save(webFeed);
                feedByClientRepository.save(new FeedByClient(webFeed));
            }

        }catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("finishing parsing article "+url);
    }

     public Date getDate(String dateTimeString) {
        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateTimeString, formatter).minusHours(1);
            Date toDate = java.util.Date.from(dateTime.toInstant(ZoneOffset.UTC));
            return toDate;
        } catch (Exception e) {
            return new Date();
        }
    }

}
