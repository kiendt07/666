import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by j on 07/06/2016.
 */
public class Document {
    private Analyzer analyzer;
    public static final int LINE_LENGTH = 130;

    public Document (InputStream source) throws WrongMode {
        this.analyzer = new Analyzer(source, Analyzer.DOCUMENT);
        analyzer.analyze();
    }

    public Collection<WordInfo> getWordInfos() {
        WordList<String, WordInfo> list = analyzer.getWordList();
        Collection<WordInfo> collection = new ArrayList<>();

        for (WordInfo info : list.getList()) {
            if (info.getWord() instanceof NonSyllableWord)
                collection.add(info);
        }
        return collection;
    }

    public int getNonSyllableWords() {
        return getWordInfos().size();
    }

    public int getParagraphs() {
        return analyzer.getParagraphs();
    }

    public int getLines() {
        return analyzer.getLines();
    }

    public int getWords() {
        return analyzer.getWords();
    }

    public int getDistinceWords() {
        return analyzer.getDistinceWords();
    }

    public int getLetters() {
        return analyzer.getLetters();
    }
}
