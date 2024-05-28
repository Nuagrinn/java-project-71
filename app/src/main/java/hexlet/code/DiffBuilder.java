package hexlet.code;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public final class DiffBuilder {

    private List<Map<String, Object>> diff = new ArrayList<>();

    public List<Map<String, Object>> build(Map<String, Object> map1, Map<String, Object> map2) {

        var keySet1 = map1.keySet().stream().toList();
        var keySet2 = map2.keySet().stream().toList();

        List<String> combinedKeyList = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .sorted()
                .toList();

        for (String k : combinedKeyList) {
            Map<String, Object> node = new HashMap<>();
            boolean containsKey1 = keySet1.contains(k);
            boolean containsKey2 = keySet2.contains(k);
            Object value1 = map1.get(k);
            Object value2 = map2.get(k);

            node.put("key", k);

            if (containsKey1 && containsKey2) {
                if (value1 != null && value1.equals(value2)) {
                    node.put("state", "notchanged");
                    node.put("actualValue", value1);
                } else {
                    node.put("state", "changed");
                    node.put("prevValue", value1);
                    node.put("actualValue", value2);
                }
            } else if (containsKey1) {
                node.put("state", "deleted");
                node.put("prevValue", value1);
            } else {
                node.put("state", "new");
                node.put("actualValue", value2);
            }
            diff.add(node);
        }

        return diff;
    }
}
