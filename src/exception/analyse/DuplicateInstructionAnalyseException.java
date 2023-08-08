package exception.analyse;

public class DuplicateInstructionAnalyseException extends AnalyseException {
    private final String hexCode;
    private final String name1;
    private final String name2;

    public DuplicateInstructionAnalyseException(String hexCode, String name1, String name2) {
        super("指令重复{" + hexCode + ", " + name1 + ", " + name2 + "}");
        this.hexCode = hexCode;
        this.name1 = name1;
        this.name2 = name2;
    }

    public String getHexCode() {
        return hexCode;
    }

    public String getName1() {
        return name1;
    }

    public String getName2() {
        return name2;
    }
}
