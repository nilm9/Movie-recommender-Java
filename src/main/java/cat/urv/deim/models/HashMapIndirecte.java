package cat.urv.deim.models;

import cat.urv.deim.exceptions.ElementNoTrobat;

import java.util.Iterator;


public class HashMapIndirecte<K extends Comparable<K>,V> implements IHashMap<K,V> {
    private static final int INITIAL_CAPACITY = 30;
    private static final double LOAD_FACTOR_THRESHOLD = 0.75;

    private Entry<K,V>[] table;
    private int size;

    public HashMapIndirecte(int capacity) {
        this.size = 0;
        this.table = new Entry[capacity];
    }

    public HashMapIndirecte() {
        this.size = 0;
        this.table = new Entry[INITIAL_CAPACITY];
    }


    @Override
    public void inserir(K key, V value) {
        //Create the index for searchingknlk
        resizeIfNeeded();
        int index = Math.abs(key.hashCode())  % table.length;

        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
            entry = entry.getNextEntry();
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        newEntry.setNextEntry(table[index]);
        table[index] = newEntry;
        size++;
    }



    @Override
    public void esborrar(K key) throws ElementNoTrobat {
        int index = Math.abs(key.hashCode())  % table.length;

        Entry<K, V> prev = null;
        Entry<K, V> current = table[index];
        while (current != null) {
            if (current.getKey().equals(key)) {
                if (prev == null) {
                    table[index] = current.getNextEntry();
                } else {
                    prev.setNextEntry(current.getNextEntry());
                }
                size--;
                return;
            }
            prev = current;
            current = current.getNextEntry();
        }
        throw new ElementNoTrobat("404");

    }


    @Override
    public boolean buscar(K key)  {
        int index = Math.abs(key.hashCode()) % table.length;
        Entry<K, V> aux = table[index];
        if (aux == null)
            return false;
        while (aux.getNextEntry() != null){
            if (aux.getKey().equals(key))
                return true;
            aux = aux.getNextEntry();
        }
        return aux.getKey().equals(key);
    }

    @Override
    public boolean esBuida() {
        return size == 0;
    }

    @Override
    public int longitud() {
        return size;
    }



    @Override
    public Iterator<V> iterator() {
        V keys[] = obtenirValors();

        IteratorHash<V> it = new IteratorHash(keys);
        return it;
    }


    @Override
    public Object[] obtenirClaus() {
        //If it is initiualized w acpacity space is wasted
        Object[] keys = new Object[size];
        int index = 0;
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> aux = table[i];
            while (aux != null) {
                keys[index] = aux.getKey();
                index++;
                aux = aux.getNextEntry();
            }
        }
        return keys;
    }

    public V[] obtenirValors() {
        Object[] values = new Object[size];
        int index = 0;
        for (int i = 0; i < table.length; i++) {
            Entry<K, V> aux = table[i];
            while (aux != null) {
                values[index] = aux.getValue();
                index++;
                aux = aux.getNextEntry();
            }
        }
        return (V[]) values;
    }



    @Override
    public V element(K key)  {
        int index = Math.abs(key.hashCode()) % table.length;
        Entry<K, V> entry = table[index];
        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.getNextEntry();
        }

        return null;
    }


    @Override
    public float factorCarrega() {
        int cont = 0;
        for (int i = 0; i < table.length; i++){
            if (table[i] != null)
                cont++;
        }
        return (float)cont / table.length;    }

    @Override
    public int midaTaula() {
        return size;
    }

    public void resizeIfNeeded() {
        if ((double) size / table.length > LOAD_FACTOR_THRESHOLD) {
            Entry<K, V>[] newTable = new Entry[table.length * 2];

            for (int i = 0; i < table.length; i++) {
                Entry<K, V> entry = table[i];
                while (entry != null) {
                    Entry<K, V> next = entry.getNextEntry();
                    int newIndex = Math.abs(entry.getKey().hashCode()) % newTable.length;
                    entry.setNextEntry(newTable[newIndex]);
                    newTable[newIndex] = entry;
                    entry = next;
                }
            }

            table = newTable;
        }
    }

    public V getOrDefault(K key, V defaultValue) {
        V value = this.element(key);
        return value != null ? value : defaultValue;
    }

    public LlistaDoblementEncadenadaOrdenada<K> keySet() {
        LlistaDoblementEncadenadaOrdenada<K> keyList = new LlistaDoblementEncadenadaOrdenada<>();
        for (Entry<K, V> entry : table) {
            if (entry != null) {
                Entry<K, V> current = entry;
                while (current != null) {
                    keyList.inserir(current.getKey());  // Insert the key into the list
                    current = current.getNextEntry();
                }
            }
        }
        return keyList;
    }




}