import convert.Converter;
import convert.Import;

public class Main {
    public static void main(String[] args) {
        var dataFromCSV = Import.InputCSV();
        for(String[] line : dataFromCSV) {
            Converter.CreateXML(line[0], line[1], line[2], line[3], line[4], line[5], line[9],
                                line[10], line[11], line[7], line[8], line[6], line[12], line[15],
                                line[16], line[23], line[26], line[27], dataFromCSV.indexOf(line));
        }


    }
}
