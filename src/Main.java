
import configuration.SheetConfig;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sheets_analize.SheetsData;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFormatException {

        System.out.println("Opening file " + SheetConfig.pathToFile);
        XSSFWorkbook workbook = new XSSFWorkbook(new File(SheetConfig.pathToFile));
        XSSFSheet sheet = workbook.getSheetAt(SheetConfig.SheetIndex);
        System.out.println("Opening sheet:" + sheet.getSheetName());
        int maxRow = sheet.getPhysicalNumberOfRows();
        System.out.println("Число записей на странице = " + maxRow);

        XSSFRow row;
        XSSFCell cell;
        StringBuilder builder;

        for (int i = 0; i <= maxRow; i++) {
            row = sheet.getRow(i);
            if (row == null) {
                continue;
            }
            builder = new StringBuilder();

            for (int j = SheetConfig.startCellIndex; j <= SheetConfig.stopCellIndex; j++) {
                cell = row.getCell(j);
                builder.append(SheetsData.getCellData(cell));
                builder.append(" || ");
            }
            System.out.println(builder.toString());
        }
    }
}
