package Model.Expressions;

import Exceptions.Temp;
import Model.Utils.MyIDictionary;
import Model.Utils.MyIHeap;
import Model.Values.BoolValue;
import Model.Values.IValue;
import Model.Values.IntValue;
import Model.Types.IntType;

public class RelationalExpressions implements IExpressions {
    private IExpressions left;
    private IExpressions right;
    private String operator;


    public RelationalExpressions(IExpressions left, IExpressions right, String operator) {
        this.left = left;
        this.right = right;
        this.operator = operator;
    }

    @Override
    public IValue evaluate(MyIDictionary<String, IValue> table, MyIHeap<IValue> heap) throws Temp {
        IValue leftValue = left.evaluate(table, heap);
        IValue rightValue = right.evaluate(table, heap);

        if (!(leftValue.getType() instanceof IntType)) {
            throw new Temp("Left operand must be of type int.");
        }
        if (!(rightValue.getType() instanceof IntType)) {
            throw new Temp("Right operand must be of type int.");
        }

        int leftInt = ((IntValue) leftValue).getValue();
        int rightInt = ((IntValue) rightValue).getValue();

        boolean result;
        switch (operator) {
            case "<":
                result = leftInt < rightInt;
                break;
            case "<=":
                result = leftInt <= rightInt;
                break;
            case "==":
                result = leftInt == rightInt;
                break;
            case "!=":
                result = leftInt != rightInt;
                break;
            case ">":
                result = leftInt > rightInt;
                break;
            case ">=":
                result = leftInt >= rightInt;
                break;
            default:
                throw new Temp("Invalid relational operator: " + operator);
        }

        return new BoolValue(result);
    }

    @Override
    public String toString() {
        return left.toString() + " " + operator + " " + right.toString();
    }
}
