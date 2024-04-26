import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestParser {

    static Map<String, Object> expectedParsed = new HashMap<>();

    @BeforeAll
    static void init() {

        expectedParsed.put("host", "hexlet.io");
        expectedParsed.put("timeout", 50);
        expectedParsed.put("proxy", "123.234.53.22");
        expectedParsed.put("follow", false);
    }

    @Test
    public void testParseFile() throws Exception {

        String jsonPath = "src/test/resources/data1.json";
        Map<String, Object> result = Parser.getData(jsonPath);
        assertEquals(expectedParsed, result);

    }

}


