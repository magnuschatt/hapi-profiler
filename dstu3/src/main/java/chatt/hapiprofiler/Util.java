package chatt.hapiprofiler;

import java.io.*;
import java.lang.reflect.Field;

public class Util {

    public static String extractUnlistedType(Field field) {
        String type = field.getGenericType().getTypeName();
        return unlist(type);
    }

    public static String unlist(String type) {
        if (type.startsWith("java.util.List<") || type.startsWith("List<"))
            type = type.substring(type.indexOf('<')+1, type.lastIndexOf('>'));
        return type;
    }

    public static void write(String file, String content) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(new File(file)));
            bw.write(content);
            bw.close();
        }catch (IOException ex) {
            System.out.println(ex.toString());
        }
    }
}
