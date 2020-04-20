import javax.swing.text.html.HTMLDocument;
import java.awt.event.KeyAdapter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Random;

public class Uno {
    private ArrayList<Kart> bank;
    private LinkedList<Player> players;

    public Uno(int number) {
        players.add(new Player("M"));
        for (int i = 0; i < number - 1; i++) {
            players.add(new Player("s" + i));
        }
        firstKart();
        bor();
    }

    private void firstKart() {
        bank.add(new Kart(0, 'r'));
        for (int i = 1; i <= 9; i++) {
            bank.add(new Kart(i, 'r'));
            bank.add(new Kart(i, 'r'));
        }
        bank.add(new Kart(0, 'b'));
        for (int i = 1; i <= 9; i++) {
            bank.add(new Kart(i, 'b'));
            bank.add(new Kart(i, 'b'));
        }
        bank.add(new Kart(0, 'g'));
        for (int i = 1; i <= 9; i++) {
            bank.add(new Kart(i, 'g'));
            bank.add(new Kart(i, 'g'));
        }
        bank.add(new Kart(0, 'y'));
        for (int i = 1; i <= 9; i++) {
            bank.add(new Kart(i, 'y'));
            bank.add(new Kart(i, 'y'));
        }
    }
    private void bor(){
        Random random=new Random();
        Iterator<Player> it=players.iterator();
        while (it.hasNext()){
            for (int i = 0; i <7 ; i++) {
                int x = random.nextInt(bank.size());
                Kart test = bank.get(x);
                it.next().addCart(test);
                bank.remove(test);
            }
        }
    }
}
