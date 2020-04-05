
import com.sun.source.tree.WhileLoopTree;

import java.util.Scanner;

public class Othello {
    private static final char WHITE = 'w';
    private static final char BLACK = 'b';
    private static final char NODE = ' ';
    private static final int MAP_SIZE = 8;

    private BoardGame board = new BoardGame(MAP_SIZE, MAP_SIZE);

    public boolean validInput(int i, char j) {
        int check = 0;
        if (i >= 1 && i <= 8) {
            check++;
        }
        Mohre test = new Mohre(i, j, WHITE);
        int check1 = test.getY();
//        System.out.println(check1);
        if (check1 >= 0 && check1 <= 7) {
            check++;
        }
        if (check == 2) {
            return true;
        } else {
//            System.out.println("invalid input");
            return false;
        }
    }

    public void firstStation() {
        board.setVisualBoard();
        board.put(3, 3, BLACK);
        board.put(4, 4, BLACK);
        board.put(3, 4, WHITE);
        board.put(4, 3, WHITE);
    }

    public boolean endGame() {
        int i = 0;
        i = board.getBlack().size() + board.getWhite().size();
        if (i >= MAP_SIZE * MAP_SIZE) {
            return true;
        }
        if (possibleMove(WHITE) || possibleMove(BLACK)) {
            return false;
        } else
            return true;
    }

    public void winner() {
        if (board.getWhite().size() > board.getBlack().size()) {
            System.out.println("white is winner");
        } else if (board.getWhite().size() == board.getBlack().size())
            System.out.println("draw");
        else
            System.out.println("black is winner");
    }

    public void showState() {
        System.out.println("White: " + board.getWhite().size() + "\nBlack: " + board.getBlack().size());
    }

    //check kone bbine kollan harekati hst ya na
    public boolean possibleMove(char x) {
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        int check = 0;
        test = board.getBoard();
        if (x == WHITE) {
            check = getCheck(test, check, BLACK, WHITE);
        } else {
            check = getCheck(test, check, WHITE, BLACK);
        }

        if (check > 0) {
            return true;
        } else
            return false;

    }

