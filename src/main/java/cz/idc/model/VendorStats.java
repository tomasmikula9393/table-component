package cz.idc.model;

import cz.idc.util.Formatter;

public class VendorStats {

    private final double units;
    private final double share;

    public VendorStats(double units, double share) {
        this.units = units;
        this.share = share;
    }

    public double getUnits() {
        return units;
    }

    public double getShare() {
        return share;
    }

    public String getFormattedShare() {
        return Formatter.formatShare(share);
    }

    public String getFormattedUnits() {
        return Formatter.formatUnits(units);
    }
}

