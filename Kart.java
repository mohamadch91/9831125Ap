import java.lang.reflect.AnnotatedArrayType;
import java.util.Objects;

public class Kart {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_red = "\u001B[38;5;196m";
    private final String ANSI_abi = "\u001B[38;5;21m";
    private final String ANSI_zard = "\u001B[38;5;190m";
    private final String ANSI_sabz = "\u001B[38;5;40m";
    private final String ANSI_Reset = "\u001B[49m";
    //    private final String BLACK = "\u26AA";
//    private final String RED = "\u26AA";
    private int number;
    // 10 for skip 11 for reverse  12 for draw and -1 for wild draw ane -2 for wild
    private char color;
    private int point;

    public Kart(int number, char color) {
        this.number = number;
        this.color = color;
        setPoint();
    }

    public char getColor() {
        return color;
    }

    public int getPoint() {
        return point;
    }

    public int getNumber() {
        return number;
    }

    private void setPoint() {
        if (color != 'l') {
            if (number == 10 || number == 11 || number == 12) point = 20;
            else point = number;
        } else
            point = 50;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Kart )) return false;
        Kart kart = (Kart) o;
        return getNumber() == kart.getNumber() &&
                getColor() == kart.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getColor());
    }

    public void show() {
        if (color == 'r') {
            System.out.println(ANSI_red + "*******" + ANSI_RESET);
            System.out.println(ANSI_red + "*     *" + ANSI_Reset);
//            System.out.println(ANSI_red + "*     *" + ANSI_RESET);
            if (number == 10)
                System.out.println(ANSI_red + "*  s  *" + ANSI_RESET);
            else if (number == 11)
                System.out.println(ANSI_red + "*  r  *" + ANSI_RESET);
            else if (number == 12)
                System.out.println(ANSI_red + "*  d  *" + ANSI_RESET);
            else if (number == -1)
                System.out.println(ANSI_red + "*  wd *" + ANSI_RESET);
            else if (number == -2)
                System.out.println(ANSI_red + "*  w  *" + ANSI_RESET);
            else
                System.out.println(ANSI_red+
                        "*  "+number+"  *");
            System.out.println(ANSI_red + "*     *" + ANSI_RESET);
//            System.out.println(ANSI_red + "*     *" + ANSI_RESET);
//            System.out.println(ANSI_red + "*     *" + ANSI_RESET);
            System.out.println(ANSI_red + "*******" + ANSI_RESET);
        }
        if (color == 'b') {
            System.out.println(ANSI_abi + "*******" + ANSI_RESET);
            System.out.println(ANSI_abi + "*     *" + ANSI_Reset);
//            System.out.println(ANSI_abi + "*     *" + ANSI_RESET);
            if (number == 10)
                System.out.println(ANSI_abi + "*  s  *" + ANSI_RESET);
            else if (number == 11)
                System.out.println(ANSI_abi + "*  r  *" + ANSI_RESET);
            else if (number == 12)
                System.out.println(ANSI_abi + "*  d  *" + ANSI_RESET);
            else if (number == -1)
                System.out.println(ANSI_abi + "*  wd *" + ANSI_RESET);
            else if (number == -2)
                System.out.println(ANSI_abi + "*  w  *" + ANSI_RESET);
            else
                System.out.println(ANSI_abi+
                        "*  "+number+"  *");
            System.out.println(ANSI_abi + "*     *" + ANSI_RESET);
//            System.out.println(ANSI_abi + "*     *" + ANSI_RESET);
//            System.out.println(ANSI_abi + "*     *" + ANSI_RESET);
            System.out.println(ANSI_abi + "*******" + ANSI_RESET);
        }
        if (color == 'g') {
            System.out.println(ANSI_sabz + "*******" + ANSI_RESET);
            System.out.println(ANSI_sabz + "*     *" + ANSI_Reset);
//            System.out.println(ANSI_sabz + "*     *" + ANSI_RESET);
            if (number == 10)
                System.out.println(ANSI_sabz + "*  s  *" + ANSI_RESET);
            else if (number == 11)
                System.out.println(ANSI_sabz + "*  r  *" + ANSI_RESET);
            else if (number == 12)
                System.out.println(ANSI_sabz + "*  d  *" + ANSI_RESET);
            else if (number == -1)
                System.out.println(ANSI_sabz + "*  wd *" + ANSI_RESET);
            else if (number == -2)
                System.out.println(ANSI_sabz + "*  w  *" + ANSI_RESET);
            else
                System.out.println(ANSI_sabz+
                        "*  "+number+"  *");
            System.out.println(ANSI_sabz + "*     *" + ANSI_RESET);
//            System.out.println(ANSI_sabz + "*     *" + ANSI_RESET);
//            System.out.println(ANSI_sabz + "*     *" + ANSI_RESET);
            System.out.println(ANSI_sabz + "*******" + ANSI_RESET);
        }
        if (color == 'y') {
            System.out.println(ANSI_zard + "*******" + ANSI_RESET);
//            System.out.println(ANSI_zard + "*     *" + ANSI_Reset);
            System.out.println(ANSI_zard + "*     *" + ANSI_RESET);
            if (number == 10)
                System.out.println(ANSI_zard + "*  s  *" + ANSI_RESET);
            else if (number == 11)
                System.out.println(ANSI_zard + "*  r  *" + ANSI_RESET);
            else if (number == 12)
                System.out.println(ANSI_zard + "*  d  *" + ANSI_RESET);
            else if (number == -1)
                System.out.println(ANSI_zard + "*  wd *" + ANSI_RESET);
            else if (number == -2)
                System.out.println(ANSI_zard + "*  w  *" + ANSI_RESET);
            else
                System.out.println(ANSI_zard+
                        "*  "+number+"  *");
            System.out.println(ANSI_zard + "*     *" + ANSI_RESET);
//            System.out.println(ANSI_zard + "*     *" + ANSI_RESET);
//            System.out.println(ANSI_zard + "*     *" + ANSI_RESET);
            System.out.println(ANSI_zard + "*******" + ANSI_RESET);
        }
        if (color == 'l') {
            System.out.println("*******" + ANSI_RESET);
            System.out.println("*     *" + ANSI_Reset);
//            System.out.println("*     *"+ANSI_RESET);
            if (number == 10)
                System.out.println("*  s  *" + ANSI_RESET);
            else if (number == 11)
                System.out.println("*  r  *" + ANSI_RESET);
            else if (number == 12)
                System.out.println("*  d  *" + ANSI_RESET);
            else if (number == -1)
                System.out.println("*  wd *" + ANSI_RESET);
            else if (number == -2)
                System.out.println("*  w  *" + ANSI_RESET);
            else
                System.out.println("*  "+number+"  *");
            System.out.println("*     *" + ANSI_RESET);
//            System.out.println("*     *" + ANSI_RESET);
//            System.out.println("*     *"+ANSI_RESET);
            System.out.println("*******" + ANSI_RESET);
        }

    }

    public void setColor(char color) {
        this.color = color;
    }
}
