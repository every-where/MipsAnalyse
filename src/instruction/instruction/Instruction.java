package instruction.instruction;

import exception.analyse.AnalyseException;
import exception.analyse.InvalidCharAnalyseException;
import exception.analyse.UnMatchedBitFlagAnalyseException;
import exception.code.InvalidCharCodeException;
import exception.code.InvalidLengthCodeException;
import instruction.bit.BitFlag;
import instruction.rule.Rule;
import util.CheckUtil;
import util.TransUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class Instruction {
    private final String name;
    private final ArrayList<Rule> rules;

    private final String output;

    public Instruction(String name, ArrayList<Rule> rules, String output) {
        this.name = name;
        this.rules = new ArrayList<>(rules);
        this.output = output;
    }

    public String getName() {
        return name;
    }

    public boolean checkBinCode(String binCode) throws InvalidLengthCodeException, InvalidCharCodeException {
        for (Rule rule:rules) {
            if (!rule.checkBinCode(binCode)) {
                return false;
            }
        }
        return true;
    }

    public String analyseBinCode(String binCode, HashMap<String, BitFlag> bitFlagMap) throws InvalidLengthCodeException, InvalidCharCodeException, AnalyseException {
        CheckUtil.checkBinCode(binCode, 32);
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < output.length()) {
            char c = output.charAt(i);
            switch (c) {
                case '\\' -> {
                    i++;
                    if (i < output.length() && (output.charAt(i) == '\\' || output.charAt(i) == '$')) {
                        sb.append(output.charAt(i));
                        i++;
                    } else {
                        throw new InvalidCharAnalyseException('\\', i - 1);
                    }
                }
                case '$' -> {
                    StringBuilder builder = new StringBuilder();
                    i++;
                    while (i < output.length() && output.charAt(i) != '$') {
                        builder.append(output.charAt(i));
                        i++;
                    }
                    if (i == output.length()) {
                        throw new InvalidCharAnalyseException(' ', i);
                    } else {
                        i++;
                        if (builder.toString().equals("name")) {
                            sb.append(name);
                        } else {
                            BitFlag bitFlag = bitFlagMap.get(builder.toString());
                            if (bitFlag != null) {
                                sb.append(TransUtil.binToOctCode(bitFlag.getBit(binCode)));
                            } else {
                                throw new UnMatchedBitFlagAnalyseException(builder.toString());
                            }
                        }
                    }
                }
                default -> {
                    sb.append(output.charAt(i++));
                }
            }
        }
        return sb.toString();
    }

}
