package exception.analyse;

public class UnMatchedInstructionAnalyseException extends AnalyseException {
    private final String hexCode;

    public UnMatchedInstructionAnalyseException(String hexCode) {
        super("未识别指令{" + hexCode + "}");
        this.hexCode = hexCode;
    }

    public String getHexCode() {
        return hexCode;
    }
}
