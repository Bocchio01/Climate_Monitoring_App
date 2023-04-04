import java.io.*;

public class trova_stringa {
    public static void main(String[] args) 
    throws IOException {
        FileReader f;
        f=new FileReader("./CoordinateMonitoraggi.dati.csv");
    
        BufferedReader b;
        b=new BufferedReader(f);
    
        String s, ricerca="Westborough";
        String[] riga = new String[7];
        int j=0, m=0, i=0, fine=0, l=0;


        boolean find=false;

        s=b.readLine()+",";
        //System.out.println(s);
        
        while (find==false) {
            l=s.length();
            System.out.println(l);
            for (int j = 0; j < l; j++) {
                if(s.charAt(j)==','){
                    riga[i] = s.substring(m,j);
                    //System.out.println(riga[i]);
                    m=j+1;
                    i++;
                }
                j++;
            }
            /*while (j<l) {
                if(s.charAt(j)==','){
                    riga[i] = s.substring(m,j);
                    //System.out.println(riga[i]);
                    m=j+1;
                    i++;
                }
                j++;
            }*/
            i=0;
            j=0;
            m=0;
            for (int n = 0; n <= 6; n++) {
                System.out.println(riga[n]);
                if (riga[n]==ricerca) {
                    System.out.println(s);
                    find=true;
                }
                else{
                    s=b.readLine()+",";
                    //System.out.println(s);
                }
            }
            //System.out.println(s);
            if(s==null) break;
        }
      }
}
