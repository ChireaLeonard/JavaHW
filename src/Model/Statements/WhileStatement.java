package Model.Statements;

import Exceptions.Temp;
import Model.Expressions.IExpressions;
import Model.Types.BoolType;
import Model.Utils.ProgramState;
import Model.Values.BoolValue;
import Model.Values.IValue;

import java.io.IOException;

public class WhileStatement implements IStatement {
    private IExpressions expression;
    private IStatement statement;

    public WhileStatement(IExpressions expression, IStatement statement) {
        this.expression = expression;
        this.statement = statement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Temp, IOException {
        IValue value = expression.evaluate(state.getSymTable(), state.getHeap());
        if(!value.getType().equals(new BoolType())) {
            throw new Temp("Expression is not boolean");
        }
        if(((BoolValue)value).getValue()) {
            state.getStack().push(this);
            state.getStack().push(statement);
        }
        return state;
    }

    @Override
    public String toString() {
        return "while(" + expression.toString() + "){\n" + statement.toString() + "\n}";
    }
}
