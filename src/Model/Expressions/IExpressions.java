package Model.Expressions;

import Exceptions.Temp;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.IValue;

public interface IExpressions {
    IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap<IValue> heap) throws Temp;
}
