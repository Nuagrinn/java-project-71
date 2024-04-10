package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import java.util.stream.Stream;

public class Differ {

    public static String generate(String path1, String path2) throws Exception {

        var mapJson1 = getData(path1);
        var mapJson2 = getData(path2);

        var keyList1 = mapJson1.keySet().stream().sorted().toList();
        var keyList2 = mapJson2.keySet().stream().sorted().toList();

        List<String> combinedKeyList = Stream.concat(keyList1.stream(), keyList2.stream())
                .distinct()
                .sorted()
                .toList();

        StringBuilder jsonBuilder = new StringBuilder();
        jsonBuilder.append("{");

        for (var k : combinedKeyList) {
            if (keyList1.contains(k) && keyList2.contains(k)) {
                if (mapJson1.get(k).equals(mapJson2.get(k))) {
                    jsonBuilder.append("\n   ").append(k).append(": ").append(mapJson1.get(k));
                } else {
                    jsonBuilder.append("\n - ").append(k).append(": ").append(mapJson1.get(k))
                            .append("\n + ").append(k).append(": ").append(mapJson2.get(k));
                }
            } else if (keyList1.contains(k)) {
                jsonBuilder.append("\n - ").append(k).append(": ").append(mapJson1.get(k));
            } else {
                jsonBuilder.append("\n + ").append(k).append(": ").append(mapJson2.get(k));
            }
        }

        jsonBuilder.append("\n}");

        return jsonBuilder.toString();

    }

    public static  Map<String, Object> getData(String path) throws Exception {
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
