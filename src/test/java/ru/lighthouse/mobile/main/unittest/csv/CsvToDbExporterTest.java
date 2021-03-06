package ru.lighthouse.mobile.main.unittest.csv;

import org.junit.jupiter.api.Test;
import ru.lighthouse.mobile.main.core.csv.CsvToDbTableImporter;
import ru.lighthouse.mobile.main.core.csv.DbTableToCsvExporter;
import ru.lighthouse.mobile.main.unittest.AbstractUnitTest;
import ru.lighthouse.mobile.main.utils.TestResourcesUtils;

import java.net.URISyntaxException;

public class CsvToDbExporterTest extends AbstractUnitTest {

    @Test
    public void test_export_csv() {
        DbTableToCsvExporter exporter = DbTableToCsvExporter.createExporter("jdbc:postgresql://localhost:5432/lighthousedb",
                                                                            "postgres",
                                                                            "password");
        exporter.export("MOBILE_USER");
    }

    @Test
    public void test_import_csv() throws URISyntaxException {
        CsvToDbTableImporter importer = CsvToDbTableImporter.createImporter("jdbc:postgresql://localhost:5432/lighthousedb",
                                                                            "postgres",
                                                                            "password");
        importer.importCsv(TestResourcesUtils.convertResourcePath("csv/import_mobile_user.csv").toString(), "MOBILE_USER");
    }
}