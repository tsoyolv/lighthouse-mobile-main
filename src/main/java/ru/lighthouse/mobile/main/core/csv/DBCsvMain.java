package ru.lighthouse.mobile.main.core.csv;

import org.apache.commons.lang3.NotImplementedException;

public class DBCsvMain {
    public static void main(String[] args) {
        CsvToDbTableImporter importer = createImporter(args);
        importer.importCsv("crm_authority.csv", "crm_authority");
        importer.importCsv("crm_user.csv", "crm_user");
        importer.importCsv("crm_authority_to_user.csv", "crm_authority_to_user");
        importer.importCsv("one_time_scheduler.csv", "one_time_scheduler");
        importer.importCsv("evaluation.csv", "evaluation");
        importer.importCsv("administrative_district.csv", "administrative_district");

        importer.importCsv("district.csv", "district");
        importer.importCsv("building_type.csv", "building_type");
        importer.importCsv("building.csv", "building");
        importer.importCsv("building_geoinfo.csv", "building_geoinfo");
        importer.importCsv("building_details.csv", "building_details");

        importer.importCsv("flat.csv", "flat");
        importer.importCsv("flat_details.csv", "flat_details");
        importer.importCsv("recommendation_flat.csv", "recommendation_flat");
        importer.importCsv("flat_price_history.csv", "flat_price_history");
        importer.importCsv("flat_image.csv", "flat_image");
        importer.importCsv("crm_purchase_order.csv", "crm_purchase_order");

        importer.importCsv("district_stats.csv", "district_stats");
        importer.importCsv("building_type_stats.csv", "building_type_stats");
        importer.importCsv("building_stats.csv", "building_stats");
        importer.importCsv("flat_stats.csv", "flat_stats");
        importer.importCsv("building_type_flat_stats.csv", "building_type_flat_stats");

        importer.importCsv("district_building_type_stats.csv", "district_building_type_stats");
        importer.importCsv("district_building_type_flat_stats.csv", "district_building_type_flat_stats");
        importer.importCsv("district_building_type_flat_stats_history.csv", "district_building_type_flat_stats_history");
        importer.importCsv("district_building_type_stats_history.csv", "district_building_type_stats_history");
        importer.importCsv("district_stats_history.csv", "district_stats_history");

        importer.importCsv("crm_purchase_order_history.csv", "crm_purchase_order_history");
        importer.importCsv("building_stats_history.csv", "building_stats_history");
        importer.importCsv("flat_stats_history.csv", "flat_stats_history");
        importer.importCsv("building_flat_stats.csv", "building_flat_stats");
        importer.importCsv("building_type_stats_history.csv", "building_type_stats_history");

        importer.importCsv("building_type_flat_stats_history.csv", "building_type_flat_stats_history");
        importer.importCsv("building_flat_stats_history.csv", "building_flat_stats_history");
    }

    private static CsvToDbTableImporter createImporter(String[] args) {
        if (args == null || args.length == 0) {
            return CsvToDbTableImporter.createImporter("jdbc:postgresql://localhost:5432/lighthousedb",
                                                       "postgres",
                                                       "password");
        }
        throw new NotImplementedException("Not implemented yet");
    }
}
