package Model.Expressions;

import Exceptions.Temp;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.IValue;

public class VariableExpression implements IExpressions{
    private String variable;

    public VariableExpression(String variable){
        this.variable = variable;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap<IValue> heap) throws Temp {
        return table.getValue(variable);
    }

    @Override
    public String toString() {
        return variable;
    }
}
