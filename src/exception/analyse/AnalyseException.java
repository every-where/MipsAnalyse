package exception.analyse;

public abstract class AnalyseException extends Exception {
    public AnalyseException(String message) {
        super("analyse: " + message);
    }
}
