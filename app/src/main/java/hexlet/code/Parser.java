package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.util.Map;

public final class Parser {

    public Map<String, Object> parse(String data, String format) throws Exception {
        ObjectMapper objectMapper;

        switch (format) {
            case "yaml":
            case "yml":
                objectMapper = new YAMLMapper();
                return objectMapper.readValue(data, new TypeReference<Map<String, Object>>() { });
            case "json":
                objectMapper = new ObjectMapper();
                return objectMapper.readValue(data, new TypeReference<Map<String, Object>>() { });
            default:
                throw new Exception("Unsupported format: " + format);
        }
    }
}
