package Model.Utils;

import Exceptions.Temp;
import Model.Statements.IStatement;
import Model.Values.IValue;
import Model.Values.StringValue;

import java.io.BufferedReader;
import java.io.IOException;

public class ProgramState {
    private MyIDictionary<String, IValue> symTable;
    private  MyIStack<IStatement> executionStack;
    private MyIList<IValue> outputList;
    private MyIDictionary<StringValue, BufferedReader> fileTable;
    private  MyIHeap<IValue> heap;
    static int id;

    public ProgramState(MyIDictionary<String, IValue> symTable, MyIStack<IStatement> executionStack,MyIList<IValue> outputList, IStatement InitialState, MyIDictionary<StringValue, BufferedReader> fileTable, MyIHeap<IValue> heap) {
        this.symTable = symTable;
        this.executionStack = executionStack;
        this.outputList = outputList;
        this.executionStack.push(InitialState);
        this.fileTable = fileTable;
        this.heap = heap;
    }

    public MyIDictionary<String, IValue> getSymTable() {
        return symTable;
    }
    public void setSymTable(MyIDictionary<String, IValue> symTable) {
        this.symTable = symTable;
    }

    public MyIStack<IStatement> getStack() {
        return executionStack;
    }

    public void setStack(MyIStack<IStatement> stack) {
        this.executionStack = stack;
    }
    public MyIList<IValue> getOutputList() {
        return outputList;
    }
    public void setOutputList(MyIList<IValue> outputList) {
        this.outputList = outputList;
    }
    @Override
    public String toString(){
        return id + "Dictionary: \n" +symTable.toString() + outputList.toString() + "FileTable: \n" + fileTable.toString() + executionStack.toString() + heap.toString() + "\n==================================\n";
    }

    public MyIDictionary<StringValue, BufferedReader> getFileTable() {
        return this.fileTable;
    }

    public MyIHeap<IValue> getHeap() {
        return heap;
    }

    public boolean isNotCompleted() {
        return !(this.executionStack.isEmpty());
    }

    public void oneStep() throws Temp, IOException {
        if (this.executionStack.isEmpty()) throw new Temp("ProgramState stack is empty");
        IStatement currentStatement = this.executionStack.pop();
        currentStatement.execute(this);
    }

}
