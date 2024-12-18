package Model.Utils;

import Exceptions.Temp;

public interface MyIStack<T> {
    public void push(T element);
    public T pop() throws Temp;
    public int size();
    public boolean isEmpty();
}
