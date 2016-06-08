import java.util.Collection;
import java.util.HashMap;

/**
 * Created by j on 07/06/2016.
 */
public class WordList<K, V> {
    private HashMap<K, V> list;

    public WordList () {
        this.list = new HashMap<>();
    }

    public V insert (K key, V element) {
        if (!list.containsKey(key))
            return this.list.put(key, element);
        else
            return null;
    }

    private V newInfo(V wordInfo) {
        final WordInfo newWordInfo = (WordInfo) wordInfo;
        newWordInfo.setAppearance(newWordInfo.getAppearance() + 1);
        return (V) newWordInfo;
    }

    public V update (K key, V element) {
        if (list.containsKey(key))
            return list.put(key, element);
        else
            return null;
    }

    public V delete (K key) {
        if (list.containsKey(key))
            return list.remove(key);
        return null;
    }

    public V get (K key) {
        if (list.containsKey(key))
            return list.get(key);
        else
            return null;
    }

    public boolean has (K key) {
        return list.containsKey(key);
    }

    public int size() {
        return list.size();
    }

    public Collection<V> getList () {
        return list.values();
    }
}
