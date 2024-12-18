package Model.Utils;

import Model.Values.IValue;

import java.util.HashMap;
import java.util.Map;

public interface MyIHeap<v> {
    public int allocate(v value);
    public v getValue(Integer key);
    public void setValue(Integer key, v value);
    public Map<Integer,v> getMap();
    public boolean isDefined(Integer key);
    public void setContent(Map<Integer, v> map);
    public Map<Integer, v> getContent();
}
