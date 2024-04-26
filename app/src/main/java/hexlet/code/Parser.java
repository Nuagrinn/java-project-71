package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Parser {

    public static Map<String, Object> getData(String path) throws Exception {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        Path filePath = Paths.get(path);
        if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
            throw new IOException("File does not exist or is not readable: " + path);
        }

        ObjectMapper objectMapper = new ObjectMapper();
        String stringJson = new String(Files.readAllBytes(filePath));
        Map<String, Object> mapJson = objectMapper.readValue(stringJson,
                new TypeReference<Map<String, Object>>() { });

        return mapJson;

    }

}
