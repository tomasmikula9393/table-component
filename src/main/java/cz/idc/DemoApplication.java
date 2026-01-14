package cz.idc;

import cz.idc.exporter.HtmlExporter;
import cz.idc.exporter.TableExporter;
import cz.idc.loader.CsvLoader;
import cz.idc.model.Table;
import cz.idc.model.VendorStats;
import cz.idc.model.enums.SortParameter;
import cz.idc.service.TableService;
import cz.idc.service.impl.TableServiceImpl;

import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Optional;
import java.util.logging.Logger;

public class DemoApplication {

    private static final Logger LOGGER = Logger.getLogger(DemoApplication.class.getName());
    private static final String VENDOR_NAME = "Dell";
    private static final int YEAR = 2010;
    private static final String QUARTER = "Q3";
    private static final String COUNTRY = "Czech Republic";

    public static void main(String[] args) throws Exception {
        Path csvPath = Path.of("src/main/resources/csv/data.csv");
        Path output = Path.of("output.html");
        Path outputSortedByUnits = Path.of("output-sorted-by-units.html");
        Path outputSortedByVendors = Path.of("output-sorted-by-vendors.html");
        Path outputCustomSort = Path.of("output-sorted-by-parameter.html");

        CsvLoader loader = new CsvLoader();
        Table table = loader.loadCsvData(csvPath);
        TableExporter exporter = new HtmlExporter();
        TableService tableService = new TableServiceImpl();
        Table filteredTable = tableService.filterdata(table, COUNTRY, YEAR, QUARTER);

        try (OutputStream out = Files.newOutputStream(output)) {
            exporter.export(filteredTable, out);
        }

        Optional<VendorStats> vendorStats = tableService.getVendorStats(table, VENDOR_NAME, YEAR, QUARTER);
        if (vendorStats.isPresent()) {
            LOGGER.info(String.format("Vendor %s sold %s units, in quarter %s of the year %s and it's %s share",
                    VENDOR_NAME, vendorStats.get().getFormattedUnits(), QUARTER, YEAR, vendorStats.get().getFormattedShare()));
        } else {
            LOGGER.info("No Vendor stats found");
        }

        //custom sort
        Table sortedBy = tableService.sortBy(filteredTable, SortParameter.VENDOR);
        try (OutputStream outCustomSort = Files.newOutputStream(outputCustomSort)) {
            exporter.export(sortedBy, outCustomSort);
        }

        //sort by vendor
        Table sortedTableByVendors = tableService.sortByVendor(filteredTable);
        try (OutputStream outSortedByVendors = Files.newOutputStream(outputSortedByVendors)) {
            exporter.export(sortedTableByVendors, outSortedByVendors);
        }

        //sort by units
        Table sortedTableByUnits = tableService.sortByUnits(filteredTable);
        try (OutputStream outSortedByUnits = Files.newOutputStream(outputSortedByUnits)) {
            exporter.export(sortedTableByUnits, outSortedByUnits);
        }

        int index = tableService.findRowIndex(filteredTable, VENDOR_NAME);
        if (index == -1) {
            LOGGER.info(String.format("Vendor %s was not found", VENDOR_NAME));
        } else {
            LOGGER.info(String.format("Vendor %s was found on the line %d", VENDOR_NAME, index));
        }
    }
}
