package cz.idc.service.impl;

import cz.idc.model.Row;
import cz.idc.model.Table;
import cz.idc.model.VendorStats;
import cz.idc.model.enums.SortParameter;
import cz.idc.service.TableService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class TableServiceImpl implements TableService {

    @Override
    public Optional<VendorStats> getVendorStats(Table table, String vendor, int year, String quarter) {
        return table.getRows().stream()
                .filter(r -> r.getVendor().equalsIgnoreCase(vendor))
                .findFirst()
                .map(r -> new VendorStats(
                        r.getUnits(),
                        table.getShareForRow(r)
                ));
    }

    @Override
    public int findRowIndex(Table table, String vendor) {
        List<Row> rows = table.getRows();
        for (int i = 0; i < rows.size(); i++) {
            if (rows.get(i).getVendor().equalsIgnoreCase(vendor)) {
                return i + 1;
            }
        }
        return -1;
    }

    @Override
    public Table sortByVendor(Table table) {
        List<Row> sorted = new ArrayList<>(table.getRows());
        sorted.sort(Comparator.comparing(Row::getVendor, String.CASE_INSENSITIVE_ORDER));
        return new Table(sorted);
    }

    @Override
    public Table sortByUnits(Table table) {
        List<Row> sorted = new ArrayList<>(table.getRows());
        sorted.sort(Comparator.comparing(Row::getUnits).reversed());
        return new Table(sorted);
    }

    @Override
    public Table sortBy(Table table, SortParameter sortParameter) {
        List<Row> sorted = new ArrayList<>(table.getRows());
        if (sortParameter == null) {
            return table;
        }
        switch (sortParameter) {
            case UNITS -> sorted.sort(Comparator.comparing(Row::getUnits).reversed());
            case VENDOR -> sorted.sort(Comparator.comparing(Row::getVendor).reversed());
        }
        return new Table(sorted);
    }

    @Override
    public Table filterdata(Table table, String country, int year, String quarter) {
        List<Row> filteredRows = table.getRows().stream()
                .filter(r -> r.getCountry().equalsIgnoreCase(country))
                .filter(r -> r.getYear() == year)
                .filter(r -> r.getQuarter().equalsIgnoreCase(quarter))
                .toList();

        return new Table(filteredRows);
    }
}
