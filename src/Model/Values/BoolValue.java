package Model.Values;

import Model.Types.BoolType;
import Model.Types.IType;

public class BoolValue implements IValue {
    private final boolean value;

    public  BoolValue() {
        value = false;
    }

    public BoolValue(boolean value) {
        this.value = value;
    }
    @Override
    public IType getType() {
        return new BoolType();
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof BoolValue)) {
            return false;
        }
        return value == ((BoolValue)obj).getValue();
    }

}
