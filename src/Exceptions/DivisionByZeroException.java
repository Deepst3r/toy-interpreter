package Exceptions;

public class DivisionByZeroException extends RuntimeException {

    public DivisionByZeroException(){
        super("You cannot divide by 0!\n");
    }
}