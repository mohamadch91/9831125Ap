import java.util.ArrayList;
import java.util.Iterator;

public class PlayerBot extends Player {
     private String name;
    public PlayerBot(String name1){
        super(name1);
        this.name=name1;
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
        Kart test1=null;
        Iterator<Kart> it=carts.iterator();
        while (it.hasNext()){
            Kart test=it.next();
            if(cart.getColor()==test.getColor()||cart.getNumber()==test.getNumber()){
                test1=test;
                removeCart(test);
            }
        }
        it=carts.iterator();
        if(test1==null){
            while (it.hasNext()){
                Kart test=it.next();
                if(test.getNumber()==-1||test.getNumber()==-2){
                    test1=test;
                    removeCart(test);
                }
            }
        }
        return test1;
    }

    @Override
    public ArrayList<Kart> getCarts() {
        return super.getCarts();
    }
}
