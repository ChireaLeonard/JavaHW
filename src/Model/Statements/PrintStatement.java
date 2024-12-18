package Model.Statements;

import Exceptions.Temp;
import Model.Expressions.IExpressions;
import Model.Utils.ProgramState;
import Model.Values.IValue;

public class PrintStatement implements IStatement {
    private final IExpressions expression;
    public PrintStatement(IExpressions expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Temp {
        IValue result = this.expression.evaluate(state.getSymTable(), state.getHeap());
        state.getOutputList().add(result);
        return state;
    }

    @Override
    public String toString() {
        return "print(" + expression.toString() + ")";
    }
}