    public int getCheck(char[][] test, int check, char white, char black) {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (test[i][j] == white) {
                    if (j + 1 < MAP_SIZE && test[i][j + 1] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j + 1;
                        if (checkMove(i+1, ch, black)) {
                            check++;
                        }
                    }
                    if (j - 1 >= 0 && test[i][j - 1] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j - 1;
                        if (checkMove(i+1, ch, black)) {
                            check++;
                        }
                    }
                    if (i + 1 < MAP_SIZE && test[i + 1][j] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j;
                        if (checkMove(i + 1+1, ch, black)) {
                            check++;
                        }
                    }
                    if (i - 1 >= 0 && test[i - 1][j] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j;
                        if (checkMove(i - 1+1, ch, black)) {
                            check++;
                        }
                    }
                    if (j + 1 < MAP_SIZE && i + 1 < MAP_SIZE && test[i + 1][j + 1] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j + 1;
                        if (checkMove(i + 1+1, ch, black)) {
                            check++;
                        }
                    }
                    if (i - 1 >= 0 && j - 1 >= 0 && test[i - 1][j - 1] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j - 1;
                        if (checkMove(i - 1+1, ch, black)) {
                            check++;
                        }
                    }
                    if (j + 1 < MAP_SIZE && i - 1 >= 0 && test[i - 1][j + 1] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j + 1;
                        if (checkMove(i - 1+1, ch, black)) {
                            check++;
                        }
                    }
                    if (j - 1 >= 0 && i + 1 < MAP_SIZE && test[i + 1][j - 1] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j - 1;
                        if (checkMove(i + 1+1, ch, black)) {
                            check++;
                        }
                    }
                }


            }

        }
        return check;
    }

    //bargardondan
    public void move(int q, char z, char color) {
        char[][] test;
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//        System.out.println(x+"s"+y);
        test = board.getBoard();
        int counter = 1;

        if (color == WHITE) {
            move1(test, y, x, counter, WHITE);
            board.put(x, y, color);
        } else {
            move1(test, y, x, counter, BLACK);
            board.put(x, y, color);
        }
        board.setBoard(test);
        board.updateVisualBoard();
    }

    public void move1(char[][] test, int y, int x, int counter, char black) {
        char t='A';
        t+=y;
        if(rast(x+1,t,black)){
        while (test[x][y + counter] != black && ( y + counter < MAP_SIZE )) {
            if (test[x][y + counter] == NODE) {
                System.out.println("hi");
                break;
            } else {
                test[x][y + counter] = black;
                board.put(x, y + counter, black);
            }
            counter++;
        }}
        counter = 1;
        if(chap(x+1,t,black)){
        while (( y - counter >= 0 ) && test[x][y - counter] != black) {
            if (test[x][y - counter] == NODE) {
                System.out.println("hi1");
                break;
            } else {
                System.out.println("hi2");
                test[x][y - counter] = black;
                board.put(x, y - counter, black);
            }
            counter++;
        }}
        counter = 1;
        if(payin(x+1,t,black)){
        while (test[x + counter][y] != black && ( x + counter < MAP_SIZE )) {
            if (test[x + counter][y] == NODE) {
                System.out.println("hi3");
                break;
            } else {
                test[x + counter][y] = black;
                board.put(x + counter, y, black);
            }
            counter++;
        }}
        counter = 1;
        if(bala(x+1,t,black)){
        while (test[x - counter][y] != black && ( x - counter >= 0 )) {
            if (test[x - counter][y] == NODE) {
                System.out.println("hi4");
                break;
            } else {
                test[x - counter][y] = black;
                board.put(x - counter, y, black);
            }
            counter++;
        }}
        int counter1;
        counter1 = 1;
        counter = 1;
        if(rastpayin(x+1,t,black)){
        while (test[x + counter][y + counter1] != black && ( x + counter < MAP_SIZE ) && ( y + counter1 < MAP_SIZE )) {
            if (test[x + counter][y + counter1] == NODE) {
                System.out.println("hi5");
                break;
            } else {
                test[x + counter][y + counter1] = black;
                board.put(x + counter, y + counter1, black);
            }

            counter++;
            counter1++;
        }}
        counter1 = 1;
        counter = 1;
        if(chapbala(x+1,t,black)){
        while (test[x - counter][y - counter1] != black && ( x - counter >= 0 ) && ( y - counter1 >= 0 )) {
            if (test[x - counter][y - counter1] == NODE) {
                System.out.println("hi6");
                break;
            } else {
                test[x - counter][y - counter1] = black;
                board.put(x - counter, y - counter1, black);
            }
            counter++;
            counter1++;
        }}
        counter1 = 1;
        counter = 1;
        if(chappayin(x+1,t,black)){
        while (test[x + counter][y - counter1] != black && ( x + counter < MAP_SIZE ) && ( y - counter1 >= 0 )) {
            if (test[x + counter][y - counter1] == NODE) {
                System.out.println("hi7");
                break;
            } else {
                test[x + counter][y - counter1] = black;
                board.put(x + counter, y - counter1, black);
            }
            counter++;
            counter1++;
        }}
        counter1 = 1;
        counter = 1;
        if(rastbala(x+1,t,black)){
        while (test[x - counter][y + counter1] != black && ( x - counter >= 0 ) && ( y + counter1 < MAP_SIZE )) {
            if (test[x - counter][y + counter1] == NODE) {
                System.out.println("hi8");
                break;
            } else {
                test[x - counter][y + counter1] = black;
                board.put(x - counter, y + counter1, black);
            }
            counter++;
            counter1++;
        }}
    }
    //check krdn harekat age bashe
    public boolean rast(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//        System.out.println(x+" "+y);
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;
        // harekat ofoghi be jolo
        for (int i = y + 1; i < MAP_SIZE; i++) {
            if (test[x][i] == WHITE)
                whiteCounter++;
            else if (test[x][i] == BLACK)
                blackCounter++;
            if (test[x][i] == NODE)
                break;
//            System.out.println(whiteCounter+" "+blackCounter);
            if (color == WHITE) {
                if (whiteCounter > 0 && blackCounter == 0) {
                    break;
                }
            }
            if (color == BLACK) {
                if (whiteCounter == 0 && blackCounter > 0) {
                    break;
                }
            }
            if (whiteCounter == 0 && blackCounter == 0)
                break;
        }
        if (whiteCounter > 0 && blackCounter > 0) {
            return true;
//            System.out.println(ways+"hi");
        }
        return false;
    }

    public boolean chap(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//        System.out.println(x+" "+y);
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;
        // harekat ofoghi be jolo


        //harekat ofoghi be aghab
        for (int i = y - 1; i >= 0; i--) {
            if (test[x][i] == WHITE)
                whiteCounter++;
            else if (test[x][i] == BLACK)
                blackCounter++;
            if (test[x][i] == NODE)
                break;
//            System.out.println(whiteCounter+" "+blackCounter);
            if (color == WHITE) {
                if (whiteCounter > 0 && blackCounter == 0) {

                    break;
                }
            }
            if (color == BLACK) {
                if (whiteCounter == 0 && blackCounter > 0) {
                    break;
                }
            }
            if (whiteCounter == 0 && blackCounter == 0)
                break;
        }
        if (whiteCounter > 0 && blackCounter > 0)
            return true;
        return false;
    }

    public boolean payin(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//        System.out.println(x+" "+y);
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;

        //harekat amodi be payin
        for (int i = x + 1; i < MAP_SIZE; i++) {
            if (test[i][y] == WHITE)
                whiteCounter++;
            else if (test[i][y] == BLACK)
                blackCounter++;
            if (test[i][y] == NODE)
                break;
//            System.out.println(whiteCounter+" "+blackCounter);
            if (color == WHITE) {
                if (whiteCounter > 0 && blackCounter == 0) {

                    break;
                }
            }
            if (color == BLACK) {
                if (whiteCounter == 0 && blackCounter > 0) {
                    break;
                }
            }
            if (whiteCounter == 0 && blackCounter == 0)
                break;
        }
        if (whiteCounter > 0 && blackCounter > 0)
            return true;
        return false;
    }

    public boolean bala(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//        System.out.println(x+" "+y);
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;

        //harekat amodi be bala
        for (int i = x - 1; i >= 0; i--) {
            if (test[i][y] == WHITE)
                whiteCounter++;
            else if (test[i][y] == BLACK)
                blackCounter++;
//            System.out.println(whiteCounter+" "+blackCounter);
            if (test[i][y] == NODE)
                break;
            if (color == WHITE) {
                if (whiteCounter > 0 && blackCounter == 0) {

                    break;
                }
            }
            if (color == BLACK) {
                if (whiteCounter == 0 && blackCounter > 0) {
                    break;
                }

            }
            if (whiteCounter == 0 && blackCounter == 0)
                break;
        }
        if (whiteCounter > 0 && blackCounter > 0)
            return true;
        return false;
    }

    public boolean rastpayin(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//        System.out.println(x+" "+y);
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;
        int counter = x + 1;
        int counter1 = y + 1;
        //harekat oribbe rast payin;
        while (counter < MAP_SIZE && counter1 < MAP_SIZE) {
            if (test[counter][counter1] == WHITE)
                whiteCounter++;
            else if (test[counter][counter1] == BLACK)
                blackCounter++;
//            System.out.println(whiteCounter+" "+blackCounter);
            if (test[counter][counter1] == NODE)
                break;
            if (color == WHITE) {
                if (whiteCounter > 0 && blackCounter == 0) {
                    break;
                }
            }
            if (color == BLACK) {
                if (whiteCounter == 0 && blackCounter > 0) {
                    break;
                }
            }
            counter++;
            counter1++;
            if (whiteCounter == 0 && blackCounter == 0)
                break;
        }
        if (whiteCounter > 0 && blackCounter > 0)
            return true;
        return false;
    }

    public boolean chapbala(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//        System.out.println(x+" "+y);
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;
        int counter = x - 1;
        int counter1 = y - 1;

        //harekat oribbe chap bala;
        while (counter >= 0 && counter1 >= 0) {
            if (test[counter][counter1] == WHITE)
                whiteCounter++;
            else if (test[counter][counter1] == BLACK)
                blackCounter++;
//            System.out.println(whiteCounter+" "+blackCounter);
            if (test[counter][counter1] == NODE)
                break;
            if (color == WHITE) {
                if (whiteCounter > 0 && blackCounter == 0) {
                    break;
                }
            }
            if (color == BLACK) {
                if (whiteCounter == 0 && blackCounter > 0) {
                    break;
                }
            }
            counter--;
            counter1--;
            if (whiteCounter == 0 && blackCounter == 0)
                break;
        }
        if (whiteCounter > 0 && blackCounter > 0)
            return true;
        return false;
    }

    public boolean chappayin(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//        System.out.println(x+" "+y);
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;
        int counter ;
        int counter1 ;

        counter = x + 1;
        counter1 = y - 1;
        //harekat oribbe chap payin;
        while (counter < MAP_SIZE && counter1 >= 0) {
            if (test[counter][counter1] == WHITE)
                whiteCounter++;
            else if (test[counter][counter1] == BLACK)
                blackCounter++;
//            System.out.println(whiteCounter+" "+blackCounter);
            if (test[counter][counter1] == NODE)
                break;
            if (color == WHITE) {
                if (whiteCounter > 0 && blackCounter == 0) {
                    break;
                }
            }
            if (color == BLACK) {
                if (whiteCounter == 0 && blackCounter > 0) {
                    break;
                }
            }
            counter++;
            counter1--;
            if (whiteCounter == 0 && blackCounter == 0)
                break;
        }
        if (whiteCounter > 0 && blackCounter > 0)
            return true;
        ;
        return false;
    }

    public boolean rastbala(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//        System.out.println(x+" "+y);
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;
        int counter ;
        int counter1 ;

        counter = x - 1;
        counter1 = y + 1;
        //harekat oribbe rast bala;
        while (counter >= 0 && counter1 < MAP_SIZE) {
            if (test[counter][counter1] == WHITE)
                whiteCounter++;
            else if (test[counter][counter1] == BLACK)
                blackCounter++;
//            System.out.println(whiteCounter+" "+blackCounter);
            if (test[counter][counter1] == NODE)
                break;
            if (color == WHITE) {
                if (whiteCounter > 0 && blackCounter == 0) {
                    break;
                }
            }
            if (color == BLACK) {
                if (whiteCounter == 0 && blackCounter > 0) {
                    break;
                }
            }
            counter--;
            counter1++;
            if (whiteCounter == 0 && blackCounter == 0)
                break;
        }
        if (whiteCounter > 0 && blackCounter > 0)
            return true;
        ;
        return false;
    }

    public boolean checkMove(int q, char z, char color) {
        int ways = 0;
        if (rast(q, z, color))
            ways++;
        if (chap(q, z, color))
            ways++;
        if (bala(q, z, color))
            ways++;
        if (payin(q, z, color))
            ways++;
        if (rastbala(q, z, color))
            ways++;
        if (rastpayin(q, z, color))
            ways++;
        if (chapbala(q, z, color))
            ways++;
        if (chappayin(q, z, color))
            ways++;
        if(ways>0)
        return true;
        else
        return false;
    }

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Othello othello = new Othello();
        othello.firstStation();
