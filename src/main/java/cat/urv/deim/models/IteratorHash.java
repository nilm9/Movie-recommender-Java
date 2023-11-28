package cat.urv.deim.models;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class IteratorHash<V> implements Iterator<V>{

    private V l[];
    private int pos;

    public IteratorHash(V[] llista) {
        this.l=llista.clone();
        pos=0;
    }

    @Override
    public boolean hasNext() {
        return ((pos<l.length)&&l[pos]!=null);
    }

    @Override
    public V next() {
        V elem = l[pos];
        pos++;
        return elem;
    }

}
