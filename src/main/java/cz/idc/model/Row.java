package cz.idc.model;

public class Row {

    private final String country;
    private final int year;
    private final String quarter;
    private final String vendor;
    private final double units;

    public Row(String country, int year, String quarter, String vendor, double units) {
        this.country = country;
        this.year = year;
        this.quarter = quarter;
        this.vendor = vendor;
        this.units = units;
    }

    public String getCountry() { return country; }
    public int getYear() { return year; }
    public String getQuarter() { return quarter; }
    public String getVendor() { return vendor; }
    public double getUnits() { return units; }
}
