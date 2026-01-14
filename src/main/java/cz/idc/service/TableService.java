package cz.idc.service;

import cz.idc.model.Table;
import cz.idc.model.VendorStats;
import cz.idc.model.enums.SortParameter;

import java.util.Optional;

public interface TableService {

    /**
     * @param table  source table
     * @param vendor vendor name
     * @param year vendor name
     * @param quarter vendor name
     * @return optional result containing units and share
     */
    Optional<VendorStats> getVendorStats(Table table, String vendor, int year, String quarter);

    /**
     * @param table  source table
     * @param vendor vendor name
     * @return row number
     */
    int findRowIndex(Table table, String vendor);

    /**
     * @param table source table
     * @return new Table with sorted rows by vendor
     */
    Table sortByVendor(Table table);

    /**
     * @param table source table
     * @return new Table with sorted rows by units
     */
    Table sortByUnits(Table table);

    /**
     * @param table source table
     * @param sortParameter sorting parameter
     * @return new Table with sorted rows by units
     */
    Table sortBy(Table table, SortParameter sortParameter);

    /**
     * @param table data source
     * @param country
     * @param year
     * @param quarter
     * @return Table initialization with specific parameters
     */
    Table filterdata(Table table, String country, int year, String quarter);
}
