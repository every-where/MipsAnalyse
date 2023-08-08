package util;

import exception.code.InvalidCharCodeException;

public class TransUtil {
    public static String alignBinCodeLength(String binCode, int length) throws InvalidCharCodeException {
        CheckUtil.checkBinCode(binCode);
        StringBuilder binCodeBuilder = new StringBuilder(binCode);
        while (binCodeBuilder.length() < length) {
            binCodeBuilder.insert(0, "0");
        }
        return binCodeBuilder.toString();
    }

    public static String alignHexCodeLength(String hexCode, int length) throws InvalidCharCodeException {
        CheckUtil.checkHexCode(hexCode);
        StringBuilder hexCodeBuilder = new StringBuilder(hexCode);
        if (hexCode.startsWith("0x")) {
            while (hexCodeBuilder.length() < length - 2) {
                hexCodeBuilder.insert(2, "0");
            }
        } else {
            while (hexCodeBuilder.length() < length) {
                hexCodeBuilder.insert(0, "0");
            }
        }
        return hexCodeBuilder.toString();
    }

    public static String hexToBinCode(String hexCode) throws InvalidCharCodeException {
        CheckUtil.checkHexCode(hexCode);
        if (hexCode.startsWith("0x")) {
            hexCode = hexCode.substring(2);
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < 8; i++) {
            switch (hexCode.charAt(i)) {
                case '0' -> result.append("0000");
                case '1' -> result.append("0001");
                case '2' -> result.append("0010");
                case '3' -> result.append("0011");
                case '4' -> result.append("0100");
                case '5' -> result.append("0101");
                case '6' -> result.append("0110");
                case '7' -> result.append("0111");
                case '8' -> result.append("1000");
                case '9' -> result.append("1001");
                case 'a', 'A' -> result.append("1010");
                case 'b', 'B' -> result.append("1011");
                case 'c', 'C' -> result.append("1100");
                case 'd', 'D' -> result.append("1101");
                case 'e', 'E' -> result.append("1110");
                case 'f', 'F' -> result.append("1111");
            }
        }
        return result.toString();
    }

    public static String binToHexCode(String binCode) throws InvalidCharCodeException {
        CheckUtil.checkBinCode(binCode);
        if (binCode.length() % 4 != 0) {
            binCode = alignBinCodeLength(binCode, (binCode.length() + 4) / 4 * 4);
        }
        StringBuilder result = new StringBuilder("0x");
        for (int i = 0;i < binCode.length() / 4;i++) {
            switch (binCode.substring(i * 4, (i + 1) * 4)) {
                case "0000" -> result.append("0");
                case "0001" -> result.append("1");
                case "0010" -> result.append("2");
                case "0011" -> result.append("3");
                case "0100" -> result.append("4");
                case "0101" -> result.append("5");
                case "0110" -> result.append("6");
                case "0111" -> result.append("7");
                case "1000" -> result.append("8");
                case "1001" -> result.append("9");
                case "1010" -> result.append("a");
                case "1011" -> result.append("b");
                case "1100" -> result.append("c");
                case "1101" -> result.append("d");
                case "1110" -> result.append("e");
                case "1111" -> result.append("f");
            }
        }
        return result.toString();
    }

    public static long binToOctCode(String binCode) throws InvalidCharCodeException {
        CheckUtil.checkBinCode(binCode);
        long result = 0;
        for (int i = 0;i < binCode.length();i++) {
            result = result * 2 + (binCode.charAt(i) - '0');
        }
        return result;
    }
}
