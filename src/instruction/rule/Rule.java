package instruction.rule;

import exception.code.InvalidCharCodeException;
import exception.code.InvalidLengthCodeException;
import exception.rule.*;
import util.CheckUtil;
import util.TransUtil;

public abstract class Rule {
    private static int distributedId = 0;

    // 唯一 id
    private final int id;

    // 是否为正向匹配
    private final boolean match;

    private final int start;

    private final int end;

    // 二进制比较码
    private final String binCompareCode;

    public Rule(boolean match, int start, int end, String compareCode) throws RuleException {
        this.id = ++distributedId;
        this.match = match;
        if (start > 31 || start < 0) {
            throw new InvalidStartRuleException(start);
        }
        if (end > 31 || end < 0) {
            throw new InvalidEndRuleException(end);
        }
        if (start < end) {
            throw new InvalidStartEndRuleException(start, end);
        }
        this.start = start;
        this.end = end;
        compareCode = compareCode.trim().replaceAll(" ", "");
        try {
            if (compareCode.startsWith("0x")) {
                CheckUtil.checkHexCode(compareCode);
                binCompareCode = TransUtil.hexToBinCode(compareCode);
            } else {
                binCompareCode = compareCode;
            }
        } catch (InvalidCharCodeException e) {
            throw new InvalidCharRuleException(e.getC(), e.getPos());
        }
        if (binCompareCode.length() != start - end + 1) {
            throw new InvalidLengthRuleException(start - end + 1, binCompareCode.length());
        }
    }

    public int getId() {
        return id;
    }

    public boolean checkBinCode(String binCode) throws InvalidCharCodeException, InvalidLengthCodeException {
        CheckUtil.checkBinCode(binCode, 32);
        return (!match) ^ binCode.substring(31 - start, 32 -end).equals(binCompareCode);
    }

    public boolean checkHexCode(String hexCode) throws InvalidCharCodeException, InvalidLengthCodeException {
        CheckUtil.checkHexCode(hexCode, 8);
        return checkBinCode(TransUtil.hexToBinCode(hexCode));
    }
}
