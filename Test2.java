import com.opencsv.*;

import java.io.*;

public class Test2 {
    public static void main(String[] args) throws Exception {
        try {
            String filePath = "./CoordinateMonitoraggio.dati.csv";
            FileReader fileReader = new FileReader(filePath);

            CSVReader openCsvReader = new CSVReader(fileReader);
            String[] record;
            while ((record = openCsvReader.readNext()) != null) {
                for (String token : record) {
                    System.out.print(token + "\t");
                }
                System.out.println();
            }
            openCsvReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
