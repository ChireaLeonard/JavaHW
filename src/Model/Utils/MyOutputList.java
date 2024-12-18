package Model.Utils;

import java.util.ArrayList;
import java.util.List;
public class MyOutputList<T> implements MyIList<T> {
    private final List<T> outputList;
    public MyOutputList() {
        outputList = new ArrayList<>();
    }

    @Override
    public List<T> getAll() {
        return outputList;
    }

    @Override
    public void add(T elem) {
        outputList.add(elem);
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (T elem : outputList) {
            output.append(elem.toString()).append("\n");
        }
        return "OutputList:\n" + output.toString() + "------------------\n";
    }
}
