package second.lab.matrixes.console;

public class DivideByZeroException extends ArithmeticException {
    public DivideByZeroException() {
        super("На ноль делить нельзя");
    }
}
