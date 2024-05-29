package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {

    public static String formatt(List<Map<String, Object>> diff) throws Exception {

        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for (var k : diff) {
            String key = k.get("key").toString();
            String state = k.get("state").toString();

            switch (state) {
                case "notchanged" -> sb.append("\n    ").append(key).append(": ").append(k.get("actualValue"));
                case "changed" -> {
                    sb.append("\n  - ").append(key).append(": ").append(k.get("prevValue"));
                    sb.append("\n  + ").append(key).append(": ").append(k.get("actualValue"));
                }
                case "deleted" -> sb.append("\n  - ").append(key).append(": ").append(k.get("prevValue"));
                case "new" -> sb.append("\n  + ").append(key).append(": ").append(k.get("actualValue"));
                default -> throw new Exception("Wrong state for foramtting");
            }
        }
        sb.append("\n}");

        return sb.toString();
    }
}
