package instruction.bit;

import exception.bit.BitException;
import exception.bit.InvalidEndBitException;
import exception.bit.InvalidStartBitException;
import exception.bit.InvalidStartEndBitException;
import exception.code.InvalidCharCodeException;
import exception.code.InvalidLengthCodeException;
import util.CheckUtil;

public class BitFlag {

    private final String name;
    private final int start;
    private final int end;

    public BitFlag(String name, int start, int end) throws BitException {
        this.name = name;
        if (start > 31 || start < 0) {
            throw new InvalidStartBitException(start);
        }
        if (end > 31 || end < 0) {
            throw new InvalidEndBitException(end);
        }
        if (start < end) {
            throw new InvalidStartEndBitException(start, end);
        }
        this.start = start;
        this.end = end;
    }

    public String getName() {
        return name;
    }

    public String getBit(String code) throws InvalidCharCodeException, InvalidLengthCodeException {
        CheckUtil.checkBinCode(code, 32);
        return code.substring(31 - start, 32 - end);
    }
}
