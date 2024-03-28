package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class Differ {

    public static void generate(String path1, String path2) {
        try {

            Path filePath1 = Paths.get(path1);
            Path filePath2 = Paths.get(path2);
            String stringJson1 = new String(Files.readAllBytes(filePath1));
            String stringJson2 = new String(Files.readAllBytes(filePath2));
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, Object> mapJson1 = objectMapper.readValue(stringJson1,
                    new TypeReference<Map<String, Object>>() { });
            Map<String, Object> mapJson2 =objectMapper.readValue(stringJson2,
                    new TypeReference<Map<String, Object>>() { });

//            mapJson1.forEach((k,v) -> System.out.println(k + ": " + v));
//            System.out.println("------------------------------------------");
//            mapJson2.forEach((k,v) -> System.out.println(k + ": " + v));

            var keyList1 = mapJson1.keySet().stream().sorted().toList();
            var keyList2 = mapJson2.keySet().stream().sorted().toList();

//            TODO: дописать обход чтобы учитывались новые значения из keyList2
            // вар1: поочередный обход 1ого и 2ого массива и сравнение элементов
            System.out.println("{");
            for(var k : keyList1) {
                if(mapJson2.containsKey(k)) {
                    if(mapJson1.get(k).equals(mapJson2.get(k))) {
                        System.out.println("  " + k + ": " + mapJson1.get(k));
                    } else {
                        System.out.println("- " + k + ": " + mapJson1.get(k));
                        System.out.println("+ " + k + ": " + mapJson2.get(k));
                    }
                } else {
                    System.out.println("- " + k + ": " + mapJson1.get(k));
                }
            }
            System.out.println("}");


        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}