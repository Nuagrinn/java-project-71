import hexlet.code.Differ;

import org.junit.jupiter.api.Test;

public class TestDiffer {
    @Test
    public void testGenJsonDiff() throws Exception {

        String data1Path = "src/test/resources/data1.json";
        String data2Path = "src/test/resources/data2.json";

        String result = Differ.generate(data1Path, data2Path, "stylish");
    }

}
