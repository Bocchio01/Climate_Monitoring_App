import java.io.*;
import java.util.*;
import prog.io.*;

public class Test {

    public static void main(String[] args) throws UnsupportedEncodingException, IOException {
        ConsoleInputManager in = new ConsoleInputManager();

        /*
         * / File getCSVFiles = new File("./geonames-and-coordinates.csv");
         * Scanner sc = new Scanner(getCSVFiles);
         * sc.useDelimiter(",");
         * while (sc.hasNext()) {
         * System.out.print(sc.next()+"|");
         * }
         * sc.close();
         */
        String miaStringa = in.readLine();
        miaStringa = miaStringa.replace("?", "");
        System.out.println(miaStringa);
    }
}