import java.util.Scanner;

public class Pentago {
    public static void main(String[] args) {
        allMap pentago=new allMap(3);
        pentago.first();
        Scanner scanner=new Scanner(System.in);
        pentago.show();
        while (pentago.gameover()){
            System.out.println("Red player turn");
            int table=scanner.nextInt();
            int number=scanner.nextInt();
            int table1;
            int charkhesh;
            while (!pentago.validPut(table,number)){
                System.out.println("invalid input");
                table=scanner.nextInt();
                number=scanner.nextInt();
            }
            pentago.putall(table,number,'r');
            pentago.show();
            if (pentago.win('r')){
                System.out.println("Red is winner");
                break;}
            check(pentago, scanner);
            if (pentago.win('r')){
                System.out.println("Red is winner");
                break;}
            System.out.println("Black player turn");
            table=scanner.nextInt();
            number=scanner.nextInt();
            while (!pentago.validPut(table,number)){
                System.out.println("invalid input");
                table=scanner.nextInt();
                number=scanner.nextInt();
            }
            pentago.putall(table,number,'b');
            pentago.show();
            if (pentago.win('b')){
                System.out.println("Black is winner");
                break;}
            check(pentago, scanner);
            if (pentago.win('b')){
                System.out.println("Black is winner");
                break;}
        }
    }

    private static void check(allMap pentago, Scanner scanner) {
        int table1;
        int charkhesh;
        if(pentago.turnCheck(1)||pentago.turnCheck(2)){
            System.out.println("Do yo want to turn 1:Yes 2: NO");
            int check=scanner.nextInt();
            if(check==1){
                System.out.println("which table want to rotate");
                table1=scanner.nextInt();
                System.out.println("1: Right 2: Left");
                charkhesh=scanner.nextInt();
                pentago.turn(charkhesh,table1);
            }
            else{
                System.out.println(" you choose stable");
            }
        }
        else{
            System.out.println("which table want to rotate");
            table1=scanner.nextInt();
            System.out.println("1: Right 2: Left");
            charkhesh=scanner.nextInt();
            pentago.turn(charkhesh,table1);
        }
        pentago.show();
    }
}
