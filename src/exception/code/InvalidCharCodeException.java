package exception.code;

public class InvalidCharCodeException extends CodeException {
    private final char c;
    private final int pos;
    public InvalidCharCodeException(String code, char c, int pos) {
        super(code, "非法字符{'" + c + "', " + pos + "}");
        this.c = c;
        this.pos = pos;
    }

    public char getC() {
        return c;
    }

    public int getPos() {
        return pos;
    }
}
