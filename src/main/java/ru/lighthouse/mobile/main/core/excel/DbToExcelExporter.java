package ru.lighthouse.mobile.main.core.excel;


import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import java.io.FileOutputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class DbToExcelExporter {
    private static final int BATCH_SIZE = 500;

    private DbToExcelExporter(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    private final String jdbcUrl;
    private final String username;
    private final String password;

    public static DbToExcelExporter createExporter(String jdbcUrl, String username, String password) {
        return new DbToExcelExporter(jdbcUrl, username, password);
    }

    public void export(String table, boolean exportId) {
        System.out.println("------------------------------------------------------------------------------------------");
        System.out.println("Export table: " + table);
        long startTime = System.nanoTime();
        String filePath = getFileName(table.concat("_Export"));
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String sql = "SELECT * FROM " + table;
            Statement statement = connection.createStatement();
            statement.setFetchSize(BATCH_SIZE);
            ResultSet result = statement.executeQuery(sql);

            SXSSFWorkbook workbook = new SXSSFWorkbook(BATCH_SIZE);
            workbook.setCompressTempFiles(true);
            Sheet sheet = workbook.createSheet(table);
            writeHeaderLine(result, sheet, exportId);

            ResultSetMetaData metaData = result.getMetaData();
            int columnsAmount = metaData.getColumnCount();

            int start = exportId ? 1 : 2;
            int rowNumber = 1;
            while (result.next()) {
                Row row = sheet.createRow(rowNumber++);
                for (int i = start; i <= columnsAmount; i++) {
                    Object valueObject = result.getObject(i);
                    Cell cell = row.createCell(i - start);
                    setCellValue(workbook, valueObject, cell);
                }
                if (rowNumber % BATCH_SIZE == 0) {
                    System.out.println("Batch saved. Batch number: " + rowNumber / BATCH_SIZE + ". Saved rows: " + rowNumber);
                }
            }

            FileOutputStream outputStream = new FileOutputStream(filePath);
            workbook.write(outputStream);
            System.out.println("Table saved. Saved rows: " + rowNumber);

            statement.close();
            long finish = System.nanoTime();
            System.out.println("Export time: " + (finish - startTime) / 1_000_000_000 + " seconds");
            System.out.println("------------------------------------------------------------------------------------------");
        } catch (SQLException e) {
            System.out.println("Database error:");
        } catch (IOException e) {
            System.out.println("File IO error:");
        }
    }

    private String getFileName(String baseName) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String dateTimeInfo = dateFormat.format(new Date());
        return baseName.concat(String.format("_%s.xlsx", dateTimeInfo));
    }


    private void writeHeaderLine(ResultSet result, Sheet sheet, boolean exportId) throws SQLException {
        ResultSetMetaData metaData = result.getMetaData();
        int numberOfColumns = metaData.getColumnCount();

        Row headerRow = sheet.createRow(0);

        int start = exportId ? 1 : 2;

        for (int i = start; i <= numberOfColumns; i++) {
            String columnName = metaData.getColumnName(i);
            Cell headerCell = headerRow.createCell(i - start);
            headerCell.setCellValue(columnName);
        }
    }

    private void setCellValue(SXSSFWorkbook workbook, Object valueObject, Cell cell) {
        if (valueObject instanceof Boolean)
            cell.setCellValue((Boolean) valueObject);
        else if (valueObject instanceof Double)
            cell.setCellValue((double) valueObject);
        else if (valueObject instanceof Float)
            cell.setCellValue((float) valueObject);
        else if (valueObject instanceof Date) {
            cell.setCellValue((Date) valueObject);
            formatDateCell(workbook, cell);
        } else if (valueObject instanceof Long) {
            cell.setCellValue((long) valueObject);
        } else if (valueObject instanceof Integer) {
            cell.setCellValue((int) valueObject);
        } else if (valueObject instanceof BigDecimal) {
            cell.setCellValue(((BigDecimal) valueObject).toPlainString());
        } else cell.setCellValue((String) valueObject);
    }

    private void formatDateCell(SXSSFWorkbook workbook, Cell cell) {
        CellStyle cellStyle = workbook.createCellStyle();
        CreationHelper creationHelper = workbook.getCreationHelper();
        cellStyle.setDataFormat(creationHelper.createDataFormat().getFormat("yyyy-MM-dd HH:mm:ss"));
        cell.setCellStyle(cellStyle);
    }
}