package util;

import exception.code.InvalidCharCodeException;
import exception.code.InvalidLengthCodeException;

public class CheckUtil {
    public static void checkBinCode(String binCode) throws InvalidCharCodeException {
        for (int i = 0;i < binCode.length();i++) {
            if (binCode.charAt(i) != '0' && binCode.charAt(i) != '1') {
                throw new InvalidCharCodeException(binCode, binCode.charAt(i), i);
            }
        }
    }

    public static void checkBinCode(String binCode, int length) throws InvalidLengthCodeException, InvalidCharCodeException {
        if (binCode.length() != length) {
            throw new InvalidLengthCodeException(binCode, length, binCode.length());
        }
        checkBinCode(binCode);
    }

    public static void checkHexCode(String hexCode) throws InvalidCharCodeException {
        if (hexCode.startsWith("0x")) {
            for (int i = 2;i < hexCode.length();i++) {
                if (!isHexChar(hexCode.charAt(i))) {
                    throw new InvalidCharCodeException(hexCode, hexCode.charAt(i), i);
                }
            }
        } else {
            for (int i = 0;i < hexCode.length();i++) {
                if (!isHexChar(hexCode.charAt(i))) {
                    throw new InvalidCharCodeException(hexCode, hexCode.charAt(i), i);
                }
            }
        }
    }

    public static void checkHexCode(String hexCode, int length) throws InvalidLengthCodeException, InvalidCharCodeException {
        if (hexCode.startsWith("0x")) {
            if (hexCode.length() != length - 2) {
                throw new InvalidLengthCodeException(hexCode, length, hexCode.length());
            }
        } else {
            if (hexCode.length() != length) {
                throw new InvalidLengthCodeException(hexCode, length, hexCode.length());
            }
        }
        checkHexCode(hexCode);
    }

    private static boolean isHexChar(char c) {
        return Character.isDigit(c) ||
                c >= 'a' && c <= 'f' ||
                c >= 'A' && c <= 'F';
    }
}
