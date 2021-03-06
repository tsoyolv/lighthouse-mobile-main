package ru.lighthouse.mobile.main.core.csv;


import org.apache.commons.lang3.StringEscapeUtils;
import ru.lighthouse.mobile.main.core.ExceptionUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class DbTableToCsvExporter {
    private static final String NEW_LINE = System.getProperty("line.separator");
    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
    private static final String DEFAULT_SEPARATOR = ";";
    private static final int DEFAULT_STATEMENT_FETCH_SIZE = 1000;

    private DbTableToCsvExporter(String jdbcUrl, String username, String password, String separator, int statementFetchSize) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.separator = separator;
        this.statementFetchSize = statementFetchSize;
    }

    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final String separator;
    private final int statementFetchSize;

    public static DbTableToCsvExporter createExporter(String jdbcUrl, String username, String password) {
        return new DbTableToCsvExporter(jdbcUrl, username, password, DEFAULT_SEPARATOR, DEFAULT_STATEMENT_FETCH_SIZE);
    }

    public static DbTableToCsvExporter createExporter(String jdbcUrl, String username, String password, String separator, int statementFetchSize) {
        return new DbTableToCsvExporter(jdbcUrl, username, password, separator, statementFetchSize);
    }

    public void export(String... tables) {
        export(true, tables);
    }

    public void export(boolean exportId, String... tables) {
        export(null, exportId, tables);
    }

    public void export(String folder, boolean exportId, String... tables) {
        for (String table : tables) {
            export(table, folder, exportId);
        }
    }

    public void export(String tableName) {
        export(tableName, true);
    }

    public void export(String tableName, boolean exportId) {
        export(tableName, null, exportId);
    }

    public void export(String tableName, String folder, boolean exportId) {
        logLine("------------------------------------------------------------------------------------------");
        logLine("Export table: " + tableName);
        long startTime = System.nanoTime();

        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            Statement statement = connection.createStatement();
            statement.setFetchSize(statementFetchSize);
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            MetaData metaData = new MetaData(resultSet.getMetaData(), exportId);


            int progressbarPercent = getProgressbarPercent(connection, tableName);
            int rowNumber = 0;
            String filePath = getFilePath(tableName, folder);
            try (FileWriter fileWriter = new FileWriter(filePath); BufferedWriter writer = new BufferedWriter(fileWriter)) {
                writer.write(createHeader(metaData) + NEW_LINE);
                while (resultSet.next()) {
                    writer.write(createRow(resultSet, metaData) + NEW_LINE);
                    showProgressBar(rowNumber, progressbarPercent);
                    rowNumber++;
                }
                logCharacter("> 100% SUCCESS");
                logLine("");
                logLine("CSV saved. Saved rows: " + rowNumber);
            } catch (IOException e) {
                logLine("IO exception" + ExceptionUtils.getCauseLocalizedMessage(e));
            }
            statement.close();

            long finish = System.nanoTime();
            logLine("Export time: " + (finish - startTime) / 1_000_000_000 + " seconds");
            long fileSize = new File(filePath).length();
            logLine("Export file size: " + fileSize + " bytes or " + fileSize / 1024 + " KyloBytes or " + fileSize / 1_048_576 + " MegaBytes");
        } catch (SQLException e) {
            logLine("Database error: " + ExceptionUtils.getCauseLocalizedMessage(e));
        }
    }

    private void showProgressBar(int rowNumber, int progressbarPercent) {
        if (progressbarPercent != 0) {
            if (rowNumber % progressbarPercent == 0) {
                logCharacter("=");
            }
        }
    }

    private void logCharacter(String word) {
        System.out.print(word);
    }

    private int getProgressbarPercent(Connection connection, String tableName) throws SQLException {
        final ResultSet resultSet = connection.createStatement().executeQuery("SELECT COUNT(1) as total FROM " + tableName);
        resultSet.next();
        int total = resultSet.getInt("total");
        if (total > 100) {
            return total / 100;
        } else {
            return 1;
        }
    }

    private String getFilePath(String tableName, String folder) {
        String folderPath = folder == null ? "" : folder + File.separator;
        return folderPath + tableName.toUpperCase() + "_EXPORT" + String.format("_%s.csv", DATE_FORMAT.format(new Date()));
    }

    private String createHeader(MetaData metaData) {
        return String.join(separator, metaData.getColumnNames());
    }

    private String createRow(ResultSet resultSet, MetaData metaData) {
        return metaData.getColumnNames()
                       .stream()
                       .map(cn -> getColumnValue(resultSet, cn))
                       .collect(Collectors.joining(separator));
    }

    private String getColumnValue(ResultSet resultSet, String cn) {
        try {
            String str = Objects.toString(resultSet.getObject(cn), "");
            str = str.replace(separator, " ");
            return StringEscapeUtils.escapeCsv(str);
        } catch (SQLException sqlException) {
            throw new RuntimeException(sqlException);
        }
    }

    private void logLine(String line) {
        System.out.println(line);
    }

    private static class MetaData {
        private final List<String> columnNames;

        private MetaData(ResultSetMetaData metaData, boolean exportId) throws SQLException {
            int firstColumn = exportId ? 1 : 2;
            int lastColumn = metaData.getColumnCount();
            this.columnNames = new ArrayList<>();
            for (int i = firstColumn; i < lastColumn; i++) {
                String columnName = metaData.getColumnName(i);
                columnNames.add(columnName);
            }
            columnNames.add(metaData.getColumnName(lastColumn));
        }

        public List<String> getColumnNames() {
            return columnNames;
        }
    }
}