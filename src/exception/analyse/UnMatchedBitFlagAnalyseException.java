package exception.analyse;

public class UnMatchedBitFlagAnalyseException extends AnalyseException {
    private final String name;

    public UnMatchedBitFlagAnalyseException(String name) {
        super("无匹配BitFlag{" + name + "}");
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
