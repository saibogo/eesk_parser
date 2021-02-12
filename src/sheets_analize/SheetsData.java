package sheets_analize;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFCell;

public class SheetsData {
    public static String getCellData(XSSFCell cell) {
        String result = "";
        if (cell == null) {
            result = "";
        } else if (cell.getCellType() == CellType.NUMERIC && DateUtil.isCellDateFormatted(cell)) {
            result = cell.getDateCellValue().toString();
        } else if (cell.getCellType() == CellType.NUMERIC) {
            result = cell.getNumericCellValue() + "";
        } else if (cell.getCellType() == CellType.STRING) {
            return cell.getStringCellValue();
        } else {
            return "";
        }

        return result;
    }
}
