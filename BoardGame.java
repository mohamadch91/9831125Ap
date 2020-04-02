import javax.swing.*;

public class BoardGame {
    private int tol;
    private  int arz;
    private char[][] Board;
    public BoardGame(int i,int j){
        tol=i;
        arz=j;
        Board=new char[tol][arz];
    }
    public void firstPut(){
        for (int i = 0; i <arz ; i++) {
            for (int j = 0; j <tol ; j++) {
                Board[i][j]=' ';
            }
        }
    }
    public void put(int i,int j,char Color){
        Board[i][j]= Color;
    }
    public void show(){
        
    }
}
