package exception.rule;

public class InvalidStartRuleException extends RuleException {
    private final int start;

    public InvalidStartRuleException(int start) {
        super("非法开始位置{" + start + "}");
        this.start = start;
    }

    public int getStart() {
        return start;
    }
}
