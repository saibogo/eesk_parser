
import configuration.SheetConfig;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import sheets_analize.EensJson;
import sheets_analize.MessageList;
import support_classes.ExitStatus;
import support_classes.ListRecord;
import support_classes.MapRecord;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InvalidFormatException {



        LoadFileOnEESK();
        List<ListRecord> filtredData = MessageList.parseOnFileEESK();
        printFiltredEESK(filtredData);

        LoadFileOnEENS();
        List<MapRecord> filtredResultList = EensJson.parseOnFileEENS();
        printFiltredEENS(filtredResultList);

        writeToResultFile(filtredData, filtredResultList);

        }

        public static void LoadFileOnEESK() {
            LoadFileOnURL(SheetConfig.urlToFile, SheetConfig.pathToFile);
        }

        public static void LoadFileOnEENS() {
            LoadFileOnURL(SheetConfig.urlToEensJson, SheetConfig.pathToJson);
        }

        public static void LoadFileOnURL(String fileURL, String pathToSave) {
            boolean fileNotLoaded = true;

            while (fileNotLoaded) {
                try {
                    System.out.println("=".repeat(SheetConfig.repeatNum));
                    System.out.println("Попытка скачать файл с сервера " + fileURL);
                    URL url = new URL(fileURL);
                    InputStream inputStream = url.openStream();
                    Files.copy(inputStream, new File(pathToSave).toPath());
                    fileNotLoaded = false;
                } catch (FileAlreadyExistsException e) {
                    System.out.println("=".repeat(SheetConfig.repeatNum));
                    System.out.println("Удаляем существующий файл");
                    (new File(pathToSave)).delete();
                } catch (IOException e) {
                    System.out.println(ExitStatus.NO_NETWORK);
                    System.exit(ExitStatus.NO_NETWORK.ordinal());
                }
            }
        }

        public static void printFiltredEESK(List<ListRecord> ls) {
            System.out.println("==".repeat(SheetConfig.repeatNum) + "\nВсего найдено совпадений " + ls.size());

            for  (ListRecord tmp: ls) {
                System.out.println("+".repeat(SheetConfig.repeatNum));
                System.out.println(tmp);
            }
        }

        public static void printFiltredEENS(List<MapRecord> ls) {
            System.out.println("+".repeat(SheetConfig.repeatNum));
            System.out.println("Всего найдено совпадений " + ls.size());
            for(MapRecord map: ls) {
                System.out.println(map);
            }
        }

        public static void writeToResultFile(List<ListRecord> listRecords, List<MapRecord> mapRecords) {
            XSSFWorkbook newWorkbook = new XSSFWorkbook();
            System.out.println("=".repeat(SheetConfig.repeatNum) + "\nПопытка записи данных в " + SheetConfig.pathToResultFile);
            XSSFSheet newSheet = newWorkbook.createSheet("Результат выборки");
            XSSFSheet secondSheet = newWorkbook.createSheet("Данные ЕЕНС");

            try {
                FileOutputStream fileOutputStream = new FileOutputStream(SheetConfig.pathToResultFile);

                Row row = newSheet.createRow(0);
                Cell cell;
                for (int j = 0; j < SheetConfig.columnsList.size(); j++) {
                    cell = row.createCell(j);
                    cell.setCellValue(SheetConfig.columnsList.get(j));
                }

                for (int i = 0; i < listRecords.size(); i++) {
                    row = newSheet.createRow(i + 1);
                    for (int j = 0; j < listRecords.get(0).size(); j++) {
                        cell = row.createCell(j );
                        cell.setCellValue(listRecords.get(i).get(j));
                    }
                }

                for (int i = 0; i < mapRecords.size(); i++) {
                    row = secondSheet.createRow(i);
                    MapRecord record = mapRecords.get(i);
                    int j = 0;
                    for (String str: record.values()) {
                        cell = row.createCell(j);
                        cell.setCellValue(str);
                        j++;
                    }
                }

                newWorkbook.write(fileOutputStream);

                System.out.println("=".repeat(SheetConfig.repeatNum));
                System.out.println("Сохранено в " + SheetConfig.pathToResultFile);

            } catch (Exception e) {
                System.out.println(ExitStatus.ERROR_SAVE_FILE);
                System.out.println(e);
                System.exit(ExitStatus.ERROR_SAVE_FILE.ordinal());
            }
        }
    }
