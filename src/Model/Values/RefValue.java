package Model.Values;

import Model.Types.IType;
import Model.Types.IntType;
import Model.Types.RefType;

public class RefValue implements IValue {
    int address;
    IType locationType;

    public RefValue(int address, IType locationType) {
        this.address = address;
        this.locationType = locationType;
    }

    public int getAddress() {
        return address;
    }

    public IType getLocationType() {
        return locationType;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof RefValue)) {
            return false;
        }
        return address == ((RefValue) obj).getAddress() && locationType == ((RefValue) obj).getLocationType();
    }

    @Override
    public IType getType() {
        return new RefType(locationType);
    }

    @Override
    public String toString() {
        return this.locationType.toString() + " at " + this.address;
    }
}
