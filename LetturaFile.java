import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class LetturaFile {
    public static void main(String[] args) {
        try {
            // Input file path
            String filePath = "DatiRegistrati.csv";

            // Read the CSV file with a BufferedReader using UTF-8 encoding
            BufferedReader reader = new BufferedReader(new FileReader(filePath, StandardCharsets.UTF_8));

            // Output file path
            // String outputFilePath = "outputfile.csv";
            // FileWriter fw = new FileWriter(outputFilePath);

            // Read and write character by character, skipping the BOM if present
            String line;
            while ((line = reader.readLine()) != null) {
                // Write the line to the output file
            }

            System.out.println("BOM removed successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}