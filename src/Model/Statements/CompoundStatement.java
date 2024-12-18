package Model.Statements;

import Exceptions.Temp;
import Model.Utils.ProgramState;

public class CompoundStatement implements IStatement {
    private final IStatement statement1;
    private final IStatement statement2;

    public CompoundStatement(IStatement statement1, IStatement statement2) {
        this.statement1 = statement1;
        this.statement2 = statement2;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Temp {
        state.getStack().push(statement2);
        state.getStack().push(statement1);
        return state;
    }

    @Override
    public String toString() {
        return statement1.toString() + ";\n" + statement2.toString();
    }
}
