package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String format(List<Map<String, Object>> diff) throws Exception {
        StringBuilder result = new StringBuilder();

        for (Map<String, Object> node : diff) {
            String key = (String) node.get("key");
            String state = (String) node.get("state");

            switch (state) {
                case "notchanged":
                    break;
                case "changed":
                    String prevValue = formatValue(node.get("prevValue"));
                    String actualValue = formatValue(node.get("actualValue"));
                    result.append("Property '" + key + "' was updated. From " + prevValue + " to " + actualValue + "\n");
                    break;
                case "deleted":
                    result.append("Property '" + key + "' was removed\n");
                    break;
                case "new":
                    String newValue = formatValue(node.get("actualValue"));
                    result.append("Property '" + key + "' was added with value: " + newValue + "\n");
                    break;
                default:
                    throw new Exception("Unexpected state");
            }
        }

        return result.toString().trim();
    }

    private static String formatValue(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String) {
            return "'" + value + "'";
        }
        if (value instanceof Map || value instanceof List) {
            return "[complex value]";
        }
        return value.toString();
    }
}
