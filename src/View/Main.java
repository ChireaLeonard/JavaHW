package View;

import Model.Commands.ExitCommand;
import Model.Commands.RunExampleCommand;
import Model.Statements.*;
import Model.Expressions.*;
import Model.Types.IntType;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Utils.*;
import Controller.Controller;
import Model.Values.StringValue;
import Repo.IRepository;
import Repo.Repository;
import View.TextMenu;

import java.io.BufferedReader;
import java.util.Objects;

public class Main {
    static Controller makeProgram(IStatement statement, String file) {
        MyIStack<IStatement> executionStack = new MyStack<>();
        MyIDictionary<String, IValue> symTable = new MyDictionary<>();
        MyIList<IValue> outputList = new MyOutputList<>();
        MyIDictionary<StringValue, BufferedReader> fileTable = new MyDictionary<>();
        MyIHeap<IValue> heap= new MyHeap<>();
        ProgramState programState = new ProgramState(symTable,executionStack,outputList,statement,fileTable,heap);
        IRepository repository = new Repository(programState,file);
        Controller ctr = new Controller(repository);
        return ctr;
    }

    public static void main(String[] args) {
        IStatement ex1 = new CompoundStatement(
                new VariableDeclarationStatement("a", new IntType()),
                new CompoundStatement(
                        new VariableDeclarationStatement("b", new IntType()),
                        new CompoundStatement(
                                new AssignStatement("a", new ArithmeticExpressions(
                                        new ValueExpression(new IntValue(2)),
                                        new ArithmeticExpressions(new ValueExpression(new IntValue(3)), new ValueExpression(new IntValue(5)), ArithmeticOps.MULTIPLY),
                                        ArithmeticOps.ADD
                                )),
                                new CompoundStatement(
                                        new AssignStatement("b", new ArithmeticExpressions(
                                                new VariableExpression("a"),
                                                new ValueExpression(new IntValue(1)),
                                                ArithmeticOps.ADD
                                        )),
                                        new PrintStatement(new VariableExpression("b"))
                                )
                        )
                )
        );
        Example2 example = new Example2();
        //----------------------------------------------------------------------------------


        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExampleCommand("1", "Problem1 :\n" + ex1.toString() + "\n==============================+\n", makeProgram(ex1,"log1.txt")));
        menu.addCommand(new RunExampleCommand("2", "Problem2 :\n" + example.ex2().toString() + "\n==============================+\n", makeProgram(example.ex2(), "log2.txt")));
        menu.addCommand(new RunExampleCommand("3", "Problem3 :\n" + example.ex3().toString() + "\n==============================+\n", makeProgram(example.ex3(), "log3.txt")));
        menu.addCommand(new RunExampleCommand("4", "Problem4 :\n" + example.ex4().toString() + "\n==============================+\n", makeProgram(example.ex4(), "log4.txt")));
        menu.addCommand(new RunExampleCommand("5", "Problem4 :\n" + example.ex5().toString() + "\n==============================+\n", makeProgram(example.ex5(), "log5.txt")));
        menu.show();

    }
}

