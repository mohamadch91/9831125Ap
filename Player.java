import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * Real player class.
 * @author Mohammad
 */
public class Player {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_red = "\u001B[38;5;196m";
    private final String ANSI_abi = "\u001B[38;5;21m";
    private final String ANSI_zard = "\u001B[38;5;190m";
    private final String ANSI_sabz = "\u001B[38;5;28m";
    private final String ANSI_Reset = "\u001B[49m";
    private final String ANSIF_WHITE = "\u001B[48;5;159m";
    private final String ANSIF_WHITE1 = "\u001B[48;5;9m";
    private final String ANSIF_WHITE2 = "\u001B[48;5;98m";
    private final String ANSIF_WHITE3 = "\u001B[48;5;0m";
    private final String ANSIF_khakestari = "\u001B[48;5;233m";
    protected String name;
    protected ArrayList<Kart> carts;

    public Player(String name1) {
        carts = new ArrayList<>();
        name = name1;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }

    /**
     * Add cart to player carts.
     * @param cart cart to add.
     */
    public void addCart(Kart cart) {
        carts.add(cart);
    }

    /**
     * Remove carts from list.
     * @param cart cart to remove.
     */
    public void removeCart(Kart cart) {
        carts.remove(cart);
    }

    public ArrayList<Kart> getCarts() {
        return carts;
    }

    /**
     * just for override.
     * @param cart ...
     * @return kart..
     */
    public Kart play(Kart cart) {
        return cart;

    }

    /**
     * play a card .
     * @param cart the main cart in game.
     * @param color color of new cart.
     * @param number number of new cart.
     * @return if can play cart ret new cart with color and number.
     */
    public Kart rplay(Kart cart, char color, int number) {
        Kart test = null;
        // check can play cart or not.
        if (cart.getColor() == color || cart.getNumber() == number) {
            test = new Kart(number, color);
            // play wild draw if draw not on main and dont have cart
        } else if (cart.getColor() == 'l' && cart.getNumber() == -1) {
            if (number != 12 && cart.getColor() == color)
                test = new Kart(number, color);
            // play wild if not card
        } else if (cart.getNumber() == -2) {
            if (cart.getColor() == color)
                test = new Kart(number, color);
        }
        // play wild
        if (test == null) {
            if (color == 'l')
                test = new Kart(number, color);
        }
        // auto play if wrong input . for real players
        if (test == null) {
            Random random = new Random();
            Iterator<Kart> it = carts.iterator();
            // look for all carts and find color and number
            while (it.hasNext()) {
                Kart test2 = it.next();
                if (cart.getColor() == test2.getColor() || cart.getNumber() == test2.getNumber()) {
                    test = test2;
                    removeCart(test);
                    return test;
                }
            }
            // if nothing play wild draw
            if (cart.getNumber() == -1) {
                Iterator<Kart> iterator = carts.iterator();
                while (iterator.hasNext()) {
                    Kart check = iterator.next();
                    if (check.getColor() == cart.getColor() && check.getNumber() != 12)
                        test = check;
                }
                // if cannot play wild draw play draw
            } else if (cart.getNumber() == -2) {
                Iterator<Kart> iterator = carts.iterator();
                while (iterator.hasNext()) {
                    Kart check = iterator.next();
                    if (check.getColor() == cart.getColor())
                        test = check;
                }
            }
            // check for draw and wild draw end of it
            if (test == null) {
                Iterator<Kart> it1 = carts.iterator();
                while (it1.hasNext()) {
                    Kart test2 = it1.next();
                    if (( test2.getNumber() == -1 && cart.getNumber() != 12 ) || test2.getNumber() == -2) {
                        test = test2;

                    }
                }
            }

        }
        return test;
    }

