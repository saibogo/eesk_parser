
import configuration.PatternToFind;
import configuration.SheetConfig;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sheets_analize.MessageList;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFormatException {

        System.out.println("Opening file " + SheetConfig.pathToFile);
        XSSFWorkbook workbook = new XSSFWorkbook(new File(SheetConfig.pathToFile));
        List<List<String>> data = MessageList.convertXLSToList(workbook);
        System.out.println("Ищется " + PatternToFind.getPatternsList());

        List<List<String>> filtredData = MessageList.filtredList(data);

        System.out.println("Всего найдено совпадений " + filtredData.size());

        for  (List<String> tmp: filtredData) {
            System.out.println(tmp);
        }

        }
    }
