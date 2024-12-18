package Model.Utils;

import Exceptions.Temp;

import java.util.Stack;

public class MyStack<T> implements MyIStack<T> {
    private final Stack<T> stack;

    public MyStack() {
        this.stack = new Stack<>();
    }

    public Stack<T> getStack() {
        return this.stack;
    }

    @Override
    public void push(T element) {
        this.stack.push(element);
    }

    @Override
    public T pop() throws Temp {
        if(this.stack.isEmpty()) {
            throw new Temp("Stack is empty");
        }
        return this.stack.pop();
    }

    @Override
    public int size() {
        return this.stack.size();
    }

    @Override
    public boolean isEmpty() {
        return this.stack.isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (T element : this.stack.reversed()) {
            sb.append(element).append("\n");
        }
        return "Stack: \n" + sb + "------------------\n";
    }
}
