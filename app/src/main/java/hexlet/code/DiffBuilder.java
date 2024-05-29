package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public final class DiffBuilder {

    public static List<Map<String, Object>> build(Map<String, Object> map1, Map<String, Object> map2) throws Exception {
        List<Map<String, Object>> diff = new ArrayList<>();

        List<String> combinedKeys = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .sorted()
                .toList();

        for (String key : combinedKeys) {
            Map<String, Object> node = new HashMap<>();
            node.put("key", key);

            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            String state = compareValues(value1, value2, map1.containsKey(key), map2.containsKey(key));
            node.put("state", state);

            switch (state) {
                case "notchanged" -> node.put("actualValue", value1);
                case "changed" -> {
                    node.put("prevValue", value1);
                    node.put("actualValue", value2);
                }
                case "deleted" -> node.put("prevValue", value1);
                case "new" -> node.put("actualValue", value2);
                default -> throw new Exception("Wrong state");
            }

            diff.add(node);
        }

        return diff;
    }

    private static String compareValues(Object value1, Object value2, boolean containsKey1, boolean containsKey2) {
        if (containsKey1 && containsKey2) {
            if (value1 == null && value2 == null) {
                return "notchanged";
            } else if (value1 == null || !value1.equals(value2)) {
                return "changed";
            } else {
                return "notchanged";
            }
        } else if (containsKey1) {
            return "deleted";
        } else {
            return "new";
        }
    }
}
