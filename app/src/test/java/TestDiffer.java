import hexlet.code.Differ;

import org.junit.jupiter.api.Test;


import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestDiffer {
    @Test
    public void testGenStylishDiff() throws Exception {

        String data1Path = "src/test/resources/fixtures/data1.json";
        String data2Path = "src/test/resources/fixtures/data2.json";

        String result = Differ.generate(data1Path, data2Path, "stylish");
        String testResult = readFixture("testStylish.txt");

        assertEquals(result, testResult);

    }



    private static String readFixture(String fileName) throws Exception {
        Path filePath = Paths.get("src", "test", "resources", "fixtures", fileName)
                .toAbsolutePath().normalize();
        return Files.readString(filePath).trim();
    }


}
