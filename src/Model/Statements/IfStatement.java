package Model.Statements;

import Exceptions.Temp;
import Model.Expressions.IExpressions;
import Model.Types.BoolType;
import Model.Utils.ProgramState;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class IfStatement implements IStatement{
    private final IStatement thenStatement;
    private final IStatement elseStatement;
    private final IExpressions expression;

    public IfStatement(IStatement thenStatement, IStatement elseStatement, IExpressions expression) {
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Temp {
        IValue value = expression.evaluate(state.getSymTable(), state.getHeap());
        if(!value.getType().equals(new BoolType())) {
            throw new Temp("Expression is not boolean");
        }
        if(((BoolValue)value).getValue()) {
            state.getStack().push(thenStatement);
        }
        else {
            state.getStack().push(elseStatement);
        }
        return state;

    }

    @Override
    public String toString() {
        return "If(" + expression + ") {" +thenStatement.toString() + "} \n else {" + elseStatement.toString() + "}";
    }
}
