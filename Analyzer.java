import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Created by j on 07/06/2016.
 */
public class Analyzer {
    private Scanner scanner;
    private WordList wordList;
    private int paragraphs;
    private int lines;
    private int words;
    private int distinceWords;
    private int characters;
    private int letters;
    private String mode;

    // Mode constant
    public static final String DOCUMENT = "doc";
    public static final String DICTIONARY = "dict";

    public Analyzer (InputStream source, String code) throws WrongMode{
        this.scanner = new Scanner(source);

        this.mode = code.equals(DOCUMENT)
                ? DOCUMENT
                : code.equals(DICTIONARY)
                ? DICTIONARY
                : null;
        switch (mode) {
            case DOCUMENT:
                this.wordList = new WordList<String, WordInfo>();
                break;
            case DICTIONARY:
                this.wordList = new WordList<String, SyllableWord>();
                break;
            default:
                throw new WrongMode();
        }
    }

    private void gatherInfo () {
        this.lines += (this.characters + (-this.characters % Document.LINE_LENGTH)) / Document.LINE_LENGTH;
    }

    public void analyze () throws WrongMode {
        String line;
        String[] words;
        while (scanner.hasNextLine()) {
            this.paragraphs++ ; // Increase the number of paragraphs
            this.lines ++;
            line = scanner.nextLine(); // Get new line
            words = line.split("([^a-zA-Z0-9/']+)'*\\1*"); // Get every single words in Array words
            this.letters += line.replaceAll("\\s+", "").length(); // Increase number of non-blank character
            this.characters += line.length();
            this.words += words.length;
            // Iterate through words, put all in wordList
            switch (mode) {
                case DOCUMENT:
                    for (String word : words) {
                        // Split word into letter's array
                        if (wordList.has(word.toLowerCase())) {
                            // trong wordList da co word
                            WordInfo wordInfo = (WordInfo) wordList.get(word.toLowerCase());
                            wordInfo.setAppearance(wordInfo.getAppearance() + 1);
                            wordList.update(word.toLowerCase(), wordInfo);
                        } else {
                            // trong wordList chua co word
                            if (Dictionary.hasWord(word.toLowerCase())) {
                                // word co trong dictionary => la SyllableWord
                                SyllableWord syllableWord = Dictionary.get(word.toLowerCase());
                                WordInfo wordInfo = new WordInfo(syllableWord, 1);
                                wordList.insert(word.toLowerCase(), wordInfo);
                            } else {
                                // neu word ko co trong Dictionary => la NonSyllableWord
                                NonSyllableWord nonSyllableWord = new NonSyllableWord(word);
                                WordInfo wordInfo = new WordInfo(nonSyllableWord, 1);
                                wordList.insert(word.toLowerCase(), wordInfo);
                            }
                        }
                    } // end for
                    break;
                    // end case DOCUMENT
                case DICTIONARY:
                    String spell = words[0].toLowerCase();
                    String pronouce = words[1];

                    SyllableWord syllableWord = new SyllableWord(spell, pronouce);
                    wordList.insert(spell, syllableWord);
                    break;

                default:
                    throw new WrongMode();
            }
        }
        this.distinceWords = wordList.size();

        gatherInfo();
    }

    public WordList getWordList() {
        return wordList;
    }

    public int getParagraphs() {
        return paragraphs;
    }

    public int getLines() {
        return lines;
    }

    public int getWords() {
        return words;
    }

    public int getDistinceWords() {
        return distinceWords;
    }

    public int getCharacters() {
        return characters;
    }

    public int getLetters() {
        return letters;
    }

    public static void main (String[] args) {
        try {
            new Dictionary(new FileInputStream(new File("src/file.txt")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
