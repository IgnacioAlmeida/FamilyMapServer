package AncestorData;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class FileParser {

    public static StringBuilder startParse(String filePath) throws IOException {
        StringBuilder data = new StringBuilder();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNext()) {
                String str = scanner.next();
                data.append(str);
            }
        }
        catch(IOException exception) {
            throw new IOException();
        }

        return data;
    }

}
