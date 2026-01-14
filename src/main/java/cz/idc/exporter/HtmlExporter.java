package cz.idc.exporter;

import cz.idc.model.Row;
import cz.idc.model.Table;
import cz.idc.util.Formatter;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

public class HtmlExporter implements TableExporter {

    @Override
    public void export(Table table, OutputStream outputStream) {
        try (PrintWriter writer = new PrintWriter(
                new OutputStreamWriter(outputStream, StandardCharsets.UTF_8))) {

            writer.println("<!DOCTYPE html>");
            writer.println("<html>");
            writer.println("<head>");
            writer.println("<meta charset=\"UTF-8\">");
            writer.println("<title>PC Quarterly Market Share</title>");
            writer.println("<style>");
            writer.println("table { border-collapse: collapse; margin-top: 10px; }");
            writer.println("th, td { border: 1px solid black; padding: 6px 12px; text-align: center; }");
            writer.println("th { background-color: #d9d9d9; font-weight: bold; }");
            writer.println(".total { background-color: #ffff99; font-weight: bold; }");
            writer.println("</style>");
            writer.println("</head>");
            writer.println("<body>");

            writer.println("<table>");
            createHeader(writer);
            writeRows(writer, table);
            createFooter(writer, table);
            writer.println("</table>");

            writer.println("</body>");
            writer.println("</html>");
        }
    }

    private void createHeader(PrintWriter writer) {
        writer.println("<tr>");
        writer.println("<th>Vendor</th>");
        writer.println("<th>Units</th>");
        writer.println("<th>Share</th>");
        writer.println("</tr>");
    }

    private void writeRows(PrintWriter writer, Table table) {
        for (Row row : table.getRows()) {
            writer.println("<tr>");
            writer.printf("<td>%s</td>%n", row.getVendor());
            writer.printf("<td>%s</td>%n", Formatter.formatUnits(row.getUnits()));
            writer.printf("<td>%.1f%%</td>%n", table.getShareForRow(row));
            writer.println("</tr>");
        }
    }

    private void createFooter(PrintWriter writer, Table table) {
        writer.println("<tr class=\"total\">");
        writer.println("<td>Total</td>");
        writer.printf("<td>%,.0f</td>%n", table.getTotalUnits());
        writer.println("<td>100.0</td>");
        writer.println("</tr>");
    }
}
