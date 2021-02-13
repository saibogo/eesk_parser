
import configuration.PatternToFind;
import configuration.SheetConfig;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import sheets_analize.MessageList;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFormatException {

        boolean fileNotLoaded = true;
        while (fileNotLoaded) {
            try {
                System.out.println("=".repeat(SheetConfig.repeatNum));
                System.out.println("Попытка скачать файл с сервера eesk.ru");
                URL url = new URL(SheetConfig.urlToFile);
                InputStream inputStream = url.openStream();
                Files.copy(inputStream, new File(SheetConfig.pathToFile).toPath());
                fileNotLoaded = false;
            } catch (FileAlreadyExistsException e) {
                System.out.println("=".repeat(SheetConfig.repeatNum));
                System.out.println("Удаляем существующий файл");
                (new File(SheetConfig.pathToFile)).delete();
            } catch (UnknownHostException e) {
                System.out.println("Сайт недоступен или отсутствует сетевое соединение");
                System.exit(0);
            }
        }

        System.out.println("=".repeat(SheetConfig.repeatNum));
        System.out.println("Opening file " + SheetConfig.pathToFile + "\n");
        XSSFWorkbook workbook = new XSSFWorkbook(new File(SheetConfig.pathToFile));
        List<List<String>> data = MessageList.convertXLSToList(workbook);

        System.out.println("+".repeat(SheetConfig.repeatNum));
        System.out.println("Ищется " + PatternToFind.getPatternsList());

        List<List<String>> filtredData = MessageList.filtredList(data);


        System.out.println("==".repeat(SheetConfig.repeatNum) + "\nВсего найдено совпадений " + filtredData.size());

        for  (List<String> tmp: filtredData) {
            System.out.println("+".repeat(SheetConfig.repeatNum));
            System.out.println(tmp);
            }

        System.out.println("=".repeat(SheetConfig.repeatNum) + "\nПопытка записи данных в " + SheetConfig.pathToResultFile);
        XSSFWorkbook newWorkbook = new XSSFWorkbook();
        XSSFSheet newSheet = newWorkbook.createSheet("Результат выборки");

        try {
            FileOutputStream fileOutputStream = new FileOutputStream(SheetConfig.pathToResultFile);

            Row row = newSheet.createRow(0);
            Cell cell;
            for (int j = 0; j < SheetConfig.columnsList.size(); j++) {
                cell = row.createCell(j);
                cell.setCellValue(SheetConfig.columnsList.get(j));
            }

            for (int i = 0; i < filtredData.size(); i++) {
                row = newSheet.createRow(i + 1);
                for (int j = 0; j < filtredData.get(0).size(); j++) {
                    cell = row.createCell(j );
                    cell.setCellValue(filtredData.get(i).get(j));
                }
            }
            newWorkbook.write(fileOutputStream);
            System.out.println("=".repeat(SheetConfig.repeatNum));
            System.out.println("Сохранено в " + SheetConfig.pathToResultFile);

        } catch (Exception e) {
            System.out.println("Ошибка!");
        }

        }
    }
