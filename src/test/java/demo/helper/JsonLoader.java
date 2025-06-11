package demo.helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class JsonLoader {
    public static String loadJson(String fileName) {
        try {
            return new String(Files.readAllBytes(Paths.get("src/test/resources/" + fileName)));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load JSON file: " + fileName, e);
        }
    }
}
