package cat.urv.deim.models;

public class Entry<K, V> {
    protected K key;
    protected V value;
    protected Entry<K, V> nextEntry;

    public Entry() {
    }

    public Entry(K key, V value) {
        this.key = key;
        this.value = value;
        nextEntry=null;
    }

    public K getKey() {
        return key;
    }

    public void setKey(K key) {
        this.key = key;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Entry<K, V> getNextEntry() {
        return nextEntry;
    }

    public void setNextEntry(Entry<K, V> nextEntry) {
        this.nextEntry = nextEntry;
    }
}
