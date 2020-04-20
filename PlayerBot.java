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
        Kart test=null;
        Iterator<Kart> it=carts.iterator();
        while (it.hasNext()){
            if(cart.getColor()==it.next().getColor()||cart.getNumber()==it.next().getNumber()){
                test=it.next();
                removeCart(it.next());
            }
        }
        it=carts.iterator();
        if(test==null){
            while (it.hasNext()){
                if(it.next().getNumber()==-1){
                    test=it.next();
                    removeCart(it.next());
                }
            }
        }
        return test;
    }
}
