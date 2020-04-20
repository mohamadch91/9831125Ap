import java.util.ArrayList;

public class Player {
    protected String name;
    protected ArrayList<Kart> carts;
    public Player(String name1){
        carts=new ArrayList<>();
        name=name1;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                '}';
    }
    public void addCart(Kart cart){
        carts.add(cart);
    }
    public void removeCart(Kart cart){
        carts.remove(cart);
    }

    public ArrayList<Kart> getCarts() {
        return carts;
    }
    public Kart play(Kart cart){
        return cart;

    }

}
