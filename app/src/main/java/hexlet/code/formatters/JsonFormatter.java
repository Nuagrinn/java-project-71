package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Map;

public class JsonFormatter {

    public static String formatt(Map<String, Object> diff, String format) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(diff);

    }

}
