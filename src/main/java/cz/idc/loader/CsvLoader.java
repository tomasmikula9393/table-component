package cz.idc.loader;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;
import cz.idc.model.Row;
import cz.idc.model.Table;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {

    public Table loadCsvData(Path filePath) throws IOException, CsvValidationException {
        List<Row> rows = new ArrayList<>();

        try (Reader reader = Files.newBufferedReader(filePath);
             CSVReader csvReader = new CSVReader(reader)) {

            String[] line;
            boolean headerSkipped = false;

            while ((line = csvReader.readNext()) != null) {
                if (!headerSkipped) {
                    headerSkipped = true;
                    continue;
                }

                rows.add(parseRow(line));
            }
        }

        return new Table(rows);
    }

    private Row parseRow(String[] line) {
        String country = line[0];
        String []timescale = line[1].split(" ");
        String vendor = line[2];
        double units = Double.parseDouble(line[3]);

        int year = Integer.parseInt(timescale[0]);
        String quarter = timescale[1];

        return new Row(country, year, quarter, vendor, units);
    }
}
