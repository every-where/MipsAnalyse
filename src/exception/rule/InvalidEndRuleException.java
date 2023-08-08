package exception.rule;

public class InvalidEndRuleException extends RuleException {
    private final int end;
    public InvalidEndRuleException(int end) {
        super("非法结束位置{" + end + "}");
        this.end = end;
    }

    public int getEnd() {
        return end;
    }
}
