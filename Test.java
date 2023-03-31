import java.io.*;
import java.util.*;

public class Test {

    public static void main(String[] args) throws FileNotFoundException{
        
        File getCSVFiles = new File("./geonames-and-coordinates.csv");
        Scanner sc = new Scanner(getCSVFiles);
        sc.useDelimiter(",");
        while (sc.hasNext()) {
            System.out.print(sc.next()+"|");
        }
        sc.close();
    }
}