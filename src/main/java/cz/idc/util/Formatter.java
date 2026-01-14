package cz.idc.util;

import java.text.NumberFormat;
import java.util.Locale;

public final class Formatter {

    private static final NumberFormat UNITS_FORMAT =
            NumberFormat.getIntegerInstance(Locale.US);

    private Formatter() {
        throw new AssertionError("Utility class should not be instantiated");
    }

    public static String formatUnits(double units) {
        return UNITS_FORMAT.format(Math.round(units));
    }

    public static String formatShare(double share) {
        return String.valueOf(share).concat("%");
    }
}
