package Model.Statements;

import Exceptions.Temp;
import Model.Expressions.IExpressions;
import Model.Utils.ProgramState;
import Model.Values.IValue;

public class AssignStatement implements IStatement {
    private final String variableName;
    private final IExpressions expression;
    public AssignStatement(String variableName, IExpressions expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Temp {
        if (!state.getSymTable().contains(this.variableName)) {
            throw new Temp("Variable '" + this.variableName + "' not found");
        }
        IValue value = state.getSymTable().getValue(this.variableName);
        IValue evalValue = this.expression.evaluate(state.getSymTable(), state.getHeap());
        if (!value.getType().equals(evalValue.getType())) {
            throw new Temp("Value type mismatch");
        }
        state.getSymTable().insert(this.variableName, evalValue);
        return state;
    }

    @Override
    public String toString() {
        return variableName + "=" + expression.toString() ;
    }
}
