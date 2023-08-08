package instruction.rule;

import exception.code.InvalidCharCodeException;
import exception.rule.RuleException;

public class MatchRule extends Rule {
    public MatchRule(int start, int end, String compareCode) throws RuleException {
        super(true, start, end, compareCode);
    }
}
