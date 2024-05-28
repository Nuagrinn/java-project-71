package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public final class Parser {
    private ObjectMapper objectMapper;
    private Map<String, Object> mapStruct;

    public Map<String, Object> getData(String path) throws Exception {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        Path filePath = Paths.get(path);
        if (!Files.exists(filePath) || !Files.isReadable(filePath)) {
            throw new IOException("File does not exist or is not readable: " + path);
        }

        String fileFormat = filePath.toString().substring(filePath.toString().lastIndexOf(".") + 1);

        switch (fileFormat) {
            case "yaml":
            case "yml":
                objectMapper = new YAMLMapper();
                String stringYaml = new String(Files.readAllBytes(filePath));
                mapStruct = objectMapper.readValue(stringYaml,
                        new TypeReference<Map<String, Object>>() { });

                return mapStruct;
            case "json":
                objectMapper = new ObjectMapper();
                String stringJson = new String(Files.readAllBytes(filePath));
                mapStruct = objectMapper.readValue(stringJson,
                        new TypeReference<Map<String, Object>>() { });

                return mapStruct;
            default:
                throw new Exception("Unsupported file format: " + fileFormat);

        }

    }

}
