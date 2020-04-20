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
    public Kart rplay(Kart cart,char color,int number){
        Kart test=null;
        if(cart.getColor()==color||cart.getNumber()==number){
            test=new Kart(number, color);
            removeCart(test);
        }
        else if(color=='b'){
            test=new Kart(number, color);
        }
        return test;
    }
    public void showAll(){
        for (int i = 0; i <carts.size() ; i++) {
            carts.get(i).show();
        }
    }

    public void setCarts(ArrayList<Kart> carts) {
        this.carts = carts;
    }


    public String getName() {
        return name;
    }
}
