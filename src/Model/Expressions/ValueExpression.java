package Model.Expressions;

import Exceptions.Temp;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.IValue;

public class ValueExpression implements IExpressions {
    private IValue value;

    public ValueExpression(IValue value) {
        this.value = value;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap<IValue> heap) throws Temp {
        return this.value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
