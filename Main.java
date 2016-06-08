import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;

/**
 * Created by j on 08/06/2016.
 */
public class Main {
    private static void printWords (Collection<WordInfo> wordInfos) {
        for (WordInfo info : wordInfos) {
            System.out.println(info.getWord().getSpell() + ": " + info.getAppearance() + " times");
        }
    }

    public static void main(String[] args) {
        Document document = null;
        try {
            new Dictionary(new FileInputStream(new File("src/dict.txt")));
            document = new Document(new FileInputStream(new File("src/file.txt")));
        } catch (WrongMode wrongMode) {
            System.out.println("Cannot create Document or Dictionary");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        System.out.println("\tStatistic:");
        System.out.println("paragraph: " + document.getParagraphs() + "\n" +
                "line: " + document.getLines() + "\n" +
                "words: " + document.getWords() + "\n" +
                "distince words: " + document.getDistinceWords() + "\n" +
                "non syllable words: " + document.getNonSyllableWords() + "\n" +
                "letter: " + document.getLetters());

        System.out.println("\n\tNon syllable words:");
        printWords(document.getWordInfos());
    }
}
