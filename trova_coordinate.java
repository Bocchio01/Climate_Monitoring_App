import java.io.*;

public class trova_coordinate {
    public static void main(String[] args) 
    throws IOException {
        FileReader f;
        f=new FileReader("./CoordinateMonitoraggi.dati.csv");//"./Dati_abbreviati.csv"
    
        BufferedReader b;
        b=new BufferedReader(f);
    
        String s, s_latitudine="45", s_longitudine="70", closest="";
        String[] riga = new String[7];
        int j=0, m=0, i=0, l=0, c=0;
        double d_latitude = Double.parseDouble(s_latitudine), d_longitude = Double.parseDouble(s_longitudine);
        double data_latitude, data_longitude, distance=400, temp_distance;

        s=b.readLine()+";";
        
        while (true) {
            s=b.readLine()+";";
            l=s.length();
            for (j = 0; j < l; j++) {
                if(s.charAt(j)==';'){
                    riga[i] = s.substring(m,j);
                    m=j+1;
                    i++;
                }
            }
            i=0;
            m=0;
            data_latitude = Double.parseDouble(riga[5]);
            data_longitude = Double.parseDouble(riga[6]);
            temp_distance = Math.sqrt(Math.pow(data_latitude-d_latitude, 2)+Math.pow(data_longitude-d_longitude, 2));
            if(distance > temp_distance) {
                distance = temp_distance;
                closest = s;
            }
            if(b.readLine()==null) break;
        }
        System.out.println(closest);
      }
}
