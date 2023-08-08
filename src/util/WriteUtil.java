package util;

import exception.WriteException;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteUtil {
    private final String fileName;

    public WriteUtil(String fileName) throws WriteException {
        this.fileName = fileName;
        File f = new File(fileName);
        if (f.exists()) {
            if (!f.delete()) {
                throw new WriteException(fileName);
            }
        } else {
            try {
                if (!f.createNewFile()) {
                    throw new WriteException(fileName);
                }
            } catch (IOException e) {
                throw new WriteException(fileName);
            }
        }
    }

    public void write(Object object) throws WriteException {
        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(object.toString());
            System.out.print(object);
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new WriteException(fileName);
        }
    }

    public void writeln(Object object) throws WriteException {
        try {
            File file = new File(fileName);
            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(object.toString());
            bw.newLine();
            System.out.println(object);
            bw.close();
            fw.close();
        } catch (IOException e) {
            throw new WriteException(fileName);
        }
    }

    public void error(Object object) throws WriteException {
        writeln("ERROR: " + object);
    }

    public void warning(Object object) throws WriteException {
        writeln("WARNING: " + object);
    }
}
