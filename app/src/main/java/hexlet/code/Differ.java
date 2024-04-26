package hexlet.code;

import java.util.List;
import java.util.stream.Stream;

public class Differ {


    public static String generate(String path1, String path2) throws Exception {

        Parser parser = new Parser();

        var mapJson1 = parser.getData(path1);
        var mapJson2 = parser.getData(path2);

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


}
