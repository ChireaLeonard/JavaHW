package Model.Expressions;

import Exceptions.Temp;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.IValue;
import Model.Values.RefValue;

public class HeapReadingExpression implements IExpressions {
    private IExpressions expression;

    public HeapReadingExpression(IExpressions expression) {
        this.expression = expression;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> symTable, MyIHeap<IValue> heap) throws Temp {
        IValue value = expression.evaluate(symTable, heap);
        if (!(value instanceof RefValue)) {
            throw new Temp("HeapRead expression is not a RefValue.");
        }

        RefValue refValue = (RefValue) value;
        int address = refValue.getAddress();

        if (!heap.isDefined(address)) {
            throw new Temp("Heap does not contain the given address: " + address);
        }

        return heap.getValue(address);
    }

    @Override
    public String toString() {
        return "rH(" + expression.toString() + ")";
    }
}
