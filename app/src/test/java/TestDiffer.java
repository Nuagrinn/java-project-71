import hexlet.code.Differ;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.skyscreamer.jsonassert.JSONAssert;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDiffer {
    @Test
    public void testStylish() throws Exception {

        String data1Path = "src/test/resources/fixtures/data1.json";
        String data2Path = "src/test/resources/fixtures/data2.json";

        String result = Differ.generate(data1Path, data2Path, "stylish");
        String testResult = normalizeNewlines(readFixture("testStylish.txt"));

        assertEquals(result, testResult);

    }

    @Test
    public void testStylishYaml() throws Exception {

        String data1Path = "src/test/resources/fixtures/data1.yaml";
        String data2Path = "src/test/resources/fixtures/data2.yaml";

        String result = Differ.generate(data1Path, data2Path, "stylish");
        String testResult = normalizeNewlines(readFixture("testStylish.txt"));

        assertEquals(result, testResult);

    }

    @Test
    public void testPlain() throws Exception {

        String data1Path = "src/test/resources/fixtures/data1.json";
        String data2Path = "src/test/resources/fixtures/data2.json";

        String result = Differ.generate(data1Path, data2Path, "plain");
        String testResult = normalizeNewlines(readFixture("testPlain.txt"));

        assertEquals(result, testResult);

    }

    @Test
    public void testJson() throws Exception {

        String data1Path = "src/test/resources/fixtures/data1.json";
        String data2Path = "src/test/resources/fixtures/data2.json";

        String result = Differ.generate(data1Path, data2Path, "json");
        String testResult = readFixture("testJson.json");

        JSONAssert.assertEquals(result, testResult, false);
    }

    private static String readFixture(String fileName) throws Exception {
        Path filePath = Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
        return Files.readString(filePath).trim();
    }

    private static String normalizeNewlines(String input) {
        return input.replaceAll("\\r\\n", "\n").replaceAll("\\r", "\n");
    }

}
