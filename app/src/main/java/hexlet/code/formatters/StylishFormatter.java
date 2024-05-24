package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class StylishFormatter {

    public static String formatt(List<Map<String, String>> diff) {
        
        StringBuilder sb = new StringBuilder();

        sb.append("{");

        for (var k : diff) {
            if (k.get("state").equals("notchanged")) {
                    sb.append("\n    ").append(k.get("key")).append(": ").append(k.get("actualValue"));
            } else if (k.get("state").equals("changed")) {
                    sb.append("\n  - ").append(k.get("key")).append(": ").append(k.get("prevValue"))
                            .append("\n  + ").append(k.get("key")).append(": ").append(k.get("actualValue"));
            } else if (k.get("state").equals("deleted")) {
                sb.append("\n  - ").append(k.get("key")).append(": ").append(k.get("prevValue"));
            } else {
                sb.append("\n  + ").append(k.get("key")).append(": ").append(k.get("actualValue"));
            }
        }
        sb.append("\n}");

        return sb.toString();
    }

}
