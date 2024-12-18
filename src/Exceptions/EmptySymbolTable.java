package Exceptions;

public class EmptySymbolTable extends RuntimeException {
    public EmptySymbolTable(String message) {
        super(message);
    }
}
