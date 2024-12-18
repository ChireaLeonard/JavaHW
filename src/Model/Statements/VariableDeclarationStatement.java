package Model.Statements;

import Exceptions.Temp;
import Model.Types.IType;
import Model.Utils.MyIDictionary;
import Model.Utils.ProgramState;
import Model.Values.IValue;

public class VariableDeclarationStatement implements IStatement {
    private String variable;
    private IType value;

    public VariableDeclarationStatement(String variable, IType value) {
        this.variable = variable;
        this.value = value;
    }
    @Override
    public ProgramState execute(ProgramState state) throws Temp {
        MyIDictionary<String,IValue> symTable = state.getSymTable();
        if(symTable.contains(variable)) {
            throw new Temp(variable + " already exists");
        }
        IValue defaultValue = value.getDefaultValue();
        state.getSymTable().insert(variable, defaultValue);
        return state;
    }
    @Override
    public String toString() {
        return value.toString() + " " +variable;
    }
}
