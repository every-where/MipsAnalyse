import code.CodeReader;
import exception.LoadException;
import exception.WriteException;
import exception.analyse.AnalyseException;
import exception.bit.BitException;
import exception.code.InvalidCharCodeException;
import exception.code.InvalidLengthCodeException;
import exception.rule.RuleException;
import instruction.InstructionLoader;
import instruction.InstructionManager;
import util.WriteUtil;

import javax.security.auth.login.LoginException;

public class Main {
    public static void main(String[] args) throws WriteException {
        WriteUtil writeUtil = new WriteUtil("tarCode.txt");
        try {
            InstructionLoader instructionLoader = new InstructionLoader("params.xml");
            InstructionManager instructionManager = new InstructionManager(instructionLoader.getInstructions(), instructionLoader.getBitFlagMap());
            instructionManager.instructionAnalyse(CodeReader.readCode("sourceCode.txt"), writeUtil);
        } catch (BitException | RuleException | LoadException | InvalidCharCodeException | InvalidLengthCodeException |
                 AnalyseException e) {
            writeUtil.error(e);
        }
    }
}
