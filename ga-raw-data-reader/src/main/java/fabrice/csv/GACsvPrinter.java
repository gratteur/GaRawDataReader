package fabrice.csv;

import fabrice.domain.Statistics;
import org.apache.commons.csv.CSVFormat;

import java.io.IOException;
import java.io.Writer;

/**
 * Created by fabrice on 10.08.15.
 */
public class GACsvPrinter {

    private CsvContent csvContent;
    private Statistics statistics;

    public GACsvPrinter(CsvContent csvContent, Statistics statistics) {
        this.csvContent = csvContent;
        this.statistics = statistics;
    }

    public void write(Writer writer) throws IOException {
        CSVPrinter csvPrinter;
        CSVFormat csvFormat = CSVFormat.DEFAULT.withDelimiter(';');
        try {
            csvPrinter = new CSVPrinter(writer, csvFormat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        csvPrinter.printRecord(csvContent.getHeaders());
        for (String[] row : csvContent.getLines()) {
            csvPrinter.printRecord(row);
            statistics.incrementPrintedCsvLines();
        }

    }
}
