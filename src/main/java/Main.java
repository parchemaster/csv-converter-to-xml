import convert.Converter;
import convert.Import;

public class Main {
    public static void main(String[] args) {
        var dataFromCSV = Import.inputCSV();
        dataFromCSV.forEach(line -> Converter.parsingXML(line[0], line[1], line[2], line[3], line[4], line[5], line[9],
                                                        line[10], line[11], line[7], line[8], line[6], line[12], line[15],
                                                        line[16], line[23], line[26], line[27], dataFromCSV.indexOf(line)));
        Converter.archiveZIP(dataFromCSV.get(0)[0]);

        dataFromCSV.forEach(line -> Converter.parsingMissed(line[0], line[13], line[14], line[17], line[18], line[19], line[20],
                line[21], line[22], line[24], line[25], dataFromCSV.indexOf(line)));
    }
}
