/**
 * Created by j on 07/06/2016.
 */
public class WordInfo {
    private Word word;
    private int appearance;

    public WordInfo(Word word, int appearance) {
        this.word = word;
        this.appearance = appearance;
    }

    public Word getWord() {
        return word;
    }

    public int getAppearance() {
        return appearance;
    }

    public void setAppearance(int appearance) {
        this.appearance = appearance;
    }
}
