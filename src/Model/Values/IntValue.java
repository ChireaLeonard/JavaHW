package Model.Values;

import Model.Types.IType;
import Model.Types.IntType;

public class IntValue implements IValue {
    private final int value;

    @Override
    public IType getType() {
        return new IntType();
    }

    public IntValue() {
        this.value = 0;
    }

    public IntValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof IntValue)) {
            return false;
        }
        return value == ((IntValue) obj).getValue();
    }
}
