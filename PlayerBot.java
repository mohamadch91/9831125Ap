import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

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

    @Override
    public Kart play(Kart cart) {
        Kart test1 = null;
        Random random=new Random();
        Iterator<Kart> it = carts.iterator();
        while (it.hasNext()) {
            Kart test = it.next();
            if (cart.getColor() == test.getColor() || cart.getNumber() == test.getNumber()) {
                test1 = test;
            }
        }
        if(cart.getNumber()==-1){
            Iterator<Kart> iterator=carts.iterator();
            while (iterator.hasNext()){
                Kart check=it.next();
                if(check.getColor()==cart.getColor()&&check.getNumber()!=12)
                    test1=check;
            }
        }
        else if(cart.getNumber()==-2) {
                Iterator<Kart> iterator=carts.iterator();
                while (iterator.hasNext()){
                    Kart check=it.next();
                    if(check.getColor()==cart.getColor())
                        test1=check;
                }
        }
       else if (test1 == null) {
            Iterator<Kart> it1 = carts.iterator();
            while (it1.hasNext()) {
                Kart test = it1.next();
                if ((test.getNumber() == -1&&cart.getNumber()!=12) || test.getNumber() == -2) {
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
