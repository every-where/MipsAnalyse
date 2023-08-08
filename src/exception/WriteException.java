package exception;

public class WriteException extends Exception{
    private final String fileName;
    public WriteException(String fileName) {
        super(fileName + ": 输入错误");
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
