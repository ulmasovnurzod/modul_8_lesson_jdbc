package uz.pdp.utilFile;



import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonUtil {

    private static final ObjectMapper mapper = new ObjectMapper();

    // Bitta obyektni JSON faylga yozish
    public static <T> void writeObjectToJsonFile(T object, String fileName) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(fileName), object);
            System.out.println("✅ JSON fayl yaratildi: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // List ni JSON faylga yozish
    public static <T> void writeListToJsonFile(List<T> list, String fileName) {
        try {
            mapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(fileName), list);
            System.out.println("✅ JSON fayl yaratildi: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

