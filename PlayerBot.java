import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 * bot player.
 * @author Mohammad
 * @since 1399-2-1
 */
public class PlayerBot extends Player {
    private String name;

    public PlayerBot(String name1) {
        super(name1);
        this.name = name1;
    }

    @Override
    public void addCart(Kart cart) {
        super.addCart(cart);
    }

    @Override
    public void removeCart(Kart cart) {
        super.removeCart(cart);
    }
    /**
     * for play auto carts.
     * @param cart cart on the board.
     * @return a new cart if can play. if can not play return null.
     */
    @Override
    public Kart play(Kart cart) {
        Kart test1 = null;
        Random random = new Random();
        Iterator<Kart> it = carts.iterator();
        // check for all carts. finde color and number.
        while (it.hasNext()) {
            Kart test = it.next();
            if (cart.getColor() == test.getColor() || cart.getNumber() == test.getNumber()) {
                test1 = test;
                removeCart(test1);
                return test1;
            }
        }
        // check for wild
        if (cart.getNumber() == -1) {
            Iterator<Kart> iterator = carts.iterator();
            while (iterator.hasNext()) {
                Kart check = iterator.next();
                if (check.getColor() == cart.getColor() && check.getNumber() != 12)
                    test1 = check;
            }
            // check for wild draw.
        } else if (cart.getNumber() == -2) {
            Iterator<Kart> iterator = carts.iterator();
            while (iterator.hasNext()) {
                Kart check = iterator.next();
                if (check.getColor() == cart.getColor())
                    test1 = check;
            }
        }
        //check for wild or wild draw at end.
        if (test1 == null) {
            Iterator<Kart> it1 = carts.iterator();
            while (it1.hasNext()) {
                Kart test = it1.next();
                if (( test.getNumber() == -1 && cart.getNumber() != 12 ) || test.getNumber() == -2) {
                    test1 = test;
                }
            }
        }
        removeCart(test1);
        return test1;
    }
    @Override
    public ArrayList<Kart> getCarts() {
        return super.getCarts();
    }
}
