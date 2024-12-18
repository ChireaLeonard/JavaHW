package Model.Utils;

import java.util.HashMap;
import java.util.Map;

public class MyHeap<v> implements MyIHeap<v>{
    private Map<Integer, v> heap;
    private int freeLocation;

    public MyHeap() {
        heap = new HashMap<>();
        freeLocation = 1;
    }

    @Override
    public int allocate(v value) {
        heap.put(freeLocation , value);
        return freeLocation++;
    }

    @Override
    public v getValue(Integer key) {
        return heap.get(key);
    }

    @Override
    public void setValue(Integer key, v value) {
        heap.put(key, value);
    }

    @Override
    public Map<Integer, v> getMap() {
        return heap;
    }

    @Override
    public boolean isDefined(Integer key) {
        return heap.containsKey(key);
    }

    @Override
    public void setContent(Map<Integer, v> map) {
        this.heap = map;
    }

    @Override
    public Map<Integer, v> getContent() {
        return this.heap;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (var entry : heap.entrySet()) {
            sb.append(entry.getKey()).append(" -> ").append(entry.getValue()).append("\n");
        }
        return "Heap: \n" + sb.toString() + "------------------\n";
    }
}
