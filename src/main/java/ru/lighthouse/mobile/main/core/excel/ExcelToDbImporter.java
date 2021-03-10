package ru.lighthouse.mobile.main.core.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ExcelToDbImporter {
    private ExcelToDbImporter(String jdbcUrl, String username, String password) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
    }

    private final String jdbcUrl;
    private final String username;
    private final String password;

    public static ExcelToDbImporter createImporter(String jdbcUrl, String username, String password) {
        return new ExcelToDbImporter(jdbcUrl, username, password);
    }

    public int importExcelToDb(String filePath, String targetTableName) {
        FileInputStream file = null;
        try {
            file = new FileInputStream(new File(filePath));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Workbook workbook = null;
        try {
            workbook = new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet firstSheet = workbook.getSheetAt(0);
        String sqlInsertStatement = createSqlInsertStatement(firstSheet, targetTableName);

        System.out.println(sqlInsertStatement);
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {

            Iterator<Row> rowIterator = firstSheet.rowIterator();
            int rowNumber = 0;
            while (rowIterator.hasNext()) {
                if (rowNumber++ == 0) {
                    continue;
                }
                Row row = rowIterator.next();

            }
            //PreparedStatement preparedStmt = connection.prepareStatement(sqlInsertStatement);
            //preparedStmt.setObject();

            // execute the preparedstatement
            //preparedStmt.execute();

        } catch (SQLException e) {
            System.out.println("Datababse error:");
            e.printStackTrace();
        }
        return -1;
    }

    private String createSqlInsertStatement(Sheet firstSheet, String targetTableName) {
        List<String> fieldsNames = getFieldsNames(firstSheet);
        return "INSERT INTO " + targetTableName + " (" + String.join(",", fieldsNames) + ") VALUES (" +
                fieldsNames.stream()
                           .map(f -> "?").collect(Collectors.joining(",")) +
                ")";
    }

    private List<String> getFieldsNames(Sheet firstSheet) {
        Row firstRow = firstSheet.getRow(0);
        List<String> fieldsNames = new ArrayList<>();
        firstRow.forEach(c -> fieldsNames.add(c.getStringCellValue()));
        return fieldsNames;
    }
}
