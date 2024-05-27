package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class PlainFormatter {

    public static String format(List<Map<String, String>> diff) {
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
                    result.append(String.format("Property '%s' was updated. From %s to %s\n", key, prevValue, actualValue));
                    break;
                case "deleted":
                    prevValue = formatValue(node.get("prevValue"));
                    result.append(String.format("Property '%s' was removed\n", key));
                    break;
                case "new":
                    actualValue = formatValue(node.get("actualValue"));
                    result.append(String.format("Property '%s' was added with value: %s\n", key, actualValue));
                    break;
            }
        }

        return result.toString().trim();
    }

    private static String formatValue(String value) {
        if (value == null) {
            return "null";
        }
        if (value.startsWith("{") || value.startsWith("[") || value.startsWith("complex value")) {
            return "[complex value]";
        }
        if (value.equals("true") || value.equals("false") || value.matches("\\d+")) {
            return value;
        }
        return String.format("'%s'", value);
    }
}
