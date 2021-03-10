package ru.lighthouse.mobile.main.unittest.excel;

import org.junit.jupiter.api.Test;
import ru.lighthouse.mobile.main.core.excel.DbToExcelExporter;
import ru.lighthouse.mobile.main.unittest.AbstractUnitTest;

public class ExportExcelUnitTest extends AbstractUnitTest {

    @Test
    public void test_export() {
        DbToExcelExporter exporter = DbToExcelExporter.createExporter("jdbc:postgresql://localhost:5432/lighthousedb",
                                                                      "postgres",
                                                                      "password");
        exporter.export("MOBILE_USER", true);
    }
}
