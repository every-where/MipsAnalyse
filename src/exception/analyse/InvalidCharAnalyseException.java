package exception.analyse;

public class InvalidCharAnalyseException extends AnalyseException {
    private final char c;
    private final int pos;

    public InvalidCharAnalyseException(char c, int pos) {
        super("非法字符{'" + c + "', " + pos + "}");
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
