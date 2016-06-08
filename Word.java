/**
 * Created by j on 07/06/2016.
 */
public class Word {
    private String spell;
    private WordInfo info;

    public Word(String spell) {
        this.spell = spell;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        final Word other = (Word) o;
        if (!this.spell.equals(other.getSpell()))
            return false;
        return false;
    }
}
