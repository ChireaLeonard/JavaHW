package Model.Types;

import Model.Values.IValue;
import Model.Values.IntValue;

public class IntType implements IType {
    @Override
    public boolean equals(Object obj) {
        return obj instanceof IntType;
    }

    @Override
    public String toString(){
        return "int";
    }

    @Override
    public IValue getDefaultValue() {
        return new IntValue();
    }
}

