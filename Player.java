import java.util.ArrayList;

public class Player {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_red = "\u001B[38;5;196m";
    private final String ANSI_abi = "\u001B[38;5;21m";
    private final String ANSI_zard = "\u001B[38;5;190m";
    private final String ANSI_sabz = "\u001B[38;5;40m";
    private final String ANSI_Reset = "\u001B[49m";
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

    public void addCart(Kart cart) {
        carts.add(cart);
    }

    public void removeCart(Kart cart) {
        carts.remove(cart);
    }

    public ArrayList<Kart> getCarts() {
        return carts;
    }

    public Kart play(Kart cart) {
        return cart;

    }

    public Kart rplay(Kart cart, char color, int number) {
        Kart test = null;
        if (cart.getColor() == color || cart.getNumber() == number) {
            test = new Kart(number, color);
        } else if (cart.getColor() == 'l' && cart.getNumber() == -1) {
            if (number != 12&&cart.getColor() == color)
                test = new Kart(number, color);
        } else if (cart.getNumber() == -2) {
            if (cart.getColor() == color)
                test = new Kart(number, color);
        } else if (test == null) {
            if (color == 'l')
                test = new Kart(number, color);
        }
        return test;
    }

    public void showAll() {
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getColor() == 'r')
                System.out.print(ANSI_red + "*******  " + ANSI_RESET);
            else if (carts.get(i).getColor() == 'b')
                System.out.print(ANSI_abi + "*******  " + ANSI_RESET);
            else if (carts.get(i).getColor() == 'y')
                System.out.print(ANSI_zard + "*******  " + ANSI_RESET);
            else if (carts.get(i).getColor() == 'g')
                System.out.print(ANSI_sabz + "*******  " + ANSI_RESET);
            else
                System.out.print("*******  " + ANSI_RESET);
        }
        System.out.println();
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getColor() == 'r')
                System.out.print(ANSI_red + "*     *  " + ANSI_RESET);
            else if (carts.get(i).getColor() == 'b')
                System.out.print(ANSI_abi + "*     *  " + ANSI_RESET);
            else if (carts.get(i).getColor() == 'y')
                System.out.print(ANSI_zard + "*     *  " + ANSI_RESET);
            else if (carts.get(i).getColor() == 'g')
                System.out.print(ANSI_sabz + "*     *  " + ANSI_RESET);
            else
                System.out.print("*     *  " + ANSI_RESET);
        }
        System.out.println();
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getColor() == 'r') {
                if (carts.get(i).getNumber() == 10)
                    System.out.print(ANSI_red + "*  s  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == 11)
                    System.out.print(ANSI_red + "*  r  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == 12)
                    System.out.print(ANSI_red + "*  d  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == -1)
                    System.out.print(ANSI_red + "*  wd *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == -2)
                    System.out.print(ANSI_red + "*  w  *  " + ANSI_RESET);
                else
                    System.out.print(ANSI_red +
                            "*  " + carts.get(i).getNumber() + "  *  ");

            }
            if (carts.get(i).getColor() == 'b') {

                if (carts.get(i).getNumber() == 10)
                    System.out.print(ANSI_abi + "*  s  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == 11)
                    System.out.print(ANSI_abi + "*  r  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == 12)
                    System.out.print(ANSI_abi + "*  d  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == -1)
                    System.out.print(ANSI_abi + "*  wd *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == -2)
                    System.out.print(ANSI_abi + "*  w  *  " + ANSI_RESET);
                else
                    System.out.print(ANSI_abi +
                            "*  " + carts.get(i).getNumber() + "  *  ");

            }
            if (carts.get(i).getColor() == 'g') {
                if (carts.get(i).getNumber() == 10)
                    System.out.print(ANSI_sabz + "*  s  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == 11)
                    System.out.print(ANSI_sabz + "*  r  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == 12)
                    System.out.print(ANSI_sabz + "*  d  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == -1)
                    System.out.print(ANSI_sabz + "*  wd *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == -2)
                    System.out.print(ANSI_sabz + "*  w  *  " + ANSI_RESET);
                else
                    System.out.print(ANSI_sabz +
                            "*  " + carts.get(i).getNumber() + "  *  ");

            }

            if (carts.get(i).getColor() == 'y') {
                if (carts.get(i).getNumber() == 10)
                    System.out.print(ANSI_zard + "*  s  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == 11)
                    System.out.print(ANSI_zard + "*  r  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == 12)
                    System.out.print(ANSI_zard + "*  d  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == -1)
                    System.out.print(ANSI_zard + "*  wd *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == -2)
                    System.out.print(ANSI_zard + "*  w  *  " + ANSI_RESET);
                else
                    System.out.print(ANSI_zard +
                            "*  " + carts.get(i).getNumber() + "  *  ");
            }
            if (carts.get(i).getColor() == 'l') {
                if (carts.get(i).getNumber() == 10)
                    System.out.print("*  s  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == 11)
                    System.out.print("*  r  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == 12)
                    System.out.print("*  d  *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == -1)
                    System.out.print("*  wd *  " + ANSI_RESET);
                else if (carts.get(i).getNumber() == -2)
                    System.out.print("*  w  *  " + ANSI_RESET);
                else
                    System.out.print("*  " + carts.get(i).getNumber() + "  *");

            }

        }
        System.out.println();
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getColor() == 'r')
                System.out.print(ANSI_red + "*     *  " + ANSI_RESET);
            else if (carts.get(i).getColor() == 'b')
                System.out.print(ANSI_abi + "*     *  " + ANSI_RESET);
            else if (carts.get(i).getColor() == 'y')
                System.out.print(ANSI_zard + "*     *  " + ANSI_RESET);
            else if (carts.get(i).getColor() == 'g')
                System.out.print(ANSI_sabz + "*     *  " + ANSI_RESET);
            else
                System.out.print("*     *  " + ANSI_RESET);
        }
        System.out.println();
        for (int i = 0; i < carts.size(); i++) {
            if (carts.get(i).getColor() == 'r')
                System.out.print(ANSI_red + "*******  " + ANSI_RESET);
            else if (carts.get(i).getColor() == 'b')
                System.out.print(ANSI_abi + "*******  " + ANSI_RESET);
            else if (carts.get(i).getColor() == 'y')
                System.out.print(ANSI_zard + "*******  " + ANSI_RESET);
            else if (carts.get(i).getColor() == 'g')
                System.out.print(ANSI_sabz + "*******  " + ANSI_RESET);
            else
                System.out.print("*******  " + ANSI_RESET);
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
