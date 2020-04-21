import javax.swing.text.html.HTMLDocument;
import java.awt.event.KeyAdapter;
import java.util.*;

public class Uno {
    private static ArrayList<Kart> bank;
    private static ArrayList<Kart> save;
    private static CircularArrayList<Player> players;

    private static void firstKart() {
        bank.add(new Kart(0, 'r'));
        for (int i = 0; i < 2; i++) {
            bank.add(new Kart(10, 'r'));
            bank.add(new Kart(11, 'r'));
            bank.add(new Kart(12, 'r'));
        }
        for (int i = 1; i <= 9; i++) {
            bank.add(new Kart(i, 'r'));
            bank.add(new Kart(i, 'r'));
        }
        for (int i = 0; i < 2; i++) {
            bank.add(new Kart(10, 'b'));
            bank.add(new Kart(11, 'b'));
            bank.add(new Kart(12, 'b'));
        }
        bank.add(new Kart(0, 'b'));
        for (int i = 1; i <= 9; i++) {
            bank.add(new Kart(i, 'b'));
            bank.add(new Kart(i, 'b'));
        }
        for (int i = 0; i < 2; i++) {
            bank.add(new Kart(10, 'g'));
            bank.add(new Kart(11, 'g'));
            bank.add(new Kart(12, 'g'));
        }
        bank.add(new Kart(0, 'g'));
        for (int i = 1; i <= 9; i++) {
            bank.add(new Kart(i, 'g'));
            bank.add(new Kart(i, 'g'));
        }
        for (int i = 0; i < 2; i++) {
            bank.add(new Kart(10, 'y'));
            bank.add(new Kart(11, 'y'));
            bank.add(new Kart(12, 'y'));
        }
        bank.add(new Kart(0, 'y'));
        for (int i = 1; i <= 9; i++) {
            bank.add(new Kart(i, 'y'));
            bank.add(new Kart(i, 'y'));
        }
        for (int i = 0; i < 4; i++) {
            bank.add(new Kart(-1, 'l'));
            bank.add(new Kart(-2, 'l'));
        }
    }

    private static void bor() {
        Random random = new Random();
        Iterator<Player> it = players.iterator();
        while (it.hasNext()) {
            Player test = it.next();
            for (int i = 0; i < 7; i++) {
                int x = random.nextInt(bank.size());
                Kart test1 = bank.get(x);
                test.addCart(test1);
                bank.remove(test1);
            }
        }
    }

    private static boolean endgame() {
        Iterator<Player> it = players.iterator();
        while (it.hasNext()) {
            if (it.next().getCarts().size() == 0) {
                System.out.println("winner is " + it.next().name);
                return true;
            }
        }
        return false;
    }

    private static void point() {
        Iterator<Player> it = players.iterator();
        int sum = 0;
        while (it.hasNext()) {
            sum = 0;
            Player test = it.next();
            for (Kart k : test.getCarts()
            ) {
                sum += k.getPoint();
            }
            System.out.println(test.getName() + " point : " + sum);
        }
    }

