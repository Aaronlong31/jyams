package com.jyams.util;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

/**
 * POI的workbook辅助类
 * 
 * @author zhanglong
 * 
 */
public class WorkbookUtils {

    /**
     * 获取指定单元格的字符值
     * 
     * @param sheet
     * @param row
     * @param cell
     * @return
     */
    public static String getStringValue(Sheet sheet, int rowNum, int cellNum) {
        if (sheet == null)
            return null;
        Row row = sheet.getRow(rowNum);
        if (row == null)
            return null;
        Cell cell = row.getCell(cellNum);
        if (cell == null)
            return null;
        if (cell.getCellType() == Cell.CELL_TYPE_STRING)
            return cell.getStringCellValue();
        return null;
    }

    /**
     * 获取指定单元格的数值
     * 
     * @param sheet
     * @param row
     * @param cell
     * @return
     */
    public static Double getNumberValue(Sheet sheet, int rowNum, int cellNum) {
        if (sheet == null)
            return null;
        Row row = sheet.getRow(rowNum);
        if (row == null)
            return null;
        Cell cell = row.getCell(cellNum);
        if (cell == null)
            return null;
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC)
            return cell.getNumericCellValue();
        return null;
    }

    /**
     * 获取指定单元格的整数值
     * 
     * @param sheet
     * @param row
     * @param cell
     * @return
     */
    public static int getIntValue(Sheet sheet, int rowNum, int cellNum) {
        Double value = getNumberValue(sheet, rowNum, cellNum);
        return value == null ? 0 : value.intValue();
    }

    /**
     * 获取指定单元格的整数值
     * 
     * @param sheet
     * @param row
     * @param cell
     * @return
     */
    public static float getFloatValue(Sheet sheet, int rowNum, int cellNum) {
        Double value = getNumberValue(sheet, rowNum, cellNum);
        return value == null ? 0 : value.floatValue();
    }

    public static void main(String[] args) {
        Double d = 12.99;
        System.out.println(d.floatValue());
    }
}
