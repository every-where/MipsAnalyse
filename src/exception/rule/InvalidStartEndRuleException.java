package exception.rule;

public class InvalidStartEndRuleException extends RuleException {
    private final int start;
    private final int end;
    public InvalidStartEndRuleException(int start, int end) {
        super("非法起始结束位置{" + start + ", " + end + "}");
        this.start = start;
        this.end = end;
    }

    public int getStart() {
        return start;
    }

    public int getEnd() {
        return end;
    }
}
