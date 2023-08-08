package code;

import java.io.*;
import java.util.ArrayList;

public class CodeReader {
    public static ArrayList<String> readCode(String path) {
        ArrayList<String> result = new ArrayList<>();
        try {
            File f = new File(path);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String code;
            while ((code = br.readLine()) != null) {
                if (!code.trim().isEmpty()) {
                    result.add(code.trim());
                }
            }
            br.close();
            fr.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
