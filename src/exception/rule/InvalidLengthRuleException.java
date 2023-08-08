package exception.rule;

public class InvalidLengthRuleException extends RuleException {
    private final int tarLength;
    private final int realLength;

    public InvalidLengthRuleException(int tarLength, int realLength) {
        super("非法长度{" + tarLength + ", " + realLength + "}");
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
