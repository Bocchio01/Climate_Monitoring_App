package Finestra;

public class Theme {

    public static boolean b=true;

    public static boolean tema(){
        b=!b;
        return b;
    }
    public static boolean theme(){
        tema();
        boolean t = tema();
        return t;
    }
}
