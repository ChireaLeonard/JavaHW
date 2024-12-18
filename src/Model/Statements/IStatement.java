package Model.Statements;
import Model.Utils.ProgramState;
import Exceptions.Temp;

import java.io.IOException;

public interface IStatement {
    ProgramState execute(ProgramState state) throws Temp, IOException;
}
