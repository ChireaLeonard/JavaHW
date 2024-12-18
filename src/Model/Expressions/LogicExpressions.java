package Model.Expressions;

import Exceptions.Temp;
import Model.Types.BoolType;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.BoolValue;
import Model.Values.IValue;

public class LogicExpressions implements IExpressions {
    private final IExpressions left;
    private final IExpressions right;
    private final LogicOps sign;

    public LogicExpressions(IExpressions left, IExpressions right, LogicOps sign) {
        this.left = left;
        this.right = right;
        this.sign = sign;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap<IValue> heap) throws Temp {
        IValue leftVal = left.evaluate(table, heap);
        if (!leftVal.getType().equals(new BoolType())) {
            throw new Temp(leftVal.getType() + " is not a boolean");
        }
        IValue rightVal = right.evaluate(table, heap);
        if (!rightVal.getType().equals(new BoolType())) {
            throw new Temp(rightVal.getType() + " is not a boolean");
        }
        boolean operand1 = ((BoolValue)leftVal).getValue();
        boolean operand2 = ((BoolValue)rightVal).getValue();
        return switch (sign) {
            case AND -> new BoolValue(operand1 && operand2);
            case OR -> new BoolValue(operand1 || operand2);
        };
    }

    @Override
    public String toString() {
        String Symbol = switch (sign) {
           case AND -> "AND";
           case OR -> "OR";
        };
        return left.toString() + " " + Symbol  + " " + right.toString();
    }
}
