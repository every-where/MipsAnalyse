package exception.code;

public class InvalidLengthCodeException extends CodeException {
    private final int tarLength;
    private final int realLength;
    public InvalidLengthCodeException(String code, int tarLength, int realLength) {
        super(code, "错误长度{" + tarLength + ", " + realLength + "}");
        this.tarLength = tarLength;
        this.realLength = realLength;
    }

    public int getTarLength() {
        return tarLength;
    }

    public int getRealLength() {
        return realLength;
    }
}
