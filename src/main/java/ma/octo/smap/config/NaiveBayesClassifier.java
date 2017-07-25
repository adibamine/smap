package ma.octo.smap.config;

import de.daslaboratorium.machinelearning.classifier.Classifier;
import de.daslaboratorium.machinelearning.classifier.bayes.BayesClassifier;
import ma.octo.smap.utils.AppConstants;
import ma.octo.smap.utils.TextProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by adib on 01/05/17.
 */
@Component
public class NaiveBayesClassifier {

    public static final String POSITIVE = "pos";
    public static final String NEGATIVE = "neg";
    public static final String NEUTRAL = "neu";

    private static String csvFile = "src/main/resources/naive_bayes_data.txt";
    private static BufferedReader br = null;
    private static String line = "";
    private static String cvsSplitBy = ";";

    public Classifier<String, String> bayes;


    @Bean
    public Classifier<String, String> getClassifier() {

        bayes = new BayesClassifier<String, String>();

        TextProcessor stopWordRemoval = new TextProcessor();


        try {
            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                System.out.println("****");
                String[] data = line.split(cvsSplitBy);
                System.out.println(data[0]);
                List<String> input = stopWordRemoval.removeStopWords(data[0], AppConstants.ALGO_VOWELS,true);
                bayes.learn(data[1], input);
                System.out.println(input + " = " + data[1]);
                System.out.println("****");

            }

        } catch (
                FileNotFoundException e) {
            e.printStackTrace();
        } catch (
                IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println(POSITIVE + " = " + bayes.getCategoryCount(POSITIVE));
        System.out.println(NEGATIVE + " = " + bayes.getCategoryCount(NEGATIVE));
        System.out.println(NEUTRAL + " = " + bayes.getCategoryCount(NEUTRAL));

        return bayes;

    }



}
