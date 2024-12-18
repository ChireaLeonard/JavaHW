package View;

import Model.Statements.*;
import Model.Expressions.*;
import Model.Types.IntType;
import Model.Types.RefType;
import Model.Types.StringType;
import Model.Values.IntValue;
import Model.Values.StringValue;

import java.beans.Expression;

public class Example2 {
    public IStatement ex2() {
        IStatement declareGreeting = new VariableDeclarationStatement("greeting", new StringType());
        IStatement assignGreeting = new AssignStatement("greeting", new ValueExpression(new StringValue("Hello world!")));
        IStatement printGreeting = new PrintStatement(new VariableExpression("greeting"));

        IStatement declareA = new VariableDeclarationStatement("a", new IntType());
        IStatement declareB = new VariableDeclarationStatement("b", new IntType());

        IStatement assignA = new AssignStatement("a", new ValueExpression(new IntValue(10)));
        IStatement assignB = new AssignStatement("b", new ValueExpression(new IntValue(20)));

        IExpressions comparison = new RelationalExpressions(new VariableExpression("a"), new VariableExpression("b"), ">");

        IStatement printA = new PrintStatement(new VariableExpression("a"));
        IStatement printB = new PrintStatement(new VariableExpression("b"));

        IStatement ifGreaterPrintA = new IfStatement(printA, printB, comparison);

        IStatement exampleProgram = new CompoundStatement(declareGreeting, new CompoundStatement(assignGreeting,
                new CompoundStatement(printGreeting, new CompoundStatement(declareA, new CompoundStatement(declareB,
                        new CompoundStatement(assignA, new CompoundStatement(assignB, ifGreaterPrintA)))))));

        return exampleProgram;
    }

    public IStatement ex3() {
        IStatement declareVarf = new VariableDeclarationStatement("varf", new StringType());
        IStatement assignVarf = new AssignStatement("varf", new ValueExpression(new StringValue("test.txt")));

        IStatement openFile = new OpenReadFileStatement(new VariableExpression("varf"));

        IStatement declareVarc = new VariableDeclarationStatement("varc", new IntType());

        IStatement readFileFirst = new ReadFileStatement(new VariableExpression("varf"), "varc");
        IStatement printVarcFirst = new PrintStatement(new VariableExpression("varc"));

        IStatement readFileSecond = new ReadFileStatement(new VariableExpression("varf"), "varc");
        IStatement printVarcSecond = new PrintStatement(new VariableExpression("varc"));

        IStatement closeFile = new CloseReadFileStatement(new VariableExpression("varf"));

        IStatement program = new CompoundStatement(declareVarf, new CompoundStatement(assignVarf, new CompoundStatement(
                openFile, new CompoundStatement(declareVarc, new CompoundStatement(readFileFirst, new CompoundStatement(
                        printVarcFirst, new CompoundStatement(readFileSecond, new CompoundStatement(
                                printVarcSecond, closeFile))))))));
        return program;
    }

    public IStatement ex4() {
        IStatement declareInt = new VariableDeclarationStatement("v", new IntType());
        IStatement assignInt = new AssignStatement("v", new ValueExpression(new IntValue(4)));
        IStatement whileStmnt = new WhileStatement(new RelationalExpressions(new VariableExpression("v"),new ValueExpression(new IntValue(0)),">"),
                new CompoundStatement(
                        new PrintStatement(new VariableExpression("v")),
                        new AssignStatement("v",new ArithmeticExpressions(new VariableExpression("v"),new ValueExpression(new IntValue(1)),ArithmeticOps.SUBTRACT))));
        IStatement printStmt = new PrintStatement(new VariableExpression("v"));
        IStatement program = new CompoundStatement(declareInt, new CompoundStatement(assignInt, new CompoundStatement(whileStmnt, printStmt)));
        return program;
    }

    public IStatement ex5() {
        IStatement declareRef = new VariableDeclarationStatement("v", new RefType(new IntType()));
        IStatement assignRef = new HeapAllocationStatement("v",new ValueExpression( new IntValue(20)));
        IStatement declareRef2 = new VariableDeclarationStatement("a", new RefType(new RefType(new IntType())));
        IStatement assignRef2 = new HeapAllocationStatement("a",new VariableExpression("v"));
        IStatement assignRef3 = new HeapAllocationStatement("v",new ValueExpression(new IntValue(30)));
        IStatement printRef = new PrintStatement(new HeapReadingExpression(new HeapReadingExpression(new VariableExpression("a"))));
        IStatement program = new CompoundStatement(declareRef, new CompoundStatement(assignRef, new CompoundStatement(
                declareRef2, new CompoundStatement(assignRef2, new CompoundStatement(assignRef3, printRef)))));
        return program;
    }
}
