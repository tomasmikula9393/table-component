package cz.idc.exporter;

import cz.idc.model.Table;

import java.io.OutputStream;

public interface TableExporter {
    /**
     * Exports the given table to the provided output stream.
     *
     * @param table        table to export
     * @param outputStream target output stream
     */
    void export(Table table, OutputStream outputStream);
}
