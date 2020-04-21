import java.lang.reflect.AnnotatedArrayType;
import java.util.Objects;

/**
 * a cart class for show specific cart.
 * @author Mohammad
 */
public class Kart {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_red = "\u001B[38;5;196m";
    private final String ANSI_abi = "\u001B[38;5;21m";
    private final String ANSI_zard = "\u001B[38;5;190m";
    private final String ANSI_sabz = "\u001B[38;5;40m";
    private final String ANSI_Reset = "\u001B[49m";
    private final String ANSIF_WHITE = "\u001B[48;5;159m";
    private final String ANSIF_WHITE1 = "\u001B[48;5;9m";
    private final String ANSIF_WHITE2 = "\u001B[48;5;98m";
    private final String ANSIF_WHITE3 = "\u001B[48;5;0m";
    private final String ANSIF_khakestari = "\u001B[48;5;233m";
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

    /**
     * set the point of cart.
     * use it on constructor.
     */
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

    /**
     * show one specific cart.
     */
    public void show() {
        if (color == 'r') {
            System.out.println(ANSI_red +ANSIF_WHITE+ "  *******  " +ANSI_Reset+ ANSI_RESET);
            System.out.println(ANSI_red +ANSIF_WHITE+"  *     *  " + ANSI_Reset);

            if (number == 10)
                System.out.println(ANSI_red +ANSIF_WHITE+"  *  s  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == 11)
                System.out.println(ANSI_red +ANSIF_WHITE+"  *  r  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == 12)
                System.out.println(ANSI_red +ANSIF_WHITE+"  *  d  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == -1)
                System.out.println(ANSI_red +ANSIF_WHITE+"  *  wd *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == -2)
                System.out.println(ANSI_red +ANSIF_WHITE+"  *  w  *  "+ANSI_Reset+ ANSI_RESET);
            else
                System.out.println(ANSI_red+ANSIF_WHITE+
                        "  *  "+number+"  *  "+ANSI_Reset);
            System.out.println(ANSI_red +ANSIF_WHITE+"  *     *  "+ANSI_Reset+ ANSI_RESET);
            System.out.println(ANSI_red +ANSIF_WHITE+"  *******  "+ANSI_Reset+ ANSI_RESET);
        }
        if (color == 'b') {
            System.out.println(ANSI_abi +ANSIF_WHITE3+"  *******  "+ANSI_Reset+ ANSI_RESET);
            System.out.println(ANSI_abi +ANSIF_WHITE3+"  *     *  "+ANSI_Reset+ ANSI_Reset);
            if (number == 10)
                System.out.println(ANSI_abi +ANSIF_WHITE3+"  *  s  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == 11)
                System.out.println(ANSI_abi +ANSIF_WHITE3+"  *  r  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == 12)
                System.out.println(ANSI_abi +ANSIF_WHITE3+"  *  d  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == -1)
                System.out.println(ANSI_abi +ANSIF_WHITE3+"  *  wd *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == -2)
                System.out.println(ANSI_abi +ANSIF_WHITE3+"  *  w  *  "+ANSI_Reset+ ANSI_RESET);
            else
                System.out.println(ANSI_abi+ANSIF_WHITE3+
                        "  *  "+number+"  *  "+ANSI_Reset);
            System.out.println(ANSI_abi +ANSIF_WHITE3+"  *     *  "+ANSI_Reset+ ANSI_RESET);
            System.out.println(ANSI_abi +ANSIF_WHITE3+"  *******  "+ANSI_Reset+ ANSI_RESET);
        }
        if (color == 'g') {
            System.out.println(ANSI_sabz +ANSIF_WHITE2+"  *******  " +ANSI_Reset+ ANSI_RESET);
            System.out.println(ANSI_sabz +ANSIF_WHITE2+"  *     *  "+ANSI_Reset+ ANSI_Reset);
            if (number == 10)
                System.out.println(ANSI_sabz +ANSIF_WHITE2+"  *  s  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == 11)
                System.out.println(ANSI_sabz +ANSIF_WHITE2+"  *  r  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == 12)
                System.out.println(ANSI_sabz +ANSIF_WHITE2+"  *  d  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == -1)
                System.out.println(ANSI_sabz +ANSIF_WHITE2+"  *  wd *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == -2)
                System.out.println(ANSI_sabz +ANSIF_WHITE2+"  *  w  *  "+ANSI_Reset+ ANSI_RESET);
            else
                System.out.println(ANSI_sabz+ANSIF_WHITE2+
                        "  *  "+number+"  *  "+ANSI_Reset+ANSI_RESET);
            System.out.println(ANSI_sabz +ANSIF_WHITE2+"  *     *  "+ANSI_Reset+ ANSI_RESET);
            System.out.println(ANSI_sabz +ANSIF_WHITE2+"  *******  "+ANSI_Reset+ ANSI_RESET);
        }
        if (color == 'y') {
            System.out.println(ANSI_zard +ANSIF_WHITE1+"  *******  "+ANSI_Reset+ ANSI_RESET);
            System.out.println(ANSI_zard +ANSIF_WHITE1+"  *     *  "+ANSI_Reset+ ANSI_RESET);
            if (number == 10)
                System.out.println(ANSI_zard +ANSIF_WHITE1+"  *  s  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == 11)
                System.out.println(ANSI_zard +ANSIF_WHITE1+"  *  r  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == 12)
                System.out.println(ANSI_zard +ANSIF_WHITE1+"  *  d  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == -1)
                System.out.println(ANSI_zard +ANSIF_WHITE1+"  *  wd *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == -2)
                System.out.println(ANSI_zard +ANSIF_WHITE1+"  *  w  *  "+ANSI_Reset+ ANSI_RESET);
            else
                System.out.println(ANSI_zard+ANSIF_WHITE1+
                        "  *  "+number+"  *  "+ANSI_Reset);
            System.out.println(ANSI_zard +ANSIF_WHITE1+"  *     *  "+ANSI_Reset+ ANSI_RESET);
            System.out.println(ANSI_zard +ANSIF_WHITE1+"  *******  "+ANSI_Reset+ ANSI_RESET);
        }
        if (color == 'l') {
            System.out.println(ANSIF_khakestari+"  *******  "+ANSI_Reset+ ANSI_RESET);
            System.out.println(ANSIF_khakestari+"  *     *  "+ANSI_Reset+ ANSI_Reset);
            if (number == 10)
                System.out.println(ANSIF_khakestari+"  *  s  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == 11)
                System.out.println(ANSIF_khakestari+"  *  r  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == 12)
                System.out.println(ANSIF_khakestari+"  *  d  *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == -1)
                System.out.println(ANSIF_khakestari+"  *  wd *  "+ANSI_Reset+ ANSI_RESET);
            else if (number == -2)
                System.out.println(ANSIF_khakestari+"  *  w  *  "+ANSI_Reset+ ANSI_RESET);
            else
                System.out.println(ANSIF_khakestari+"  *  "+number+"  *  "+ANSI_Reset);
            System.out.println(ANSIF_khakestari+"  *     *  "+ANSI_Reset+ ANSI_RESET);
            System.out.println(ANSIF_khakestari+"  *******  "+ANSI_Reset+ ANSI_RESET);
        }

    }

    public void setColor(char color) {
        this.color = color;
    }
}
