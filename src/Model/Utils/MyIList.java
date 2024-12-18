package Model.Utils;
import java.util.List;
public interface MyIList<T> {
    List<T> getAll();
    void add(T elem);
}