    public static void main(String[] args) {
        bank = new ArrayList<>();
        save = new ArrayList<>();
        players = new CircularArrayList<>();
        int jahat = 0;//0 clock wise 1 anticlock wise
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        System.out.println("1) single player\n2)multi player");
        int type=scanner.nextInt();
        if(type==1){
        int save1 = 0;
        int counter = 0;
        System.out.println("Enter player number");
        int number = scanner.nextInt();
        players.add(new Player("M"));
        for (int i = 0; i < number - 1; i++) {
            players.add(new PlayerBot("s" + i));
        }
        firstKart();
        bor();
        Kart vasat = bank.get(random.nextInt(bank.size()));
        while (vasat.getColor() == 'l') {
            vasat = bank.get(random.nextInt(bank.size()));
        }
        while (true) {
            int i = save1;
            if (jahat == 0) {
                while (i >= 0) {
                    System.out.println("clock wise");
                    System.out.println("board");
                    vasat.show();
                    if (players.get(i).getName().equals("M")) {
                        System.out.println("all cards of " + players.get(i).getName());
                        players.get(i).showAll();
                        System.out.println("enter cart: first number then color and 'l' for wilds and  10 for skip 11" +
                                " for reverse 12 for draw -1 for wild draw and -2 for wild ");
                        Kart test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                        while (!players.get(i).carts.contains(test)) {
                            System.out.println("wrong input");
                            test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                        }
                        Kart play = players.get(i).rplay(vasat, test.getColor(), test.getNumber());
                        if (play == null || ( play.getNumber() != 12 && play.getNumber() != -1 )) {
                            for (Kart k : save) {
                                System.out.println("picked up");
                                players.get(i).addCart(k);
                                k.show();
                                counter = 1;
                            }
                            if (counter == 1) {
                                System.out.println("all cards of " + players.get(i).getName());
                                System.out.println("board");
                                vasat.show();
                                players.get(i).showAll();
                                System.out.println("enter cart: first number then color  and 'l' for wilds and  10 for skip 11" +
                                        " for reverse 12 for draw -1 for wild draw and -2 for wild ");
                                test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                                while (!players.get(i).carts.contains(test)) {
                                    System.out.println("wrong input");
                                    test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                                }
                                play = players.get(i).rplay(vasat, test.getColor(), test.getNumber());
                            }
                            save = new ArrayList<>();
                        }
                        if (play == null && counter == 0) {
                            Kart check = bank.get(random.nextInt(bank.size()));
                            System.out.println("picked up");
                            check.show();
                            bank.remove(check);
                            players.get(i).addCart(check);
                            System.out.println("board");
                            vasat.show();
                            System.out.println("all cards of " + players.get(i).getName());
                            players.get(i).showAll();
                            System.out.println("enter cart: first number then color  and 'l' for wilds and  10 for skip 11" +
                                    " for reverse 12 for draw -1 for wild draw and -2 for wild ");
                            test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                            while (!players.get(i).carts.contains(test)) {
                                System.out.println("wrong input");
                                test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                            }
                            play = players.get(i).rplay(vasat, test.getColor(), test.getNumber());
                        }
                        counter = 0;
                        if (play == null) {
                            System.out.println("can not move ");
                        } else {
                            vasat = play;
                            System.out.println("played");
                            play.show();
                            players.get(i).removeCart(play);
                            bank.add(play);
                            if (endgame()) {
                                return;
                            }
                            if (play.getNumber() == 10) {
                                i += 2;

                                continue;
                            }
                            if (play.getNumber() == 11) {
                                jahat = 1;
                                save1 = --i;
                                break;
                            }
                            if (play.getNumber() == 12) {
                                Kart check = bank.get(random.nextInt(bank.size()));
                                Kart check1 = bank.get(random.nextInt(bank.size()));
                                bank.remove(check);
                                bank.remove(check1);
                                save.add(check);
                                save.add(check1);
                            }
                            if (play.getNumber() == -2) {
                                System.out.println("enter new color ");
                                char color = scanner.next().charAt(0);
                                vasat.setColor(color);
                            }
                            if (play.getNumber() == -1) {
                                for (int j = 0; j < 4; j++) {
                                    Kart check = bank.get(random.nextInt(bank.size()));
                                    bank.remove(check);
                                    save.add(check);
                                }
                                System.out.println("enter new color ");
                                char color = scanner.next().charAt(0);
                                vasat.setColor(color);
                            }
                        }
                    } else {
                        System.out.println("all cards of " + players.get(i).getName());
                        players.get(i).showAll();
                        Kart play = players.get(i).play(vasat);
                        if (play == null || ( play.getNumber() != 12 && play.getNumber() != -1 )) {
                            for (Kart k : save) {
                                System.out.println("picked up");
                                players.get(i).addCart(k);
                                k.show();
                                counter = 1;
                            }
                            if (counter == 1) {
                                System.out.println("board");
                                vasat.show();
                                System.out.println("all cards of " + players.get(i).getName());
                                players.get(i).showAll();
                                play = players.get(i).play(vasat);
                            }
                            save = new ArrayList<>();
                        }
                        if (play == null && counter == 0) {
                            Kart check = bank.get(random.nextInt(bank.size()));
                            System.out.println("picked up");
                            check.show();
                            bank.remove(check);
                            players.get(i).addCart(check);
                            System.out.println("board");
                            vasat.show();
                            System.out.println("all cards of " + players.get(i).getName());
                            players.get(i).showAll();
                            play = players.get(i).play(vasat);
                        }
                        counter = 0;
                        if (play == null) {
                            System.out.println("can not move ");
                        } else {
                            vasat = play;
                            System.out.println("played");
                            play.show();
                            players.get(i).removeCart(play);
                            if (endgame()) {
                                return;
                            }
                            bank.add(play);
                            if (play.getNumber() == 10) {
                                i += 2;
                                continue;
                            }
                            if (play.getNumber() == 11) {
                                jahat = 1;
                                save1 = --i;
                                break;
                            }
                            if (play.getNumber() == 12) {
                                Kart check = bank.get(random.nextInt(bank.size()));
                                Kart check1 = bank.get(random.nextInt(bank.size()));
                                bank.remove(check);
                                bank.remove(check1);
                                save.add(check);
                                save.add(check1);
                            }
                            if (play.getNumber() == -2) {
                                int x = random.nextInt(4);
                                char color = 'r';
                                switch (x) {
                                    case 1:
                                        color = 'b';
                                        break;
                                    case 2:
                                        color = 'r';
                                        break;
                                    case 3:
                                        color = 'y';
                                        break;
                                    case 4:
                                        color = 'g';
                                        break;
                                }
                                vasat.setColor(color);
                            }
                            if (play.getNumber() == -1) {
                                for (int j = 0; j < 4; j++) {
                                    Kart check = bank.get(random.nextInt(bank.size()));
                                    bank.remove(check);
                                    save.add(check);
                                }
                                int x = random.nextInt(4);
                                char color = 'r';
                                switch (x) {
                                    case 1:
                                        color = 'b';
                                        break;
                                    case 2:
                                        color = 'r';
                                        break;
                                    case 3:
                                        color = 'y';
                                        break;
                                    case 4:
                                        color = 'g';
                                        break;
                                }
                                vasat.setColor(color);
                            }
                        }
                    }
                    i++;
                    point();
                }
            } else if (jahat == 1) {
                while (i >= 0) {
                    System.out.println("Anti clock wise");
                    System.out.println("board");
                    vasat.show();
                    if (players.get(i).getName().equals("M")) {
                        System.out.println("all cards of " + players.get(i).getName());
                        players.get(i).showAll();
                        System.out.println("enter cart: first number then color  and 'l' for wilds and  10 for skip 11" +
                                " for reverse 12 for draw -1 for wild draw and -2 for wild ");
                        Kart test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                        while (!players.get(i).carts.contains(test)) {
                            System.out.println("wrong input");
                            test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                        }
                        Kart play = players.get(i).rplay(vasat, test.getColor(), test.getNumber());
                        if (play == null || ( play.getNumber() != 12 && play.getNumber() != -1 )) {
                            for (Kart k : save) {
                                System.out.println("picked up");
                                players.get(i).addCart(k);
                                k.show();
                                counter = 1;
                            }
                            if (counter == 1) {
                                System.out.println("board");
                                vasat.show();
                                System.out.println("all cards of " + players.get(i).getName());
                                players.get(i).showAll();
                                System.out.println("enter cart:fist number then color  'l' for wilds and  10 for skip 11" +
                                        " for reverse 12 for draw -1 for wild draw and -2 for wild ");
                                test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                                while (!players.get(i).carts.contains(test)) {
                                    System.out.println("wrong input");
                                    test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                                }
                                play = players.get(i).rplay(vasat, test.getColor(), test.getNumber());
                            }
                            save = new ArrayList<>();
                        }
                        if (play == null && counter == 0) {
                            Kart check = bank.get(random.nextInt(bank.size()));
                            System.out.println("picked up");
                            check.show();
                            bank.remove(check);
                            players.get(i).addCart(check);
                            System.out.println("board");
                            vasat.show();
                            System.out.println("all cards of " + players.get(i).getName());
                            players.get(i).showAll();
                            System.out.println("enter cart: first number then color  and 'l' for wilds and  10 for skip 11" +
                                    " for reverse 12 for draw -1 for wild draw and -2 for wild ");
                            test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                            while (!players.get(i).carts.contains(test)) {
                                System.out.println("wrong input");
                                test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                            }
                            play = players.get(i).rplay(vasat, test.getColor(), test.getNumber());
                        }
                        counter = 0;
                        if (play == null) {
                            System.out.println("can not move ");
                        } else {
                            vasat = play;
                            System.out.println("played");
                            play.show();
                            players.get(i).removeCart(play);
                            if (endgame()) {
                                return;
                            }
                            bank.add(play);
                            if (play.getNumber() == 10) {
                                i -= 2;
                                continue;
                            }
                            if (play.getNumber() == 11) {
                                jahat = 0;
                                save1 = ++i;
                                break;
                            }
                            if (play.getNumber() == 12) {
                                Kart check = bank.get(random.nextInt(bank.size()));
                                Kart check1 = bank.get(random.nextInt(bank.size()));
                                bank.remove(check);
                                bank.remove(check1);
                                save.add(check);
                                save.add(check1);
                            }
                            if (play.getNumber() == -2) {
                                System.out.println("enter new color ");
                                char color = scanner.next().charAt(0);
                                vasat.setColor(color);
                            }
                            if (play.getNumber() == -1) {
                                for (int j = 0; j < 4; j++) {
                                    Kart check = bank.get(random.nextInt(bank.size()));
                                    bank.remove(check);
                                    save.add(check);
                                }
                                System.out.println("enter new color ");
                                char color = scanner.next().charAt(0);
                                vasat.setColor(color);
                            }
                        }
                    } else {
                        System.out.println("all cards of " + players.get(i).getName());
                        players.get(i).showAll();
                        Kart play = players.get(i).play(vasat);
                        if (play == null || ( play.getNumber() != 12 && play.getNumber() != -1 )) {
                            for (Kart k : save) {
                                System.out.println("picked up");
                                players.get(i).addCart(k);
                                k.show();
                                counter = 1;
                            }
                            if (counter == 1) {
                                System.out.println("board");
                                vasat.show();
                                System.out.println("all cards of " + players.get(i).getName());
                                players.get(i).showAll();
                                play = players.get(i).play(vasat);
                            }
                            save = new ArrayList<>();
                        }
                        if (play == null && counter == 0) {
                            Kart check = bank.get(random.nextInt(bank.size()));
                            System.out.println("picked up");
                            check.show();
                            bank.remove(check);
                            players.get(i).addCart(check);
                            System.out.println("board");
                            vasat.show();
                            System.out.println("all cards of " + players.get(i).getName());
                            players.get(i).showAll();
                            play = players.get(i).play(vasat);
                        }
                        counter = 0;
                        if (play == null) {
                            System.out.println("can not move ");
                        } else {
                            vasat = play;
                            System.out.println("played");
                            play.show();
                            players.get(i).removeCart(play);
                            if (endgame()) {
                                return;
                            }
                            bank.add(play);
                            if (play.getNumber() == 10) {
                                i -= 2;
                                continue;
                            }
                            if (play.getNumber() == 11) {
                                jahat = 0;
                                save1 = ++i;
                                break;
                            }
                            if (play.getNumber() == 12) {
                                Kart check = bank.get(random.nextInt(bank.size()));
                                Kart check1 = bank.get(random.nextInt(bank.size()));
                                bank.remove(check);
                                bank.remove(check1);
                                save.add(check);
                                save.add(check1);
                            }
                            if (play.getNumber() == -2) {
                                int x = random.nextInt(4);
                                char color = 'y';
                                switch (x) {
                                    case 1:
                                        color = 'b';
                                        break;
                                    case 2:
                                        color = 'r';
                                        break;
                                    case 3:
                                        color = 'y';
                                        break;
                                    case 4:
                                        color = 'g';
                                        break;
                                }
                                vasat.setColor(color);
                            }
                            if (play.getNumber() == -1) {
                                for (int j = 0; j < 4; j++) {
                                    Kart check = bank.get(random.nextInt(bank.size()));
                                    bank.remove(check);
                                    save.add(check);
                                }
                                int x = random.nextInt(4);
                                char color = 'r';
                                switch (x) {
                                    case 1:
                                        color = 'b';
                                        break;
                                    case 2:
                                        color = 'r';
                                        break;
                                    case 3:
                                        color = 'y';
                                        break;
                                    case 4:
                                        color = 'g';
                                        break;
                                }
                                vasat.setColor(color);
                            }
                        }
                    }
                    i--;
                    point();
                }
            }
        }
    }
    else {
            int save1 = 0;
            int counter = 0;
            System.out.println("Enter player number");
            int number = scanner.nextInt();
            players.add(new Player("M"));
            for (int i = 0; i < number - 1; i++) {
                System.out.println("enter player names");
                players.add(new Player( scanner.next()));
            }
            firstKart();
            bor();
            Kart vasat = bank.get(random.nextInt(bank.size()));
            while (vasat.getColor() == 'l') {
                vasat = bank.get(random.nextInt(bank.size()));
            }
            while (true) {
                int i = save1;
                if (jahat == 0) {
                    while (i >= 0) {
                        System.out.println("clock wise");
                        System.out.println("board");
                        vasat.show();
                            System.out.println("all cards of " + players.get(i).getName());
                            players.get(i).showAll();
                            System.out.println("enter cart: first number then color and 'l' for wilds and  10 for skip 11" +
                                    " for reverse 12 for draw -1 for wild draw and -2 for wild ");
                            Kart test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                            while (!players.get(i).carts.contains(test)) {
                                System.out.println("wrong input");
                                test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                            }
                            Kart play = players.get(i).rplay(vasat, test.getColor(), test.getNumber());
                            if (play == null || ( play.getNumber() != 12 && play.getNumber() != -1 )) {
                                for (Kart k : save) {
                                    System.out.println("picked up");
                                    players.get(i).addCart(k);
                                    k.show();
                                    counter = 1;
                                }
                                if (counter == 1) {
                                    System.out.println("board");
                                    vasat.show();
                                    System.out.println("all cards of " + players.get(i).getName());
                                    players.get(i).showAll();
                                    System.out.println("enter cart: first number then color  and 'l' for wilds and  10 for skip 11" +
                                            " for reverse 12 for draw -1 for wild draw and -2 for wild ");
                                    test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                                    while (!players.get(i).carts.contains(test)) {
                                        System.out.println("wrong input");
                                        test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                                    }
                                    play = players.get(i).rplay(vasat, test.getColor(), test.getNumber());
                                }
                                save = new ArrayList<>();
                            }
                            if (play == null && counter == 0) {
                                Kart check = bank.get(random.nextInt(bank.size()));
                                System.out.println("picked up");
                                check.show();
                                bank.remove(check);
                                players.get(i).addCart(check);
                                System.out.println("board");
                                vasat.show();
                                System.out.println("all cards of " + players.get(i).getName());
                                players.get(i).showAll();
                                System.out.println("enter cart: first number then color  and 'l' for wilds and  10 for skip 11" +
                                        " for reverse 12 for draw -1 for wild draw and -2 for wild ");
                                test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                                while (!players.get(i).carts.contains(test)) {
                                    System.out.println("wrong input");
                                    test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                                }
                                play = players.get(i).rplay(vasat, test.getColor(), test.getNumber());
                            }
                            counter = 0;
                            if (play == null) {
                                System.out.println("can not move ");
                            } else {
                                vasat = play;
                                System.out.println("played");
                                play.show();
                                players.get(i).removeCart(play);
                                bank.add(play);
                                if (endgame()) {
                                    return;
                                }
                                if (play.getNumber() == 10) {
                                    i += 2;

                                    continue;
                                }
                                if (play.getNumber() == 11) {
                                    jahat = 1;
                                    save1 = --i;
                                    break;
                                }
                                if (play.getNumber() == 12) {
                                    Kart check = bank.get(random.nextInt(bank.size()));
                                    Kart check1 = bank.get(random.nextInt(bank.size()));
                                    bank.remove(check);
                                    bank.remove(check1);
                                    save.add(check);
                                    save.add(check1);
                                }
                                if (play.getNumber() == -2) {
                                    System.out.println("enter new color ");
                                    char color = scanner.next().charAt(0);
                                    vasat.setColor(color);
                                }
                                if (play.getNumber() == -1) {
                                    for (int j = 0; j < 4; j++) {
                                        Kart check = bank.get(random.nextInt(bank.size()));
                                        bank.remove(check);
                                        save.add(check);
                                    }
                                    System.out.println("enter new color ");
                                    char color = scanner.next().charAt(0);
                                    vasat.setColor(color);
                                }
                            }

                        i++;
                        point();
                    }
                } else if (jahat == 1) {
                    while (i >= 0) {
                        System.out.println("Anti clock wise");
                        System.out.println("board");
                        vasat.show();

                            System.out.println("all cards of " + players.get(i).getName());
                            players.get(i).showAll();
                            System.out.println("enter cart: first number then color  and 'l' for wilds and  10 for skip 11" +
                                    " for reverse 12 for draw -1 for wild draw and -2 for wild ");
                            Kart test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                            while (!players.get(i).carts.contains(test)) {
                                System.out.println("wrong input");
                                test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                            }
                            Kart play = players.get(i).rplay(vasat, test.getColor(), test.getNumber());
                            if (play == null || ( play.getNumber() != 12 && play.getNumber() != -1 )) {
                                for (Kart k : save) {
                                    System.out.println("picked up");
                                    players.get(i).addCart(k);
                                    k.show();
                                    counter = 1;
                                }
                                if (counter == 1) {
                                    System.out.println("board");
                                    vasat.show();
                                    System.out.println("all cards of " + players.get(i).getName());
                                    players.get(i).showAll();
                                    System.out.println("enter cart:fist number then color  'l' for wilds and  10 for skip 11" +
                                            " for reverse 12 for draw -1 for wild draw and -2 for wild ");
                                    test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                                    while (!players.get(i).carts.contains(test)) {
                                        System.out.println("wrong input");
                                        test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                                    }
                                    play = players.get(i).rplay(vasat, test.getColor(), test.getNumber());
                                }
                                save = new ArrayList<>();
                            }
                            if (play == null && counter == 0) {
                                Kart check = bank.get(random.nextInt(bank.size()));
                                System.out.println("picked up");
                                check.show();
                                bank.remove(check);
                                players.get(i).addCart(check);
                                System.out.println("board");
                                vasat.show();
                                System.out.println("all cards of " + players.get(i).getName());
                                players.get(i).showAll();
                                System.out.println("enter cart: first number then color  and 'l' for wilds and  10 for skip 11" +
                                        " for reverse 12 for draw -1 for wild draw and -2 for wild ");
                                test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                                while (!players.get(i).carts.contains(test)) {
                                    System.out.println("wrong input");
                                    test = new Kart(scanner.nextInt(), scanner.next().charAt(0));
                                }
                                play = players.get(i).rplay(vasat, test.getColor(), test.getNumber());
                            }
                            counter = 0;
                            if (play == null) {
                                System.out.println("can not move ");
                            } else {
                                vasat = play;
                                System.out.println("played");
                                play.show();
                                players.get(i).removeCart(play);
                                if (endgame()) {
                                    return;
                                }
                                bank.add(play);
                                if (play.getNumber() == 10) {
                                    i -= 2;
                                    continue;
                                }
                                if (play.getNumber() == 11) {
                                    jahat = 0;
                                    save1 = ++i;
                                    break;
                                }
                                if (play.getNumber() == 12) {
                                    Kart check = bank.get(random.nextInt(bank.size()));
                                    Kart check1 = bank.get(random.nextInt(bank.size()));
                                    bank.remove(check);
                                    bank.remove(check1);
                                    save.add(check);
                                    save.add(check1);
                                }
                                if (play.getNumber() == -2) {
                                    System.out.println("enter new color ");
                                    char color = scanner.next().charAt(0);
                                    vasat.setColor(color);
                                }
                                if (play.getNumber() == -1) {
                                    for (int j = 0; j < 4; j++) {
                                        Kart check = bank.get(random.nextInt(bank.size()));
                                        bank.remove(check);
                                        save.add(check);
                                    }
                                    System.out.println("enter new color ");
                                    char color = scanner.next().charAt(0);
                                    vasat.setColor(color);
                                }
                            }
                        i--;
                        point();
                    }
                }
            }

        }
    }
}
