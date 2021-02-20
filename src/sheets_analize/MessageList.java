package sheets_analize;

import configuration.PatternToFind;
import configuration.SheetConfig;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import support_classes.ExitStatus;
import support_classes.ListRecord;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class MessageList {
    public static List<ListRecord> convertXLSToList(XSSFWorkbook workbook) {
        List<ListRecord> result = new ArrayList<>();

        XSSFSheet sheet = workbook.getSheetAt(SheetConfig.SheetIndex);
        System.out.println("Opening sheet:" + sheet.getSheetName());
        int maxRow = sheet.getPhysicalNumberOfRows();
        System.out.println("Число записей на странице = " + maxRow);

        XSSFRow row;
        XSSFCell cell;
        ListRecord rowList;

        for (int i = 0; i <= maxRow; i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            rowList = new ListRecord();

            for (int j = SheetConfig.startCellIndex; j <= SheetConfig.stopCellIndex; j++) {
                cell = row.getCell(j);
                rowList.add(SheetsData.getCellData(cell));
            }

            result.add(rowList);
        }
            return result;
    }

    public static boolean isPatternFound(ListRecord dataList) {

        List<DateFormat> dateFormatList = new ArrayList<>();
        dateFormatList.add(new SimpleDateFormat("E MMM dd HH:mm:ss zzzz yyyy", Locale.ENGLISH));
        dateFormatList.add(new SimpleDateFormat("dd.MM.yyyy"));
        dateFormatList.add(new SimpleDateFormat("dd..MM.yyyy"));

        String dateString = dataList.get(4);
        Date dateMessage = Calendar.getInstance().getTime();
        boolean foundCorrectFormat = false;

        if (dateString == null || dateString.equals("")) {
            return false;
        }


        for (DateFormat dateFormat: dateFormatList) {
            try {
                dateMessage = dateFormat.parse(dateString);
                foundCorrectFormat = true;
                break;
            } catch (ParseException e) {
                continue;
            }
        }

        if (!foundCorrectFormat) {
            System.out.println("!".repeat(SheetConfig.repeatNum));
            System.out.println("Неправильный формат даты -> " + dateString);
            return false;
        }

        if (dateMessage.getTime() + 23 * 60 * 60 * 1000 < Calendar.getInstance().getTime().getTime()) {
            return false;
        }

        for (String pattern: PatternToFind.getPatternsList()) {
            for (String str: dataList.getList()) {
                if (str.contains(pattern)) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<ListRecord> filtredList(List<ListRecord> dataList) {
        List<ListRecord> result = new ArrayList<>();
        for (ListRecord tmp: dataList) {
            if (isPatternFound(tmp)) {
                result.add(tmp);
            }
        }
        return result;
    }

    public static List<ListRecord> parseOnFileEESK() {
        List<ListRecord> result;

        System.out.println("=".repeat(SheetConfig.repeatNum));
        System.out.println("Opening file " + SheetConfig.pathToFile + "\n");
        XSSFWorkbook workbook = null;

        try {
            workbook = new XSSFWorkbook(new File(SheetConfig.pathToFile));
        } catch (IOException e) {
            System.out.println("!".repeat(SheetConfig.repeatNum));
            System.out.println(ExitStatus.ERROR_OPEN_FILE + " " + SheetConfig.pathToFile);
            System.exit(ExitStatus.ERROR_OPEN_FILE.ordinal());
        } catch (InvalidFormatException e) {
            System.out.println("!".repeat(SheetConfig.repeatNum));
            System.out.println(ExitStatus.ERROR_FILE_FORMAT + " " + SheetConfig.pathToFile);
            System.exit(ExitStatus.ERROR_FILE_FORMAT.ordinal());
        }

        List<ListRecord> data = MessageList.convertXLSToList(workbook);

        System.out.println("+".repeat(SheetConfig.repeatNum));
        System.out.println("Ищется " + PatternToFind.getPatternsList());

        result = MessageList.filtredList(data);

        return result;
    }
}
