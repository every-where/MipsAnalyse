package instruction;

import exception.WriteException;
import exception.analyse.AnalyseException;
import exception.analyse.DuplicateInstructionAnalyseException;
import exception.analyse.UnMatchedInstructionAnalyseException;
import exception.code.InvalidCharCodeException;
import exception.code.InvalidLengthCodeException;
import instruction.bit.BitFlag;
import instruction.instruction.Instruction;
import util.TransUtil;
import util.WriteUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class InstructionManager {
    private final ArrayList<Instruction> instructions;
    private final HashMap<String, BitFlag> bitFlagMap;

    public InstructionManager(ArrayList<Instruction> instructions, HashMap<String, BitFlag> bitFlagMap) {
        this.instructions = instructions;
        this.bitFlagMap = bitFlagMap;
    }

    public void instructionAnalyse(ArrayList<String> hexCodeList, WriteUtil writeUtil) throws InvalidCharCodeException, InvalidLengthCodeException, AnalyseException, WriteException {
        for (String hexCode:hexCodeList) {
            String binCode = TransUtil.hexToBinCode(hexCode.trim());
            boolean flag = false;
            String name = "";
            for (Instruction instruction:instructions) {
                if (instruction.checkBinCode(binCode)) {
                    writeUtil.writeln(instruction.analyseBinCode(binCode, bitFlagMap));
                    if (flag) {
                        throw new DuplicateInstructionAnalyseException(hexCode, name, instruction.getName());
                    } else {
                        flag = true;
                        name = instruction.getName();
                    }
                }
            }
            if (!flag) {
                throw new UnMatchedInstructionAnalyseException(hexCode);
            }
        }
    }
}
