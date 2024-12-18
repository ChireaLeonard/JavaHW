package Model.Utils;


import Exceptions.Temp;

import java.util.Dictionary;
import java.util.Map;
import java.util.HashMap;

public class MyDictionary<K,V> implements MyIDictionary<K,V> {
    private final Map<K,V> map;

    public MyDictionary() {
        this.map = new HashMap<>();
    }

    @Override
    public void insert(K key, V value) {
        this.map.put(key, value);
    }

    @Override
    public V getValue(K key) throws Temp {
        return this.map.get(key);
    }

    @Override
    public void remove(K key) throws Temp {
        this.map.remove(key);
    }

    @Override
    public boolean contains(K key) {
        return map.containsKey(key);
    }

    public Map<K,V> getAll() {
        return this.map;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        //auto in c++
        for(var v: map.entrySet()) {
            sb.append(v.getKey()).append("->").append(v.getValue()).append("\n");
        }
        return sb + "------------------\n";
    }
}
