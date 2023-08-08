package exception.bit;

public class InvalidEndBitException extends BitException {
    private final int end;
    public InvalidEndBitException(int end) {
        super("非法结束位置{" + end + "}");
        this.end = end;
    }

    public int getEnd() {
        return end;
    }
}
