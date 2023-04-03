import java.io.File;  // Import the File class
import java.io.IOException;  // Import the IOException class to handle errors

public class CreaFile {
  public static void main(String[] args) {
    try {
      File file = new File("filename.txt");
      if (file.createNewFile()) {
        System.out.println("Il file creato e': " + file.getName());
      } else {
        System.out.println("Il file esiste gia'.");
      }
    } catch (IOException e) {
      System.out.println("Errore.");
      e.printStackTrace();
    }
  }
}
    
}
