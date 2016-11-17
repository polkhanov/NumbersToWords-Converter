package ru.converter;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

class Dictionary {

    Dictionary() {

        fillMap(EXPONENT_MAP, "exponentMap.xls", 1);
        fillMap(decimalNumbersMap, "decimalNumbers.xls", 2);
        fillMap(hundredthNumbersMap, "hundredNumbers.xls", 1);
        fillMap(simpleNumbersMap, "simpleNumbers.xls", 1);


    }

    enum numbersMap {
        FIRST, SECOND, THIRD
    }

    static final String ZERO = "ноль";
    static final String MINUS = "минус";

    static private final String[] thousandOperator = new String[]{"", "одна", "две"};

    static final Map<Integer, String> EXPONENT_MAP = new HashMap<Integer, String>() {{
        put(0, "");
         /*    put(1,   "тысяча");
           put(2,   "миллион");
           put(3,   "миллиард");
           put(4,   "биллион");
           put(5,   "биллиард");
           put(6,   "триллион");
           put(7,   "триллиард");
           put(8,   "квадриллион");
           put(9,   "квадриллиард");
           put(10,   "квинтиллион");
           put(11,   "квинтиллиард");
           put(12,   "секстиллион");
           put(13,   "секстиллиард");
           put(14,   "септиллион");
           put(15,   "септиллиард");
           put(16,   "октиллион");
           put(17,   "октиллиард");
           put(18,   "нониллион");
           put(19,   "нониллиард");
           put(20,   "дециллион");
           put(21,   "дециллиард");*/
    }};

    private static final Map<Integer, String> simpleNumbersMap = new HashMap<Integer, String>() {{
        put(0, "");
      /*  put(1,    "один");
        put(2,    "два");
        put(3,    "три");
        put(4,    "четыре");
        put(5,    "пять");
        put(6,    "шесть");
        put(7,    "семь");
        put(8,    "восемь");
        put(9,    "девять");
        put(10,   "десять");
        put(11,   "одиннадцать");
        put(12,   "двенадцать");
        put(13,   "тринадцать");
        put(14,   "четырнадцать");
        put(15,   "пятнадцать");
        put(16,   "шестнадцать");
        put(17,   "семнадцать");
        put(18,   "восемнадцать");
        put(19,   "девятнадцать");*/
    }};
    private static final Map<Integer, String> decimalNumbersMap = new HashMap<Integer, String>() {{
      /*  put(2,  "двадцать");
        put(3,  "тридцать");
        put(4,   "сорок");
        put(5,   "пятьдесят");
        put(6,   "шестьдесят");
        put(7,   "семьдесят");
        put(8,   "восемьдесят");
        put(9,   "девяносто");*/

    }};
    private static final Map<Integer, String> hundredthNumbersMap = new HashMap<Integer, String>() {{
        put(0, "");
      /* put(1,   "сто");
       put(2,   "двести");
       put(3,   "триста");
       put(4,   "четыреста");
       put(5,   "пятьсот");
       put(6,   "шестьсот");
       put(7,   "семьсот");
       put(8,   "восемьсот");
       put(9,   "девятьсот");*/

    }};

    static String getExponent(int key) {
        return EXPONENT_MAP.get(key);
    }

    static String getSimpleNumber(int key) {
        return simpleNumbersMap.get(key);
    }

    static String getDecimalNumber(int key) {
        return decimalNumbersMap.get(key);
    }

    static String getHundredthNumber(int key) {
        return hundredthNumbersMap.get(key);
    }

    static String getThousandOperator(int key) {
        return thousandOperator[key];
    }

    private void fillMap(Map<Integer, String> fillMap, String pathFile, int fillIndex) {
        try {
            ClassLoader classLoader = this.getClass().getClassLoader();
            FileInputStream file = new FileInputStream(new File(classLoader.getResource(pathFile).getFile()));
            HSSFWorkbook workbook = new HSSFWorkbook(file);
            String testText = "";
            for (Row row : workbook.getSheetAt(0)) {
                for (Cell cell : row) {
                    testText = cell.getRichStringCellValue().getString();
                }
                fillMap.put(fillIndex++, testText);
            }
        } catch (NullPointerException | IOException e) {
            System.out.println("Проблемы с открытием файла: " + pathFile + ". Возможно файл поврежден или не существует!");

        }
    }
}
