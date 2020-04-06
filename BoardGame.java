import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.io.Console;
import java.text.AttributedCharacterIterator;
import java.util.HashSet;

public class BoardGame {
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_pink = "\u001B[38;5;197m";
    private final String ANSIF_ghermez = "\u001B[48;5;93m";
    private final String ANSIF_khakestari = "\u001B[48;5;243m";
    private final String ANSIF_Reset = "\u001B[49m";
    private final String BLACK = "\u26AA";
    private final String WHITE = "\u26AB";
    private int tol;
    private int arz;
    private char[][] Board;
    private char[][] visualBoard;
    private HashSet<Mohre> white = new HashSet<>();
    private HashSet<Mohre> black = new HashSet<>();

    /**
     * constructor.
     * @param i number of row.
     * @param j number of column.
     */
    public BoardGame(int i, int j) {
        tol = i;
        arz = j;
        Board = new char[tol][arz];
        visualBoard = new char[tol * 5][arz * 11];
    }

    /**
     * put white space to array to know empty boxes.
     */
    public void firstPut() {
        for (int i = 0; i < arz; i++) {
            for (int j = 0; j < tol; j++) {
                Board[i][j] = ' ';
            }
        }
    }

    /**
     * check number of black and white nodes.
     */
    public void checknode() {
        char test = 'A';
        white = new HashSet<>();
        black = new HashSet<>();
        for (int i = 0; i < tol; i++) {
            for (int j = 0; j < arz; j++) {
                if (Board[i][j] == 'w') {
                    test += j;
                    Mohre mohre = new Mohre(i, test, 'w');
                    white.add(mohre);

                } else if (Board[i][j] == 'b') {
                    test += j;
                    Mohre mohre = new Mohre(i, test, 'b');
                    black.add(mohre);

                }
            }
        }
    }

    /**
     * put e node on board
     * @param x node row.
     * @param y node column.
     * @param Color node color.
     *              update Board array.
     *              and update visual board.
     */
    public void put(int x, int y, char Color) {

        Board[x][y] = Color;
        updateVisualBoard();
        checknode();
    }

    /**
     * we have visual Board to draw ground for gaming.
     * and this method merge this with source array.
     */
    public void updateVisualBoard() {
        for (int i = 0; i < tol; i++) {
            for (int j = 0; j < arz; j++) {
                visualBoard[i * 4 + 2][j * 10 + 5] = Board[i][j];

            }

        }
    }

    /**
     * set a table in bigger array.
     */
    public void setVisualBoard() {
        firstPut();
        for (int i = 0; i <= tol * 4; i++) {
            for (int j = 0; j <= arz * 10; j++) {
                if (i % 4 == 0 && !( j % 10 == 0 )) {

                    visualBoard[i][j] = '-';
                } else if (( i % 4 == 0 ) && ( j % 10 == 0 )) {
//
                    visualBoard[i][j] = '|';
                } else if (!( i % 4 == 0 ) && ( j % 10 == 0 )) {
                    visualBoard[i][j] = '|';
                } else {
                    visualBoard[i][j] = ' ';
                }
            }

        }

    }

    /**
     * print Board of game.
     */
    public void show() {
        int counter = 0;
        int counter1 = 1;
        System.out.print("       ");
        for (int k = 0; k < arz * 10; k++) {
            char test = 'A';
            if (k % 10 == 0) {
                test += counter;
                System.out.print(test + "         ");
                counter++;
            }

        }
        System.out.println();
        for (int i = 0; i <= tol * 4; i++) {
            if (i % 4 == 2) {
                System.out.print(counter1 + " ");
                counter1++;
            } else
                System.out.print("  ");
            for (int j = 0; j <= arz * 10; j++) {

                if (visualBoard[i][j] == ' ') {
                    System.out.print(ANSIF_ghermez + ' ' + ANSIF_Reset);
                } else if (visualBoard[i][j] == 'w') {
                    System.out.print(ANSIF_ghermez + WHITE + ANSIF_Reset);
                } else if (visualBoard[i][j] == 'b') {
                    System.out.print(ANSIF_ghermez + BLACK + ANSIF_Reset);
                } else {
                    System.out.print(ANSIF_khakestari + ANSI_pink + " " + ANSIF_Reset + ANSI_RESET);
                }
            }
            System.out.println();
        }
        System.out.println("\n");
    }

    public char[][] getBoard() {
        return Board;
    }

    public void setBoard(char[][] board) {
        Board = board;
    }

    public HashSet<Mohre> getBlack() {
        return black;
    }

    public HashSet<Mohre> getWhite() {
        return white;
    }
}