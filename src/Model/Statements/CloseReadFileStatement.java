package Model.Statements;

import Exceptions.Temp;
import Model.Expressions.IExpressions;
import Model.Utils.ProgramState;
import Model.Values.IValue;
import Model.Values.StringValue;
import Model.Types.StringType;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseReadFileStatement implements IStatement {
    private IExpressions exp;

    public CloseReadFileStatement(IExpressions exp) {
        this.exp = exp;
    }

    @Override
    public ProgramState execute(ProgramState state) throws Temp {
        IValue value = exp.evaluate(state.getSymTable(), state.getHeap());

        if (!(value.getType() instanceof StringType)) {
            throw new Temp("The expression does not evaluate to a string.");
        }

        String filePath = ((StringValue) value).getValue();  // Extract the string value (file path)

        if (!state.getFileTable().contains(new StringValue(filePath))) {
            throw new Temp("The file is not open or not present in the file table.");
        }

        BufferedReader reader = state.getFileTable().getValue(new StringValue(filePath));

        try {
            reader.close();
        } catch (IOException e) {
            throw new Temp("Error closing the file: " + e.getMessage());
        }

        state.getFileTable().remove(new StringValue(filePath));

        return state;
    }

    @Override
    public String toString() {
        return "closeRFile(" + exp.toString() + ")";
    }

}
