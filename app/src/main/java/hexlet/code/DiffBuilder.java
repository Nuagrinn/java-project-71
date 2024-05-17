package hexlet.code;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class DiffBuilder {

    private Map<String, Object> diff = new HashMap<>();

    public Map<String, Object> build(Map<String, Object> map1, Map<String, Object> map2) {

        var keySet1 = map1.keySet().stream().sorted().toList();
        var keySet2 = map2.keySet().stream().sorted().toList();

        List<String> combinedKeyList = Stream.concat(map1.keySet().stream(), map2.keySet().stream())
                .distinct()
                .sorted()
                .toList();
        

        for (var k : combinedKeyList) {
            Map<String,String> node = new HashMap<>();
            if (keySet1.contains(k) && keySet2.contains(k)) {
                if (map1.get(k).equals(map2.get(k))) {
                    node.put("state", "notchanged");
                    node.put("prevValue", null);
                    node.put("actualValue", map1.get(k).toString());
                    
                } else {

                    node.put("state", "changed");
                    node.put("prevValue", map1.get(k).toString());
                    node.put("actualValue", map2.get(k).toString());
                }
            } else if (keySet1.contains(k)) {
                node.put("state", "deleted");
                node.put("prevValue", map1.get(k).toString());
                node.put("actualValue", null);
            } else {
                node.put("state", "new");
                node.put("prevValue",null);
                node.put("actualValue", map2.get(k).toString());           
            }
            
            diff.put(k, node);
        }

        return diff;
    }

}
