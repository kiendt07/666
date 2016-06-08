import java.io.InputStream;

/**
 * Created by j on 07/06/2016.
 */
public class Dictionary {
    private static WordList<String, SyllableWord> words;

    public Dictionary (InputStream source) {
        Analyzer analyzer;
        try {
            analyzer = new Analyzer(source, Analyzer.DICTIONARY);
            analyzer.analyze();
            this.words = analyzer.getWordList();
        } catch (WrongMode wrongMode) {
            System.out.println("Cannot create dictionary");
        }
    }

    public static boolean hasWord (String word) {
        return words.has(word);
    }

    public static SyllableWord get (String word) {
        return words.get(word);
    }
}
