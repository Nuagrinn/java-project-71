import hexlet.code.Parser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SuppressWarnings("magicnumber")
public class TestParser {

    private static Map<String, Object> expectedParsed = new HashMap<>();

    @BeforeAll
    static void init() {
        expectedParsed.put("host", "hexlet.io");
        expectedParsed.put("timeout", 50);
        expectedParsed.put("proxy", "123.234.53.22");
        expectedParsed.put("follow", false);
    }

    public static Map<String, Object> getExpectedParsed() {
        return expectedParsed;
    }

    @Test
    public void testParseJson() throws Exception {
        Parser parser = new Parser();
        String jsonPath = "src/test/resources/fixtures/data1.json";
        Map<String, Object> result = parser.getData(jsonPath);
        assertEquals(getExpectedParsed(), result);
    }

    @Test
    public void testParseYAML() throws Exception {
        Parser parser = new Parser();
        String yamlPath = "src/test/resources/fixtures/data1.yaml";
        Map<String, Object> result = parser.getData(yamlPath);
        assertEquals(getExpectedParsed(), result);
    }

    @Test
    public void testParseError() {
        Parser parser = new Parser();
        String errorFilePath = "src/test/resources/fixtures/data3.txt";
        assertThrows(Exception.class, () -> parser.getData(errorFilePath));
    }
}
