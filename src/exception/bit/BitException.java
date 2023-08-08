package exception.bit;

public abstract class BitException extends Exception {
    public BitException(String message) {
        super("bit: " + message);
    }
}
