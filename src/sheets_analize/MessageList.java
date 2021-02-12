package sheets_analize;

import configuration.PatternToFind;
import configuration.SheetConfig;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.util.ArrayList;
import java.util.List;

public class MessageList {
    public static List<List<String>> convertXLSToList(XSSFWorkbook workbook) {
        List<List<String>> result = new ArrayList<>();

        XSSFSheet sheet = workbook.getSheetAt(SheetConfig.SheetIndex);
        System.out.println("Opening sheet:" + sheet.getSheetName());
        int maxRow = sheet.getPhysicalNumberOfRows();
        System.out.println("Число записей на странице = " + maxRow);

        XSSFRow row;
        XSSFCell cell;
        List<String> rowList;

        for (int i = 0; i <= maxRow; i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            rowList = new ArrayList<>();

            for (int j = SheetConfig.startCellIndex; j <= SheetConfig.stopCellIndex; j++) {
                cell = row.getCell(j);
                rowList.add(SheetsData.getCellData(cell));
            }

            result.add(rowList);
        }
            return result;
    }

    public static boolean isPatternFound(List<String> dataList) {
        for (String pattern: PatternToFind.getPatternsList()) {
            for (String str: dataList) {
                if (str.indexOf(pattern) >= 0) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<List<String>> filtredList(List<List<String>> dataList) {
        List<List<String>> result = new ArrayList<>();
        for (List<String> tmp: dataList) {
            if (isPatternFound(tmp)) {
                result.add(tmp);
            }
        }
        return result;
    }
}
