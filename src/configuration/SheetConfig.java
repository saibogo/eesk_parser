package configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SheetConfig {
    public static final String pathToFile = "/home/saibogo/IdeaProjects/eesk_data_parser/info_shutdown.xlsm";
    public static final String urlToFile = "https://eesk.ru/upload/site1/info_shutdown.xlsm";
    public static final String pathToResultFile = "/home/saibogo/IdeaProjects/eesk_data_parser/filtred_info.xlsx";

    public static final int SheetIndex = 0;
    public static final int startCellIndex = 0;
    public static final int stopCellIndex = 9;
    public static final String column1 = "Регион";
    public static final String column2 = "Район";
    public static final String column3 = "Населенный пункт";
    public static final String column4 = "Улица";
    public static final String column5 = "Дата отключения";
    public static final String column6 = "Время отключения";
    public static final String column7 = "Дата включения";
    public static final String column8 = "Время включения";
    public static final String column9 = "Филиал";
    public static final String column10 = "РЭС";

    public static final List<String> columnsList = new ArrayList<>(Arrays.asList(column1, column2, column3,
            column4, column5, column6, column7, column8, column9, column10));

    public static final int repeatNum = 20;

}
