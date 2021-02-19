package sheets_analize;

import configuration.PatternToFind;
import configuration.SheetConfig;
import support_classes.ListRecord;
import support_classes.MapRecord;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class EensJson {
    private static final List<String> tagList = new ArrayList<>(Arrays.asList("num\":", "startDate\":", "endDate\":",
            "objects\":", "street\":" , "file\":", "link\":"));

    public static ListRecord convertFileToList(String pathtoJson) {
        ListRecord result = new ListRecord();
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

        int firstIndex;
        int secondIndex;

        while (currentJSON.contains("{")) {
            firstIndex = currentJSON.indexOf("{");
            secondIndex = currentJSON.indexOf("}");
            result.add(currentJSON.substring(firstIndex, secondIndex + 1));
            currentJSON = currentJSON.substring(secondIndex + 1);
        }

        return result;
    }

    public static MapRecord convertJSONToList(String JSONString) {
        MapRecord result = new MapRecord();


        int firstIndex;
        int secondIndex;
        String newTag;

        for (String tag: tagList) {
            firstIndex = JSONString.indexOf(tag) + tag.length() + 1;
            secondIndex = JSONString.substring(firstIndex + 1).indexOf("\"") + firstIndex;
            newTag = tag.substring(0, tag.length() - 2);
            result.put(newTag, JSONString.substring(firstIndex , secondIndex + 1));
        }

        return result;
    }

    public static List<MapRecord> convertAllJSONToList(List<String> JSONList) {
        List<MapRecord> result = new ArrayList<>();
        for(String tmp: JSONList) {
            result.add(convertJSONToList(tmp));
        }

        return result;
    }

    public static boolean isValidRecord(MapRecord record) {

        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            Date messageDate = dateFormat.parse(record.get("startDate"));
            if (messageDate.getTime() + 23 * 60 * 60 * 1000 < Calendar.getInstance().getTime().getTime()) {
                return false;
            }
        } catch (ParseException e) {
            System.out.println("!".repeat(SheetConfig.repeatNum));
            System.out.println("Неверный формат даты -> " + record.get("startDate"));
            return false;
        }

        for (String pattern: PatternToFind.getPatternsList()) {
            for (String str: record.values()) {
                if (str.contains(pattern)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static List<MapRecord> filtredJSONLIST(List<MapRecord> recordsList) {
        List<MapRecord> result = new ArrayList<>();
        for(MapRecord record: recordsList) {
            if (isValidRecord(record)) {
                result.add(record);
            }
        }

        return result;
    }
}