//        othello.board.show();
        System.out.println("Hello to othello game");
        othello.board.show();
        while (!othello.endGame()) {

            if (othello.possibleMove(WHITE)) {
                System.out.println("WHITE  your turn ");
                int row = scanner.nextInt();
                char column = scanner.next().charAt(0);
                while (!othello.validInput(row, column) || !othello.checkMove(row, column, WHITE)) {
//                    if(!othello.validInput(row,column))
//                        System.out.println("fuck");
                    System.out.println("invalid input");
                    row = scanner.nextInt();
                    column = scanner.next().charAt(0);
                }
                othello.move(row, column, WHITE);
                othello.board.show();
            } else {
                System.out.println(" pass turn from White");
            }
            othello.showState();
//            othello.wait(1000);
            if (othello.possibleMove(BLACK)) {
                System.out.println("BLACK  your turn ");
                int row = scanner.nextInt();
                char column = scanner.next().charAt(0);
                while (!othello.validInput(row, column) && !othello.checkMove(row, column, BLACK)) {
                    System.out.println("invalid input");
                    row = scanner.nextInt();
                    column = scanner.next().charAt(0);
                }
                othello.move(row, column, BLACK);
                othello.board.show();
            } else {
                System.out.println(" pass turn from black");
            }
            othello.showState();
        }
        othello.winner();
    }
}