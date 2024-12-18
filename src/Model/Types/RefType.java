package Model.Types;

import Model.Values.IValue;
import Model.Values.RefValue;

public class RefType implements IType {
    IType inner;

    public RefType(IType inner) {
        this.inner = inner;
    }

    IType getInner() {
        return inner;
    }

    @Override
    public String toString() {
        return "Ref(" + inner.toString() + ")";
    }

    @Override
    public boolean equals(Object another) {
        if (another instanceof RefType) {
            return inner.equals(((RefType) another).getInner());
        }
        else return false;
    }

    @Override
    public IValue getDefaultValue() {
        return new RefValue(0,inner);
    }
}
