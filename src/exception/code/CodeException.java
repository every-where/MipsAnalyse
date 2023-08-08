package exception.code;

public abstract class CodeException extends Exception {
    private final String code;
    public CodeException(String code, String message) {
        super(code + ": " + message);
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
