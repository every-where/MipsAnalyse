package exception.rule;

public abstract class RuleException extends Exception {
    public RuleException(String message) {
        super("rule: " + message);
    }
}
