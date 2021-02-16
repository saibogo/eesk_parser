package sheets_analize;

import configuration.PatternToFind;
import configuration.SheetConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class EensJson {
    private static final List<String> tagList = new ArrayList<>(Arrays.asList("num\":", "startDate\":", "endDate\":",
            "objects\":", "street\":" , "file\":", "link\":"));

    public static  List<String> convertFileToList(String pathtoJson) {
        List<String> result = new ArrayList<>();
        String currentJSON = "";
        try {
            System.out.println("=".repeat(SheetConfig.repeatNum));
            System.out.println("Попытка чтения полученного JSON-файла " + pathtoJson);
            FileInputStream fileInputStream = new FileInputStream(pathtoJson);
            Scanner scanner = new Scanner(fileInputStream);
            StringBuilder builder = new StringBuilder();
            while (scanner.hasNext()) {
                builder.append(scanner.next());
            }
            currentJSON = builder.toString();
        } catch (FileNotFoundException e) {
            System.out.println("!".repeat(SheetConfig.repeatNum));
            System.out.println("Ошибка открытия файла " + pathtoJson);
            System.exit(0);
        }

        int firstIndex = 0;
        int secondIndex = 0;

        while (currentJSON.contains("{")) {
            firstIndex = currentJSON.indexOf("{");
            secondIndex = currentJSON.indexOf("}");
            result.add(currentJSON.substring(firstIndex, secondIndex + 1));
            currentJSON = currentJSON.substring(secondIndex + 1);
        }

        return result;
    }

    public static List<String> convertJSONToList(String JSONString) {
        List<String> result = new ArrayList<>();


        int firstIndex = 0;
        int secondIndex = 0;

        for (String tag: tagList) {
            firstIndex = JSONString.indexOf(tag) + tag.length() + 1;
            secondIndex = JSONString.substring(firstIndex + 1).indexOf("\"") + firstIndex;
            result.add(JSONString.substring(firstIndex , secondIndex + 1));
        }

        return result;
    }

    public static List<List<String>> convertAllJSONToList(List<String> JSONList) {
        List<List<String>> result = new ArrayList<>();
        for(String tmp: JSONList) {
            result.add(convertJSONToList(tmp));
        }

        return result;
    }

    public static boolean isValidRecord(List<String> record) {

        for (String pattern: PatternToFind.getPatternsList()) {
            for (String str: record) {
                if (str.contains(pattern)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static List<List<String>> filtredJSONLIST(List<List<String>> recordsList) {
        List<List<String>> result = new ArrayList<>();
        for(List<String> record: recordsList) {
            if (isValidRecord(record)) {
                result.add(record);
            }
        }

        return result;
    }
}
