package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class JsonFormatter {

    public static String formatt(List<Map<String, String>> diff) throws Exception {

        ObjectMapper objectMapper = new ObjectMapper();

        return objectMapper.writeValueAsString(diff);

    }

}
