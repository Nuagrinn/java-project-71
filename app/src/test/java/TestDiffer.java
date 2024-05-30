import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDiffer {
    private static String data1PathJson;
    private static String data2PathJson;
    private static String data1PathYaml;
    private static String data2PathYaml;

    @BeforeAll
    static void init() {
        data1PathJson = "src/test/resources/fixtures/data1.json";
        data2PathJson = "src/test/resources/fixtures/data2.json";
        data1PathYaml = "src/test/resources/fixtures/data1.yaml";
        data2PathYaml = "src/test/resources/fixtures/data2.yaml";
    }

    @Test
    public void testStylishDefaultJson() throws Exception {
        String result = Differ.generate(data1PathJson, data2PathJson);
        String testResult = normalizeNewlines(readFixture("testStylish.txt"));

        assertEquals(testResult, result);
    }

    @Test
    public void testStylishDefaultYaml() throws Exception {
        String result = Differ.generate(data1PathYaml, data2PathYaml);
        String testResult = normalizeNewlines(readFixture("testStylish.txt"));

        assertEquals(testResult, result);
    }

    @Test
    public void testStylishJson() throws Exception {
        testDiffer(data1PathJson, data2PathJson, "stylish", "testStylish.txt");
    }

    @Test
    public void testStylishYaml() throws Exception {
        testDiffer(data1PathYaml, data2PathYaml, "stylish", "testStylish.txt");
    }

    @Test
    public void testPlainJson() throws Exception {
        testDiffer(data1PathJson, data2PathJson, "plain", "testPlain.txt");
    }

    @Test
    public void testPlainYaml() throws Exception {
        testDiffer(data1PathYaml, data2PathYaml, "plain", "testPlain.txt");
    }

    @Test
    public void testJsonJson() throws Exception {
        testDifferJson(data1PathJson, data2PathJson, "json", "testJson.json");
    }

    @Test
    public void testJsonYaml() throws Exception {
        testDifferJson(data1PathYaml, data2PathYaml, "json", "testJson.json");
    }

    private void testDiffer(String data1Path, String data2Path, String format, String expectedFile)
            throws Exception {
        String result = Differ.generate(data1Path, data2Path, format);
        String testResult = normalizeNewlines(readFixture(expectedFile));
        assertEquals(testResult, result);
    }

    private void testDifferJson(String data1Path, String data2Path, String format, String expectedFile)
            throws Exception {
        String result = Differ.generate(data1Path, data2Path, format);
        String testResult = readFixture(expectedFile);
        JSONAssert.assertEquals(testResult, result, false);
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
