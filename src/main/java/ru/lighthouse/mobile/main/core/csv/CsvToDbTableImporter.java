package ru.lighthouse.mobile.main.core.csv;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import ru.lighthouse.mobile.main.core.ExceptionUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CsvToDbTableImporter {
    private static final List<DateFormat> DATE_FORMATS = List.of(new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss"),
                                                                 new SimpleDateFormat("yyyy-MM-dd"));
    private static final String DEFAULT_SEPARATOR = ";";

    private CsvToDbTableImporter(String jdbcUrl, String username, String password, String separator) {
        this.jdbcUrl = jdbcUrl;
        this.username = username;
        this.password = password;
        this.separator = separator;
    }

    private final String jdbcUrl;
    private final String username;
    private final String password;
    private final String separator;

    public static CsvToDbTableImporter createImporter(String jdbcUrl, String username, String password) {
        return new CsvToDbTableImporter(jdbcUrl, username, password, DEFAULT_SEPARATOR);
    }

    public static CsvToDbTableImporter createImporter(String jdbcUrl, String username, String password, String separator) {
        return new CsvToDbTableImporter(jdbcUrl, username, password, separator);
    }

    public void importCsv(Map<String, String> filesToTables) {
        filesToTables.forEach(this::importCsv);
    }

    public void importCsv(String file, String table) {
        logLine("------------------------------------------------------------------------------------------");
        logLine("Import csv " + file + " table: " + table);
        long startTime = System.nanoTime();

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Map<Integer, ColumnType> indexToColumn = readColumns(br.readLine());
            fillColumnsSqlTypes(table, indexToColumn);
            String sqlStatement = createSqlInsertOrMerge(table, indexToColumn);
            logLine(sqlStatement);
            try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
                connection.setAutoCommit(false);

                int rowsAmount = 0;
                String line;
                StringBuilder row = new StringBuilder();
                int columnsAmount = indexToColumn.values().size();
                int separatorsAmount = 0;
                while ((line = br.readLine()) != null) {
                    separatorsAmount += getSeparatorsAmount(line);
                    row.append(line);
                    if (columnsAmount == separatorsAmount + 1) {
                        String[] values = row.toString().split(separator, -1);
                        insertOrMergeRow(connection, sqlStatement, values, indexToColumn);
                        rowsAmount++;
                        row = new StringBuilder();
                        separatorsAmount = 0;
                    }
                }

                logLine("Table saved. Saved rows: " + rowsAmount);
                connection.commit();
            } catch (SQLException sqlException) {
                logLine("SqlException while inserting: " + ExceptionUtils.getCauseLocalizedMessage(sqlException));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long finish = System.nanoTime();
        logLine("Import CSV time: " + (finish - startTime) / 1_000_000_000 + " seconds");
    }

    private int getSeparatorsAmount(String line) {
        return StringUtils.countMatches(line, separator);
    }

    private String createSqlInsertOrMerge(String table, Map<Integer, ColumnType> columnsToIndex) {
        final Collection<ColumnType> columnTypes = columnsToIndex.values();
        String columns = columnTypes
                .stream()
                .map(ColumnType::getColumnName)
                .collect(Collectors.joining(","));
        String valuesQuestionMarks = columnTypes
                .stream()
                .map(e -> "?")
                .collect(Collectors.joining(","));
        String sql = "INSERT INTO " + table + " (" + columns + ")" + " VALUES (" + valuesQuestionMarks + ") ";
        boolean hasId = columnTypes.stream().anyMatch(c -> "id".equals(c.getColumnName()));
        if (hasId) {
            sql += "ON CONFLICT (ID) DO UPDATE SET " +
                    columnTypes
                            .stream()
                            .filter(c -> !"id".equals(c.getColumnName()))
                            .map(c -> c.getColumnName() + "=" + "EXCLUDED." + c.getColumnName())
                            .collect(Collectors.joining(","));
        }
        return sql;
    }

    private void insertOrMergeRow(Connection connection, String sql, String[] values, Map<Integer, ColumnType> columnsToIndex) throws SQLException {
        PreparedStatement preparedStmt = connection.prepareStatement(sql);
        for (int i = 1; i <= values.length; i++) {
            ColumnType columnType = columnsToIndex.get(i);
            preparedStmt.setObject(i, convertValue(values[i - 1], columnType), columnType.getSqlType());
        }
        preparedStmt.execute();
    }

    private Object convertValue(String value, ColumnType columnType) {
        value = value == null || value.isEmpty() ? null : StringEscapeUtils.unescapeCsv(value);
        if (value != null && Types.DATE == columnType.getSqlType()) {
            return convertDate(value);
        }
        return value;
    }

    private Date convertDate(String value) {
        for (DateFormat format : DATE_FORMATS) {
            try {
                return format.parse(value);
            } catch (ParseException e) {
                continue;
            }
        }
        throw new RuntimeException("Date parse exception:" + value);
    }

    private void fillColumnsSqlTypes(String table, Map<Integer, ColumnType> columnsToIndex) {
        try (Connection connection = DriverManager.getConnection(jdbcUrl, username, password)) {
            String columns = columnsToIndex.values().stream().map(ColumnType::getColumnName).collect(Collectors.joining(","));
            String sql = "SELECT " + columns + " FROM " + table + " WHERE 1 = 2";
            ResultSetMetaData metaData = connection.createStatement().executeQuery(sql).getMetaData();
            for (int i = 1; i <= columnsToIndex.size(); i++) {
                int sqlType = metaData.getColumnType(i);
                columnsToIndex.get(i).setSqlType(sqlType);
            }
        } catch (SQLException sqlException) {
            logLine("Sql exception while getting column types: " + ExceptionUtils.getCauseLocalizedMessage(sqlException));
        }
    }

    private Map<Integer, ColumnType> readColumns(String firstRow) throws IOException {
        if (firstRow == null) {
            throw new IOException("CSV Header is null or empty");
        }
        Map<Integer, ColumnType> indexToColumn = new HashMap<>();
        String[] columns = firstRow.split(separator);
        int index = 1;
        for (String column : columns) {
            indexToColumn.put(index++, new ColumnType(column.toLowerCase()));
        }
        return indexToColumn;
    }

    private void logLine(String line) {
        System.out.println(line);
    }

    public static class ColumnType {
        private final String columnName;
        private Integer sqlType;

        public ColumnType(String columnName) {
            this.columnName = columnName;
        }

        public void setSqlType(Integer sqlType) {
            this.sqlType = sqlType;
        }

        public Integer getSqlType() {
            return sqlType;
        }

        public String getColumnName() {
            return columnName;
        }
    }
}
