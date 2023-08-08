package exception.bit;

public class InvalidStartBitException extends BitException {
    private final int start;

    public InvalidStartBitException(int start) {
        super("非法开始位置{" + start + "}");
        this.start = start;
    }

    public int getStart() {
        return start;
    }
}
