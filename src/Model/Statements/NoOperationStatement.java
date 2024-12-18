package Model.Statements;

import Exceptions.Temp;
import Model.Utils.ProgramState;

public class NoOperationStatement implements IStatement {
    @Override
    public ProgramState execute(ProgramState state) throws Temp {
        return state;
    }

    @Override
    public String toString() {
        return "NoOperationStatement";
    }
}
