package exception.rule;

import exception.code.CodeException;

public class InvalidCharRuleException extends RuleException {
    private final char c;
    private final int pos;
    public InvalidCharRuleException(char c, int pos) {
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
