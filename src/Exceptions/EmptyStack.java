package Exceptions;

public class EmptyStack extends RuntimeException {
    public EmptyStack(String message) {
        super(message);
    }
}
