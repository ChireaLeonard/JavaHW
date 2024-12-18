package Model.Statements;

import Exceptions.Temp;
import Model.Expressions.IExpressions;
import Model.Types.RefType;
import Model.Utils.ProgramState;
import Model.Values.IValue;
import Model.Values.RefValue;

import java.io.IOException;

public class HeapAllocationStatement implements IStatement {
    String name;
    IExpressions expression;

    public HeapAllocationStatement(String name, IExpressions expression) {
        this.name = name;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Temp, IOException {
        if(!state.getSymTable().contains(name)) {
            throw new Temp("Variable name not in sym table");
        }
        IValue variableName = state.getSymTable().getValue(name);
        if(!(variableName instanceof RefValue)) {
            throw new Temp("Variable is not RefType");
        }
        IValue evalExpression = expression.evaluate(state.getSymTable(),state.getHeap());
        if(!variableName.getType().equals(new RefType(evalExpression.getType()))) {
            throw new Temp("Variable is not RefType");
        }
        int addr = state.getHeap().allocate(evalExpression);
        state.getSymTable().insert(name,new RefValue(addr, evalExpression.getType()));
        return state;
    }
    @Override
    public String toString() {
        return "new(" + name + ", " +expression.toString() +  ")";
    }
}
