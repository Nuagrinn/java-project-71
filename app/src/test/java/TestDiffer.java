import hexlet.code.Differ;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import java.util.stream.IntStream;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestDiffer {
    @Test
    public void testGenerate() throws Exception {

        String data1Path = "src/test/resources/data1.json";
        String data2Path = "src/test/resources/data2.json";

        String correctDiff = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";

        List<String> result = List.of(Differ.generate(data1Path, data2Path).split("\\n"));
        List<String> correctDiffList = List.of(correctDiff.split("\\n"));

        IntStream.range(0, Math.max(result.size(), correctDiffList.size()))
                .forEach(i -> assertEquals(result.get(i).trim(), correctDiffList.get(i).trim()));

        result.forEach(System.out::println);
    }

}
