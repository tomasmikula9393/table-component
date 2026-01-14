package cz.idc.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Table {

    private final List<Row> rows;

    public Table(List<Row> rows) {
        this.rows = new ArrayList<>(rows);
    }

    public List<Row> getRows() {
        return rows;
    }

    public double getTotalUnits() {
        return rows.stream()
                .mapToDouble(Row::getUnits)
                .sum();
    }


    public double getShareForRow(Row row) {
        double totalUnits = getTotalUnits();
        if (row.getUnits() == 0) {
            return 0.0;
        }

        double share = (row.getUnits() / totalUnits) * 100.0;
        return BigDecimal.valueOf(share)
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
    }
}
