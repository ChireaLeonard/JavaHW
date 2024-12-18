package Model.Utils;
import java.util.Map;
import Exceptions.Temp;

public interface MyIDictionary <K,V>{
    void insert(K key, V value);
    V getValue(K key) throws Temp;
    void remove(K key) throws Temp;
    boolean contains(K key);
    Map<K,V> getAll();
}
