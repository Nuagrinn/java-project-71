package hexlet.code;

public class Differ {

    public static String generate(String path1, String path2) throws Exception {

        return generate(path1, path2, "stylish");
    }

    public static String generate(String path1, String path2, String format) throws Exception {
        Parser parser = new Parser();

        var mapJson1 = parser.getData(path1);
        var mapJson2 = parser.getData(path2);

        DiffBuilder diffBuilder = new DiffBuilder();

        var diff = diffBuilder.build(mapJson1, mapJson2);

        return Formatter.formatt(diff, format);
    }
}
