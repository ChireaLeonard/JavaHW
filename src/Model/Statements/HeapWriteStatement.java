package Model.Statements;

import Exceptions.Temp;
import Model.Expressions.IExpressions;
import Model.Types.RefType;
import Model.Utils.ProgramState;
import Model.Values.IValue;
import Model.Values.RefValue;

public class HeapWriteStatement implements IStatement {
    private String varName;
    private IExpressions expression;

    public HeapWriteStatement(String varName, IExpressions expression) {
        this.varName = varName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Temp {

        if (!state.getSymTable().contains(varName)) {
            throw new Temp("Variable " + varName + " is not defined in the symbol table.");
        }

        IValue varValue = state.getSymTable().getValue(varName);
        if (!(varValue instanceof RefValue)) {
            throw new Temp("Variable " + varName + " is not a RefValue.");
        }

        RefValue refValue = (RefValue) varValue;
        int address = refValue.getAddress();

        if (state.getHeap().isDefined(address)) {
            throw new Temp("Heap does not contain the given address: " + address);
        }

        IValue exprValue = expression.evaluate(state.getSymTable(), state.getHeap());

        if (!refValue.getType().equals(new RefType(exprValue.getType()))) {
            throw new Temp("Type mismatch: Expression type does not match the location type of variable " + varName);
        }
        int addr = state.getHeap().allocate(exprValue);
        state.getSymTable().insert(varName,new RefValue(addr, exprValue.getType()));

        return state;
    }
    @Override
    public String toString() {
        return "hW(" + varName + ", " + expression.toString() + ")";
    }
}
