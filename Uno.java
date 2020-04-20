import javax.swing.text.html.HTMLDocument;
import java.awt.event.KeyAdapter;
import java.util.*;

public class Uno {
    private static ArrayList<Kart> bank;
    private static ArrayList<Kart> save;
    private static CircularArrayList<Player> players;
    private  static void firstKart() {
        bank.add(new Kart(0, 'r'));
        for (int i = 0; i <2 ; i++) {
            bank.add(new Kart(10,'r'));
            bank.add(new Kart(11,'r'));
            bank.add(new Kart(12,'r'));
        }
        for (int i = 1; i <= 9; i++) {
            bank.add(new Kart(i, 'r'));
            bank.add(new Kart(i, 'r'));
        }
        for (int i = 0; i <2 ; i++) {
            bank.add(new Kart(10,'b'));
            bank.add(new Kart(11,'b'));
            bank.add(new Kart(12,'b'));
        }
        bank.add(new Kart(0, 'b'));
        for (int i = 1; i <= 9; i++) {
            bank.add(new Kart(i, 'b'));
            bank.add(new Kart(i, 'b'));
        }
        for (int i = 0; i <2 ; i++) {
            bank.add(new Kart(10,'g'));
            bank.add(new Kart(11,'g'));
            bank.add(new Kart(12,'g'));
        }
        bank.add(new Kart(0, 'g'));
        for (int i = 1; i <= 9; i++) {
            bank.add(new Kart(i, 'g'));
            bank.add(new Kart(i, 'g'));
        }
        for (int i = 0; i <2 ; i++) {
            bank.add(new Kart(10,'y'));
            bank.add(new Kart(11,'y'));
            bank.add(new Kart(12,'y'));
        }
        bank.add(new Kart(0, 'y'));
        for (int i = 1; i <= 9; i++) {
            bank.add(new Kart(i, 'y'));
            bank.add(new Kart(i, 'y'));
        }
        for (int i = 0; i <4 ; i++) {
            bank.add(new Kart(-1,'l'));
            bank.add(new Kart(-2,'l'));
        }
    }
    private static void bor(){
        Random random=new Random();
        Iterator<Player> it=players.iterator();
        while (it.hasNext()){
            Player test=it.next();
            for (int i = 0; i <7 ; i++) {
                int x = random.nextInt(bank.size());
                Kart test1 = bank.get(x);
                test.addCart(test1);
                bank.remove(test1);
            }
        }
    }
    private static boolean endgame(){
        Iterator<Player> it=players.iterator();
        while (it.hasNext()){
            if(it.next().getCarts().size()==0){
                System.out.println("winner is "+it.next().name);
                return true ;}}
        return false;
    }
private static void point(){
        Iterator<Player> it=players.iterator();
        int sum=0;
        while (it.hasNext()){
            sum=0;
            Player test=it.next();
            for (Kart k:test.getCarts()
                 ) {
                sum+=k.getPoint();
            }
            System.out.println(it.next().getName()+" point : "+sum);
        }
}
    public static void main(String[] args) {
        bank=new ArrayList<>();
        save=new ArrayList<>();
        players=new CircularArrayList<>();
        int jahat=0;//0 clock wise 1 anticlock wise
        Scanner scanner =new Scanner(System.in);
        Random random=new Random();
        int save1=0;
//        int counter=0;
//        Kart save1=null;
        System.out.println("Enter player number");
        int number=scanner.nextInt();
        players.add(new Player("M"));
        for (int i = 0; i < number - 1; i++) {
            players.add(new PlayerBot("s" + i));
        }
        firstKart();
        bor();

        while (true){
            Kart vasat=bank.get(random.nextInt(bank.size()));
            int i=save1;
            if(jahat==0){
                System.out.println("clock wise");
                while (i>=0){
                    System.out.println("board");
                    vasat.show();
                    if(players.get(i).getName().equals("M")){
                        System.out.println("all cards");
                        players.get(i).showAll();
                        System.out.println("enter cart");
                        Kart play=players.get(i).rplay(vasat,scanner.next().charAt(0),scanner.nextInt());
                        if(play==null||(play.getNumber()!=12&&play.getNumber()!=-1)){
                            for (Kart k:save) {
                                players.get(i).addCart(k);
                            }
                            save=new ArrayList<>();
                        }
                        if(play==null){
                            Kart check=bank.get(random.nextInt(bank.size()));
                            System.out.println("picked up");
                            check.show();
                            bank.remove(check);
                            players.get(i).addCart(check);
                        }
                        else{
                            vasat=play;
                            System.out.println("played");
                            play.show();
                            players.get(i).removeCart(play);
                            bank.add(play);
                            if(endgame()){
                                return;
                            }
                            if(play.getNumber()==10){
                                i++;
                                continue;
                            }
                            if(play.getNumber()==11){
                                jahat=1;
                                save1=i;
                                break;
                            }
                            if(play.getNumber()==12){
                                Kart check=bank.get(random.nextInt(bank.size()));
                                Kart check1=bank.get(random.nextInt(bank.size()));
                                check.show();
                                check.show();
                                bank.remove(check);
                                bank.remove(check1);
                                save.add(check);
                                save.add(check1);
                            }
                            if(play.getNumber()==-2){
                                System.out.println("enter new color ");
                                char color=scanner.next().charAt(0);
                                vasat.setColor(color);
                            }
                            if(play.getNumber()==-1){
                                for (int j = 0; j <4 ; j++) {
                                    Kart check=bank.get(random.nextInt(bank.size()));
                                    check.show();
                                    bank.remove(check);
                                    save.add(check);
                                }
                            }
                        }
                    }
                    else{
//                        players.get(i).showAll();
                        Kart play=players.get(i).play(vasat);
                        if(play==null||(play.getNumber()!=12&&play.getNumber()!=-1)){
                            for (Kart k:save) {
                                players.get(i).addCart(k);
                            }
                            save=new ArrayList<>();
                        }
                        if(play==null){
                            Kart check=bank.get(random.nextInt(bank.size()));
                            System.out.println("picked up");
                            check.show();
                            bank.remove(check);
                            players.get(i).addCart(check);
                        }
                        else{
                            vasat=play;
                            System.out.println("played");
                            play.show();
                            players.get(i).removeCart(play);
                            if(endgame()){
                                return;
                            }
                            bank.add(play);
                            if(play.getNumber()==10){
                                i++;
                                continue;
                            }
                            if(play.getNumber()==11){
                                jahat=1;
                                save1=i;
                                break;
                            }
                            if(play.getNumber()==12){
                                Kart check=bank.get(random.nextInt(bank.size()));
                                Kart check1=bank.get(random.nextInt(bank.size()));
                                check.show();
                                check.show();
                                bank.remove(check);
                                bank.remove(check1);
                                save.add(check);
                                save.add(check1);
                            }
                            if(play.getNumber()==-2){
                                System.out.println("enter new color ");
                                char color=scanner.next().charAt(0);
                                vasat.setColor(color);
                            }
                            if(play.getNumber()==-1){
                                for (int j = 0; j <4 ; j++) {
                                    Kart check=bank.get(random.nextInt(bank.size()));
                                    check.show();
                                    bank.remove(check);
                                    save.add(check);
                                }
                            }
                        }

                    }
//                    save1=play;
                    i++;
                    point();
                }

            }
            else if(jahat==1){
                System.out.println("Anti clock wise");
                while (i>=0){
                    System.out.println("board");
                    vasat.show();
                    if(players.get(i).getName().equals("M")){
                        System.out.println("all cards");
                        players.get(i).showAll();
                        System.out.println("enter cart");
                        Kart play=players.get(i).rplay(vasat,scanner.next().charAt(0),scanner.nextInt());
                        if(play==null||(play.getNumber()!=12&&play.getNumber()!=-1)){
                            for (Kart k:save) {
                                players.get(i).addCart(k);
                            }
                            save=new ArrayList<>();
                        }
                        if(play==null){
                            Kart check=bank.get(random.nextInt(bank.size()));
                            System.out.println("picked up");
                            check.show();
                            bank.remove(check);
                            players.get(i).addCart(check);
                        }
                        else{
                            vasat=play;
                            System.out.println("played");
                            play.show();
                            players.get(i).removeCart(play);
                            if(endgame()){
                                return;
                            }
                            bank.add(play);
                            if(play.getNumber()==10){
                                i++;
                                continue;
                            }
                            if(play.getNumber()==11){
                                jahat=1;
                                save1=i;
                                break;
                            }
                            if(play.getNumber()==12){
                                Kart check=bank.get(random.nextInt(bank.size()));
                                Kart check1=bank.get(random.nextInt(bank.size()));
                                check.show();
                                check.show();
                                bank.remove(check);
                                bank.remove(check1);
                                save.add(check);
                                save.add(check1);
                            }
                            if(play.getNumber()==-2){
                                System.out.println("enter new color ");
                                char color=scanner.next().charAt(0);
                                vasat.setColor(color);
                            }
                            if(play.getNumber()==-1){
                                for (int j = 0; j <4 ; j++) {
                                    Kart check=bank.get(random.nextInt(bank.size()));
                                    check.show();
                                    bank.remove(check);
                                    save.add(check);
                                }
                            }
                        }
                    }
                    else{
//                        players.get(i).showAll();
                        Kart play=players.get(i).play(vasat);
                        if(play==null||(play.getNumber()!=12&&play.getNumber()!=-1)){
                            for (Kart k:save) {
                                players.get(i).addCart(k);
                            }
                            save=new ArrayList<>();
                        }
                        if(play==null){
                            Kart check=bank.get(random.nextInt(bank.size()));
                            System.out.println("picked up");
                            check.show();
                            bank.remove(check);
                            players.get(i).addCart(check);
                        }
                        else{
                            vasat=play;
                            System.out.println("played");
                            play.show();
                            players.get(i).removeCart(play);
                            if(endgame()){
                                return;
                            }
                            bank.add(play);
                            if(play.getNumber()==10){
                                i++;
                                continue;
                            }
                            if(play.getNumber()==11){
                                jahat=1;
                                save1=i;
                                break;
                            }
                            if(play.getNumber()==12){
                                Kart check=bank.get(random.nextInt(bank.size()));
                                Kart check1=bank.get(random.nextInt(bank.size()));
                                check.show();
                                check.show();
                                bank.remove(check);
                                bank.remove(check1);
                                save.add(check);
                                save.add(check1);
                            }
                            if(play.getNumber()==-2){
                                System.out.println("enter new color ");
                                char color=scanner.next().charAt(0);
                                vasat.setColor(color);
                            }
                            if(play.getNumber()==-1){
                                for (int j = 0; j <4 ; j++) {
                                    Kart check=bank.get(random.nextInt(bank.size()));
                                    check.show();
                                    bank.remove(check);
                                    save.add(check);
                                }
                            }
                        }

                    }
//                    save1=play;
                    i--;
                    point();
                }

            }

        }

    }
}
