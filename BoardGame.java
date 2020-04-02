import javax.swing.*;
<<<<<<< Updated upstream

public class BoardGame {
    private int tol;
    private  int arz;
    private char[][] Board;
=======
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Console;
import java.text.AttributedCharacterIterator;

public class BoardGame {
    private  final String ANSI_RESET = "\u001B[0m";
    private  final String ANSI_pink = "\u001B[38;5;197m";
    private  final String ANSIF_sefid = "\u001B[48;5;230m";
    private  final String ANSIF_ghermez = "\u001B[48;5;93m";
    private  final String ANSIF_khakestari = "\u001B[48;5;243m";
    private  final String ANSIF_Reset = "\u001B[49m";
    private  final String ANSIF_WHITE = "\u001B[48;5;7m";
    private  final String BLACK="\u26AA";
    private  final String WHITE="\u26AB";
    private int tol;
    private  int arz;
    private char[][] Board;
    private char[][] visualBoard;
>>>>>>> Stashed changes
    public BoardGame(int i,int j){
        tol=i;
        arz=j;
        Board=new char[tol][arz];
<<<<<<< Updated upstream
    }
    public void firstPut(){
=======
        visualBoard=new char[tol*3][arz*5];
    }
    public void firstPut(){


>>>>>>> Stashed changes
        for (int i = 0; i <arz ; i++) {
            for (int j = 0; j <tol ; j++) {
                Board[i][j]=' ';
            }
        }
    }
    public void put(int i,int j,char Color){
<<<<<<< Updated upstream
        Board[i][j]= Color;
    }
    public void show(){
        
=======
        Board[i-1][j-1]= Color;
        updateVisualBoard();


    }
    public void updateVisualBoard(){
        for (int i = 0; i <tol ; i++) {
            for (int j = 0; j <arz ; j++) {
                visualBoard[i*2+1][j*4+2]=Board[i][j];

            }

        }
    }
    public void setVisualBoard(){
        firstPut();
        for (int i = 0; i <tol*2.1 ; i++) {
            for (int j = 0; j <arz*4.1 ; j++) {
                if(i%2==0&&!(j%4==0)){

                    visualBoard[i][j]='-';
                }
                else if((i%2==0)&&(j%4==0)){
//                    if(i==0||i==17){
//                        visualBoard[i][j]='-';
//                    }
//                    else
                    visualBoard[i][j]='|';
                }
                else if(!(i%2==0)&&(j%4==0)){
                    visualBoard[i][j]='|';
                }
                else{
                    visualBoard[i][j]=' ';
                }
            }

        }

    }

    public void show(){
        for (int i = 0; i <tol*2.1; i++) {
            for (int j = 0; j <arz*4.1 ; j++) {
                if(visualBoard[i][j]==' '){
                    System.out.print(ANSIF_ghermez+' '+ANSIF_Reset);
                }
                else if(visualBoard[i][j]=='w'){
                    System.out.print(ANSIF_ghermez+WHITE+ANSIF_Reset);
                }
                else if(visualBoard[i][j]=='b'){
                    System.out.print(ANSIF_ghermez+BLACK+ANSIF_Reset);
                }
                else{
                System.out.print(ANSIF_khakestari+ANSI_pink+visualBoard[i][j]+ANSIF_Reset+ANSI_RESET);
            }}
            System.out.println();
        }


>>>>>>> Stashed changes
    }
}
