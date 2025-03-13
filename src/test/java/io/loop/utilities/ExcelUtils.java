package io.loop.utilities;


import io.cucumber.java.sl.Ce;
import io.loop.pages.ReceivedDocs;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.*;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private Sheet workSheet;
    private Workbook workBook;
    private String path;

    private static final Logger LOG = LogManager.getLogger();

    public ExcelUtils (String path, String sheetName){
        this.path = path;

        try{
            // open excel file
            FileInputStream ExcelFile = new FileInputStream(path);

            // access the required data sheet
            workBook = WorkbookFactory.create(ExcelFile);
            workSheet = workBook.getSheet(sheetName);
        } catch (Exception e) {
            LOG.error(e.getMessage() + ": happened while accessing excel");
            throw  new RuntimeException();
        }

    }

    public String getCellData(int rowNum, int colNum){
        Cell cell;
        try {
            cell = workSheet.getRow(rowNum).getCell(colNum);
            String cellData = cell.toString();
            //return cell.getStringCellValue();
            return cellData;
        } catch (Exception e) {
            LOG.error("happened while getting cell data: " + e.getMessage());
            throw  new RuntimeException();
        }
    }

    public void setCellData(String value, int rowNum, int colNum) {
        Cell cell;
        Row row;

        try {
            row = workSheet.getRow(rowNum);
            if (row == null) {
                row = workSheet.createRow(rowNum);
            }

            cell = row.getCell(colNum);
            if (cell == null) {
                cell = row.createCell(colNum);
            }

            cell.setCellValue(value);

            try (FileOutputStream fileOut = new FileOutputStream(path)) {
                workBook.write(fileOut);
            }

        } catch (Exception e) {
            LOG.error("Error while writing to Excel file", e);
            throw new RuntimeException("Error while writing to Excel file: " + e.getMessage(), e);
        }
    }

    public int columnCount(){
        return workSheet.getRow(0).getLastCellNum();
    }

    public int rowCount(){
//        return workSheet.getLastRowNum();

            return workSheet.getLastRowNum() + 1; // Ensure the total number of rows is counted

    }

    public String [][] getDataArray(){
        String [][] data = new String[rowCount()][columnCount()];

        for (int i = 0; i < rowCount(); i++) {
            for (int j = 0; j < columnCount(); j++) {
                String value = getCellData(i,j);
                data[i][j] = value;
            }
        }
        return data;
    }

    public List <String> getColumnNames(){
        List<String> columns = new ArrayList<>();

        for (Cell cell : workSheet.getRow(0)){
            columns.add(cell.toString());
        }
        return columns;
    }

    public List<Map<String, String>> getDataListWithMap(){
        // get all columns
        List<String> columns = getColumnNames();

        // this will be returned
        List<Map<String, String>> data = new ArrayList<>();
        for (int i = 1; i <rowCount() ; i++) {
            // get each row
            Row row = workSheet.getRow(i);
            // create map of the row using the column and value
            // column map key, cell value
            Map<String, String> rowMap = new HashMap<>();
            for(Cell cell : row){
                int columnIndex = cell.getColumnIndex();
//                rowMap.put(columns.get(columnIndex), cell.getCellStyle().toString());
                rowMap.put(columns.get(columnIndex), getCellData(i, columnIndex));
            }
        }
        return data;
    }

    public List<List<String>> getDataList() {
        List<List<String>> data = new ArrayList<>();

        for (int i = 0; i < rowCount(); i++) {
            Row rowObj = workSheet.getRow(i);
            if (rowObj == null) continue; // Skip empty rows

            List<String> row = new ArrayList<>();
            for (int j = 0; j < columnCount(); j++) {
                row.add(getCellData(i, j));
            }
            data.add(row);

        }
        return data;
    }

    public void setCellData(String value, String columnName, int row) {
        int column = getColumnNames().indexOf(columnName);

        if (column == -1) {
            LOG.error("Column name '" + columnName + "' not found in Excel sheet.");
            throw new IllegalArgumentException("Column name '" + columnName + "' not found in Excel sheet.");
        }

        setCellData(value, row, column);
    }

    public List<Map<String, String>> getDataListV2() {
        List<Map<String, String>> data = new ArrayList<>();
        List<String> columns = getColumnNames();

        for (int i = 1; i <= rowCount(); i++) {
            Row row = workSheet.getRow(i);
            if (row == null) continue; // Skip empty rows

            Map<String, String> rowMap = new HashMap<>();
            for (int j = 0; j < columnCount(); j++) {
                Cell cell = row.getCell(j);
                String cellValue = "";

                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            cellValue = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                cellValue = cell.getDateCellValue().toString();
                            } else {
                                cellValue = Double.toString(cell.getNumericCellValue());
                            }
                            break;
                        case BOOLEAN:
                            cellValue = Boolean.toString(cell.getBooleanCellValue());
                            break;
                        case FORMULA:
                            cellValue = cell.getCellFormula();
                            break;
                        case BLANK:
                            cellValue = "";
                            break;
                        default:
                            cellValue = cell.toString();
                            break;
                    }
                }

                rowMap.put(columns.get(j), cellValue);
            }
            data.add(rowMap);
        }
        return data;
    }
}

















