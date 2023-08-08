package exception;

public class LoadException extends Exception {
    private final String fileName;
    public LoadException(String fileName) {
        super(fileName + ": 解析错误");
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