    /**
     * show a all carts of player in one row.
     */
    public void showAll() {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getColor() == 'r')
                System.out.print(ANSI_red+ANSIF_WHITE + "  *******  " +ANSI_Reset+ ANSI_RESET);
            else if (carts.get(i).getColor() == 'b')
                System.out.print(ANSI_abi +ANSIF_WHITE3+ "  *******  "+ANSI_Reset + ANSI_RESET);
            else if (carts.get(i).getColor() == 'y')
                System.out.print(ANSI_zard +ANSIF_WHITE1
                        +"  *******  " + ANSI_RESET+ANSI_Reset);
            else if (carts.get(i).getColor() == 'g')
                System.out.print(ANSI_sabz +ANSIF_WHITE2+ "  *******  "+ANSI_Reset + ANSI_RESET);
            else
                System.out.print(ANSIF_khakestari+"  *******  " +ANSI_Reset
                        + ANSI_RESET);
            System.out.print("  ");
        }
        System.out.println();
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getColor() == 'r')
                System.out.print(ANSI_red + ANSIF_WHITE+"  *     *  "+ANSI_Reset + ANSI_RESET);
            else if (carts.get(i).getColor() == 'b')
                System.out.print(ANSI_abi +ANSIF_WHITE3+ "  *     *  "+ANSI_Reset + ANSI_RESET);
            else if (carts.get(i).getColor() == 'y')
                System.out.print(ANSI_zard+ANSIF_WHITE1 + "  *     *  "+ANSI_Reset + ANSI_RESET);
            else if (carts.get(i).getColor() == 'g')
                System.out.print(ANSI_sabz +ANSIF_WHITE2+ "  *     *  "+ANSI_Reset + ANSI_RESET);
            else
                System.out.print(ANSIF_khakestari+"  *     *  "+ANSI_Reset + ANSI_RESET);
            System.out.print("  ");
        }
        System.out.println();
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getColor() == 'r') {
                if (carts.get(i).getNumber() == 10)
                    System.out.print(ANSI_red +ANSIF_WHITE+ "  *  s  *  "+ANSI_Reset + ANSI_RESET);
                else if (carts.get(i).getNumber() == 11)
                    System.out.print(ANSI_red +ANSIF_WHITE+"  *  r  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == 12)
                    System.out.print(ANSI_red +ANSIF_WHITE+"  *  d  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == -1)
                    System.out.print(ANSI_red +ANSIF_WHITE+"  *  wd *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == -2)
                    System.out.print(ANSI_red +ANSIF_WHITE+"  *  w  *  " +ANSI_Reset+ANSI_RESET);
                else
                    System.out.print(ANSI_red +ANSIF_WHITE+
                            "  *  " + carts.get(i).getNumber() + "  *  "+ANSI_Reset+ANSI_RESET);
            }
            if (carts.get(i).getColor() == 'b') {
                if (carts.get(i).getNumber() == 10)
                    System.out.print(ANSI_abi +ANSIF_WHITE3+"  *  s  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == 11)
                    System.out.print(ANSI_abi +ANSIF_WHITE3+"  *  r  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == 12)
                    System.out.print(ANSI_abi +ANSIF_WHITE3+"  *  d  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == -1)
                    System.out.print(ANSI_abi +ANSIF_WHITE3+"  *  wd *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == -2)
                    System.out.print(ANSI_abi +ANSIF_WHITE3+"  *  w  *  " +ANSI_Reset+ANSI_RESET);
                else
                    System.out.print(ANSI_abi +ANSIF_WHITE3+
                            "  *  " + carts.get(i).getNumber() + "  *  "+ANSI_Reset+ANSI_RESET);
            }
            if (carts.get(i).getColor() == 'g') {
                if (carts.get(i).getNumber() == 10)
                    System.out.print(ANSI_sabz +ANSIF_WHITE2+"  *  s  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == 11)
                    System.out.print(ANSI_sabz +ANSIF_WHITE2+"  *  r  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == 12)
                    System.out.print(ANSI_sabz +ANSIF_WHITE2+"  *  d  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == -1)
                    System.out.print(ANSI_sabz +ANSIF_WHITE2+"  *  wd *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == -2)
                    System.out.print(ANSI_sabz +ANSIF_WHITE2+"  *  w  *  " +ANSI_Reset+ANSI_RESET);
                else
                    System.out.print(ANSI_sabz +ANSIF_WHITE2+
                            "  *  " + carts.get(i).getNumber() + "  *  "+ANSI_Reset+ANSI_RESET);

            }
            if (carts.get(i).getColor() == 'y') {
                if (carts.get(i).getNumber() == 10)
                    System.out.print(ANSI_zard +ANSIF_WHITE1+"  *  s  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == 11)
                    System.out.print(ANSI_zard +ANSIF_WHITE1+"  *  r  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == 12)
                    System.out.print(ANSI_zard +ANSIF_WHITE1+"  *  d  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == -1)
                    System.out.print(ANSI_zard +ANSIF_WHITE1+"  *  wd *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == -2)
                    System.out.print(ANSI_zard +ANSIF_WHITE1+"  *  w  *  " +ANSI_Reset+ANSI_RESET);
                else
                    System.out.print(ANSI_zard +ANSIF_WHITE1+
                            "  *  " + carts.get(i).getNumber() + "  *  "+ANSI_Reset+ANSI_RESET);
            }
            if (carts.get(i).getColor() == 'l') {
                if (carts.get(i).getNumber() == 10)
                    System.out.print(ANSIF_khakestari+"  *  s  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == 11)
                    System.out.print(ANSIF_khakestari+"  *  r  *  " +ANSI_Reset+ANSI_RESET);
                else if (carts.get(i).getNumber() == 12)
                    System.out.print(ANSIF_khakestari+"  *  d  *  " +ANSI_Reset+ ANSI_RESET);
                else if (carts.get(i).getNumber() == -1)
                    System.out.print(ANSIF_khakestari+"  *  wd *  "+ANSI_Reset + ANSI_RESET);
                else if (carts.get(i).getNumber() == -2)
                    System.out.print(ANSIF_khakestari+"  *  w  *  "+ANSI_Reset + ANSI_RESET);
                else
                    System.out.print(ANSIF_khakestari+"  *  " + carts.get(i).getNumber() + "  *"+ANSI_Reset+ANSI_RESET);

            }
            System.out.print("  ");
        }
        System.out.println();
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getColor() == 'r')
                System.out.print(ANSI_red +ANSIF_WHITE+"  *     *  " +ANSI_Reset+ANSI_RESET);
            else if (carts.get(i).getColor() == 'b')
                System.out.print(ANSI_abi +ANSIF_WHITE3+"  *     *  " +ANSI_Reset+ANSI_RESET);
            else if (carts.get(i).getColor() == 'y')
                System.out.print(ANSI_zard +ANSIF_WHITE1+"  *     *  " +ANSI_Reset+ANSI_RESET);
            else if (carts.get(i).getColor() == 'g')
                System.out.print(ANSI_sabz +ANSIF_WHITE2+"  *     *  " +ANSI_Reset+ANSI_RESET);
            else
                System.out.print(ANSIF_khakestari+"  *     *  " + ANSI_Reset+ANSI_RESET);
            System.out.print("  ");
        }
        System.out.println();
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getColor() == 'r')
                System.out.print(ANSI_red +ANSIF_WHITE+"  *******  " +ANSI_Reset+ANSI_RESET);
            else if (carts.get(i).getColor() == 'b')
                System.out.print(ANSI_abi +ANSIF_WHITE3+"  *******  " +ANSI_Reset+ANSI_RESET);
            else if (carts.get(i).getColor() == 'y')
                System.out.print(ANSI_zard +ANSIF_WHITE1+"  *******  " +ANSI_Reset+ANSI_RESET);
            else if (carts.get(i).getColor() == 'g')
                System.out.print(ANSI_sabz +ANSIF_WHITE2+"  *******  " +ANSI_Reset+ANSI_RESET);
            else
                System.out.print(ANSIF_khakestari+"  *******  " +ANSI_Reset+ ANSI_RESET);
            System.out.print("  ");
        }
        System.out.println();

    }

    public void setCarts(ArrayList<Kart> carts) {
        this.carts = carts;
    }


    public String getName() {
        return name;
    }
}
