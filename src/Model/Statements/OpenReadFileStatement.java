package Model.Statements;

import Exceptions.Temp;
import Model.Expressions.IExpressions;
import Model.Utils.ProgramState;
import Model.Values.IValue;
import Model.Values.StringValue;
import Model.Types.StringType;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class OpenReadFileStatement implements IStatement {
    private IExpressions exp;

    public OpenReadFileStatement(IExpressions exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Temp {
        IValue value = exp.evaluate(state.getSymTable(), state.getHeap());

        if (!(value.getType() instanceof StringType)) {
            throw new Temp("The expression does not evaluate to a string.");
        }

        StringValue filePath = (StringValue) value;  // Get the string value (file path)

        if (state.getFileTable().contains(filePath)) {
            throw new Temp("The file is already opened.");
        }

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath.getValue()));

            state.getFileTable().insert(filePath, reader);

        } catch (IOException e) {
            throw new Temp("Error opening the file: " + e.getMessage());
        }

        return state;
    }

    @Override
    public String toString() {
        return "openRFile(" + exp.toString() + ")";
    }
}
