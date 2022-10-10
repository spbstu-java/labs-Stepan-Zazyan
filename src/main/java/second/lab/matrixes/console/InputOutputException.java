package second.lab.matrixes.console;

public class InputOutputException extends RuntimeException {

    public InputOutputException() {
        this(null);
    }
    public InputOutputException(String message) {
        super(message);
    }
}
