package hexlet.code;

import hexlet.code.formatters.JsonFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String formatt(List<Map<String, String>> diff, String format) throws Exception {

        return switch (format) {
            case "stylish" -> StylishFormatter.formatt(diff);
            case "json" -> JsonFormatter.formatt(diff);
            default -> throw new Exception("Unexpected formatt");
        };

    }

}
