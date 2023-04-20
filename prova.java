import java.nio.charset.StandardCharsets;

public class prova {
    public static void main(String[] args) {

        // Stampa caratteri accentati alfabeto latino con codici esadecimali
        byte[] b = new byte[] { (byte) 0xc3, (byte) 0x9f };
        String s = new String(b, StandardCharsets.UTF_8);
        System.out.println("The string: " + s);
        // System.out.println("The codepoint for that first char: " + (int)
        // s.charAt(0));
    }

}
