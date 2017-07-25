package ma.octo.smap.utils;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.core.LowerCaseFilterFactory;
import org.apache.lucene.analysis.core.StopFilterFactory;
import org.apache.lucene.analysis.custom.CustomAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.standard.StandardFilterFactory;
import org.apache.lucene.analysis.standard.StandardTokenizerFactory;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.spell.*;
import org.apache.lucene.store.RAMDirectory;

import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adib on 02/05/17.
 */
public class TextProcessor {

    public static final String DISTANCE_ALGO_DATA_PATH = "src/main/resources/distances_algo_data";

    static Analyzer analyzer;

    public static List<String> removeStopWords(String text, int type, boolean first_process) throws Exception {
        try {
            analyzer = CustomAnalyzer.builder()
                    .withTokenizer(StandardTokenizerFactory.class)
                    .addTokenFilter(StandardFilterFactory.class)
                    .addTokenFilter(LowerCaseFilterFactory.class)
                    .addTokenFilter(StopFilterFactory.class, "ignoreCase", "false", "words", "stopwords.txt", "format", "wordset")
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String> without_stop_words = tokenizeString(analyzer, text);
        List<String> stemmed = without_stop_words.stream().map(s -> {
            String stemmedWord = ( type == AppConstants.ALGO_VOWELS ) ? s.replaceAll("[aeiouAEIOU]", "") : s;
            String withoutRepeatedChar = stemmedWord.replaceAll("(.)\\1+", "$1");
            return withoutRepeatedChar;
        }).collect(Collectors.toList());
        if( (type != AppConstants.ALGO_VOWELS) && (!first_process) )
            stemmed = applyDistanceAlgo(stemmed, type);
        return stemmed;
    }

    private static List<String> applyDistanceAlgo(List<String> comment, int type) {

        String textComment = String.join(" ", comment);
        StandardAnalyzer analyzer = new StandardAnalyzer();
        IndexWriterConfig config = new IndexWriterConfig(analyzer);
        SpellChecker spellchecker = null;
        try {

            if(type == AppConstants.ALGO_JAROWINKLER)
                spellchecker = new SpellChecker(new RAMDirectory(), new JaroWinklerDistance());
            else if(type == AppConstants.ALGO_NGRAM)
                spellchecker = new SpellChecker(new RAMDirectory(), new JaroWinklerDistance());
            else
                spellchecker = new SpellChecker(new RAMDirectory());
            Dictionary dictionary = new PlainTextDictionary(new File(DISTANCE_ALGO_DATA_PATH).toPath());
            spellchecker.indexDictionary(dictionary,config,false);
            if( spellchecker.exist(textComment) )
                return Arrays.asList(textComment);
            String[] suggestions = spellchecker.suggestSimilar(textComment, 1);
            if(suggestions.length > 0)
                return Arrays.asList(suggestions[0]);
            else
                return comment;
        } catch (IOException e) {
            return comment;
        }
    }


    public static List<String> tokenizeString(Analyzer analyzer, String str) {

        List result = new ArrayList<>();

        try {

            TokenStream stream = analyzer.tokenStream(null, new StringReader(str));

            stream.reset();

            while (stream.incrementToken()) {

                result.add(stream.getAttribute(CharTermAttribute.class).toString());

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return result;

    }

}
