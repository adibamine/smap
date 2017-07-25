package ma.octo.smap.utils;


import com.ibm.watson.developer_cloud.natural_language_understanding.v1.NaturalLanguageUnderstanding;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalysisResults;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.AnalyzeOptions;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.Features;
import com.ibm.watson.developer_cloud.natural_language_understanding.v1.model.SentimentOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by adib on 03/05/17.
 */
@Component
public class NaturalLanguageUnderstandingApi {

    @Autowired
    private Environment env;

    private static final String IBM_BLUEMIX_USER = "ibm_bluemix_user";
    private static final String IBM_BLUEMIX_PASSWORD = "ibm_bluemix_password";

    public Features features;

    public NaturalLanguageUnderstanding service;

    @Bean
    public Features getFeatures() {
        service = new NaturalLanguageUnderstanding(
                NaturalLanguageUnderstanding.VERSION_DATE_2017_02_27,
                env.getProperty(IBM_BLUEMIX_USER),
                env.getProperty(IBM_BLUEMIX_PASSWORD)
        );

        SentimentOptions sentiment = new SentimentOptions.Builder()
                .build();

        features = new Features.Builder()
                .sentiment(sentiment)
                .build();

        return features;

    }

}
