package Finestra;
import java.io.*;
import java.nio.charset.StandardCharsets;
import javax.swing.JTextField;

public class Find_coord {
    public static String closest_coord(JTextField la, JTextField lo)
            throws IOException {

        FileReader f;
        f = new FileReader("./geonames-and-coordinates.csv", StandardCharsets.UTF_8);

        BufferedReader b;
        b = new BufferedReader(f);

        String s, string_latitude = la.getText(), string_longitude = lo.getText(), closest = "";
        String[] line_elements = new String[7];
        int j = 0, m = 0, i = 0, l = 0;
        double double_latitude = Double.parseDouble(string_latitude), double_longitude = Double.parseDouble(string_longitude);
        double data_latitude, data_longitude, distance = 400, temp_distance;

        s = b.readLine();

        while (true) {
            s=b.readLine();
            if(s==null) break;
            l=s.length();
            for (j = 0; j < l; j++) {
                if(s.charAt(j)==';'){
                    line_elements[i] = s.substring(m,j).toLowerCase();
                    m=j+1;
                    i++;
                }
            }
            i=0;
            m=0;
            line_elements[5]=line_elements[5].replace(',', '.');
            line_elements[6]=line_elements[6].replace(',', '.');
            data_latitude = Double.parseDouble(line_elements[5]);
            data_longitude = Double.parseDouble(line_elements[6]);
            temp_distance = Math.sqrt(Math.pow(data_latitude - double_latitude, 2) + Math.pow(data_longitude - double_longitude, 2));
            if (distance > temp_distance) {
                distance = temp_distance;
                closest = s;
            }
            if (b.readLine() == null)
                break;
        }
        System.out.println(closest);
        f.close();
        return closest;
    }
}