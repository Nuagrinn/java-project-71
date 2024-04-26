import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
    public void testParseJson() throws Exception {
        Parser parser = new Parser();
        String jsonPath = "src/test/resources/data1.json";
        Map<String, Object> result = parser.getData(jsonPath);
        assertEquals(expectedParsed, result);

    }

    @Test
    public void testParseYAML() throws Exception {
        Parser parser = new Parser();
        String yamlPath = "src/test/resources/data1.yaml";
        Map<String, Object> result = parser.getData(yamlPath);
        assertEquals(expectedParsed, result);

    }

    @Test
    public void testParseError() throws Exception {
        Parser parser = new Parser();
        String errorFilePath = "src/test/resources/data3.txt";
        assertThrows(Exception.class, () -> parser.getData(errorFilePath));

    }

}


