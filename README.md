# PC Quarterly Market Share

This project demonstrates how to load raw market data from a CSV file,
process it into a tabular structure, and generate market share reports
for a selected country and reporting period.

The solution is implemented using **Java SE only**, with **OpenCSV**
used for CSV parsing, as required by the assignment.

---

## Project Structure

- `CsvLoader`
    - Loads raw data from CSV into a `Table` object.
- `Table`
    - Holds tabular data and provides derived values such as total units
      and market share for individual rows.
- `TableService`
    - Provides operations over tables:
        - filtering by country and reporting period
        - querying vendor statistics
        - sorting rows
        - locating vendor rows
- `VendorStats`
    - Simple value object representing units and market share for a single vendor.      
- `HtmlExporter`
    - Exports the table into a static HTML file formatted according to
      the reference table.
- `Formatter`
    - Utility class for formatting numeric values (units, percentages).
- `DemoApplication`
    - Demonstrates how a client would use the API.

---

## Supported Features

- Load CSV input data
- Filter data by:
    - country
    - year
    - quarter
- Calculate:
    - total units
    - market share per vendor
- Query vendor statistics (units and share)
- Sort table rows:
    - alphabetically by vendor
    - by unit values
- Export results to HTML

---

## HTML Output

The generated HTML file is a standalone static document.
It can be opened directly in any web browser (Chrome, Firefox, Edge).

Example output files:
- `output.html`
- `output-sorted-by-vendors.html`
- `output-sorted-by-units.html`

---

## How to Run

1. Open the project in IntelliJ IDEA (or any Java IDE)
2. Run `DemoApplication`
3. Configure filters and input parameters
4. Open the generated HTML files in a web browser

---

## Design Notes

- The CSV loader only reads raw data.
- All calculations (totals, shares) are derived values.
- Business logic is separated from presentation logic.
- The API is designed to be easy to use by other programmers.

