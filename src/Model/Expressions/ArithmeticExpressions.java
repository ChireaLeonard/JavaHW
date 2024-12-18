package Model.Expressions;

import Exceptions.Temp;
import Model.Types.IntType;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.IValue;
import Model.Values.IntValue;

public class ArithmeticExpressions implements IExpressions{
    private final IExpressions left;
    private final IExpressions right;
    private final ArithmeticOps operation;

    public ArithmeticExpressions(IExpressions left, IExpressions right, ArithmeticOps operation) {
        this.left = left;
        this.right = right;
        this.operation = operation;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap<IValue> heap) throws Temp {
        IValue leftVal = left.evaluate(table,heap);
        if (!leftVal.getType().equals(new IntType())) {
            throw new Temp(leftVal.getType() + " is not an integer");
        }
        IValue rightVal = right.evaluate(table, heap);
        if (!rightVal.getType().equals(new IntType())) {
            throw new Temp(rightVal.getType() + " is not an integer");
        }
        int operand1 = ((IntValue)leftVal).getValue();
        int operand2 = ((IntValue)rightVal).getValue();

        return switch (operation) {
            case ADD -> new IntValue(operand1 + operand2);
            case SUBTRACT -> new IntValue(operand1 - operand2);
            case MULTIPLY -> new IntValue(operand1 * operand2);
            case DIVIDE -> {
                if (operand2 == 0) {
                    throw new Temp("Division by zero");
                }
                yield new IntValue(operand1 / operand2);
            }
        };
    }

    @Override
    public String toString() {
        String Symbol = switch (operation) {
            case ADD -> "+";
            case SUBTRACT -> "-";
            case MULTIPLY -> "*";
            case DIVIDE -> "/";
        };
        return left.toString() +" "+ Symbol +" "+ right.toString();
    }
}
