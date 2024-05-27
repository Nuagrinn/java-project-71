package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String format(List<Map<String, String>> diff) throws Exception {
        StringBuilder result = new StringBuilder();

        for (Map<String, String> node : diff) {
            String key = node.get("key");
            String state = node.get("state");

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

    private static String formatValue(String value) {
        if (value == null) {
            return "null";
        }
        if (value.startsWith("{") || value.startsWith("[") || value.equals("[complex value]")) {
            return "[complex value]";
        }
        if (value.equals("true") || value.equals("false") || value.matches("-?\\d+(\\.\\d+)?")) {
            return value;
        }
        return "'" + value + "'";
    }
}
