package convert;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class Import {
    static String pathCSV = "src/main/resources/input.csv";

    public static List<String[]> inputCSV() {
        List<String[]> allData = new ArrayList<>();
        try {
            // Create an object of file reader class with CSV file as a parameter.
            FileReader filereader = new FileReader(pathCSV);

            // create csvParser object with
            // custom separator semi-colon
            CSVParser parser = new CSVParserBuilder().withSeparator(';').build();

            // create csvReader object with parameter
            // filereader and parser
            CSVReader csvReader = new CSVReaderBuilder(filereader)
                    .withCSVParser(parser)
                    .withSkipLines(1)
                    .build();

            // Read all data at once
            allData = csvReader.readAll();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return allData;
    }
}
