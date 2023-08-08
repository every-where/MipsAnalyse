package instruction.rule;

import exception.code.InvalidCharCodeException;
import exception.rule.RuleException;

public class UnMatchRule extends Rule {
    public UnMatchRule(int start, int end, String compareCode) throws RuleException {
        super(false, start, end, compareCode);
    }
}
