
import com.sun.source.tree.WhileLoopTree;

import java.util.Scanner;

public class Othello {
    private static final char WHITE = 'w';
    private static final char BLACK = 'b';
    private static final char EMPTY = ' ';
    private static final int MAP_SIZE = 8;

    private BoardGame board = new BoardGame(MAP_SIZE, MAP_SIZE);

    /**
     * check input is valid or not.
     * @param i row of input.
     * @param j column of input.
     * @return if input valid return true.
     * check input in the box or not.
     */
    public boolean validInput(int i, char j) {
        int check = 0;
        if (i >= 1 && i <= 8) {
            check++;
        }
        Mohre test = new Mohre(i, j, WHITE);
        int check1 = test.getY();
        if (check1 >= 0 && check1 <= 7) {
            check++;
        }
        // both row and column check
        if (check == 2) {
            return true;
        } else {

            return false;
        }
    }
    /**
     * put for EMPTY for first of the gmae.
     */
    public void firstStation() {
        board.setVisualBoard();
        board.put(3, 3, BLACK);
        board.put(4, 4, BLACK);
        board.put(3, 4, WHITE);
        board.put(4, 3, WHITE);
    }
    /**
     * check end pf the game
     * @return if game is not end return false.
     * check both color have possible move and number of nodes.
     */
    public boolean endGame() {
        int i = 0;
        i = board.getBlack().size() + board.getWhite().size();
        // check number of nodes
        if (i >= MAP_SIZE * MAP_SIZE) {
            return true;
        }
        // check each color has move or not
        if (possibleMove(WHITE) || possibleMove(BLACK)) {
            return false;
        } else
            return true;
    }

    /**
     * show winner at the end of the game.
     * with number of nodes color have.
     */
    public void winner() {
        // compare number of nodes
        if (board.getWhite().size() > board.getBlack().size()) {
            System.out.println("white is winner");
        } else if (board.getWhite().size() == board.getBlack().size())
            System.out.println("draw");
        else
            System.out.println("black is winner");
    }

    /**
     * show number of nodes for each color.
     */
    public void showState() {
        System.out.println("White: " + board.getWhite().size() + "\nBlack: " + board.getBlack().size());
    }

    /**
     * check each player has possible move or not.
     * @param color color of player.
     * @return if player has move return true.
     */
    public boolean possibleMove(char color) {
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        int check = 0;
        test = board.getBoard();
        // for each player
        if (color == WHITE) {
            //number of moves player has
            check = getCheck(test, check, BLACK, WHITE);
        } else {
            check = getCheck(test, check, WHITE, BLACK);
        }

        if (check > 0) {
            return true;
        } else
            return false;

    }

    /**
     * calculate ways of move for each player
     * @param test Board of gaming have moves.
     * @param check
     * @param white opposite color of the player want to check.
     * @param black color of the player we check moves.
     * @return
     */
    public int getCheck(char[][] test, int check, char white, char black) {
        // check all opposite nodes in map
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (test[i][j] == white) {
                    // check all side. just one box over
                    // check right side.
                    if (j + 1 < MAP_SIZE && test[i][j + 1] == EMPTY) {
                        char ch;
                        ch = 'A';
                        ch += j + 1;
                        //check in right side. we have move or not
                        if (checkMove(i + 1, ch, black)) {
                            check++;
                        }
                    }
                    //check left side.
                    if (j - 1 >= 0 && test[i][j - 1] == EMPTY) {
                        char ch;
                        ch = 'A';
                        ch += j - 1;
                        // check we have posible move or not
                        if (checkMove(i + 1, ch, black)) {
                            check++;
                        }
                    }
                    //up side.
                    if (i + 1 < MAP_SIZE && test[i + 1][j] == EMPTY) {
                        char ch;
                        ch = 'A';
                        ch += j;
                        if (checkMove(i + 1 + 1, ch, black)) {
                            check++;
                        }
                    }
                    // down side.
                    if (i - 1 >= 0 && test[i - 1][j] == EMPTY) {
                        char ch;
                        ch = 'A';
                        ch += j;
                        if (checkMove(i - 1 + 1, ch, black)) {
                            check++;
                        }
                    }
                    // right down
                    if (j + 1 < MAP_SIZE && i + 1 < MAP_SIZE && test[i + 1][j + 1] == EMPTY) {
                        char ch;
                        ch = 'A';
                        ch += j + 1;
                        if (checkMove(i + 1 + 1, ch, black)) {
                            check++;
                        }
                    }
                    //left up
                    if (i - 1 >= 0 && j - 1 >= 0 && test[i - 1][j - 1] == EMPTY) {
                        char ch;
                        ch = 'A';
                        ch += j - 1;
                        if (checkMove(i - 1 + 1, ch, black)) {
                            check++;
                        }
                    }
                    //right up
                    if (j + 1 < MAP_SIZE && i - 1 >= 0 && test[i - 1][j + 1] == EMPTY) {
                        char ch;
                        ch = 'A';
                        ch += j + 1;
                        if (checkMove(i - 1 + 1, ch, black)) {
                            check++;
                        }
                    }
                    //left down
                    if (j - 1 >= 0 && i + 1 < MAP_SIZE && test[i + 1][j - 1] == EMPTY) {
                        char ch;
                        ch = 'A';
                        ch += j - 1;
                        if (checkMove(i + 1 + 1, ch, black)) {
                            check++;
                        }
                    }
                }


            }

        }
        return check;
    }

    /**
     *  turn color of nodes.
     * @param q node row.
     * @param z node column.
     * @param color color of node.
     *              if this location cant change node color.
     *              do nothing.
     *
     */
    public void move(int q, char z, char color) {
        char[][] test;
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//
        test = board.getBoard();
        int counter = 1;
        // check both color
        if (color == WHITE) {
            move1(test, y, x, counter, WHITE);

        } else {
            move1(test, y, x, counter, BLACK);
        }
        // update source board and visual board for print.
        board.setBoard(test);
        board.updateVisualBoard();
    }

    /**
     * this method help move method.
     * @param test Board of game.
     * @param y param column.
     * @param x param row
     * @param counter check moves.
     * @param black color of node.
     *              check each side. have move or not if .
     *              have turn nodes color .
     */
    public void move1(char[][] test, int y, int x, int counter, char black) {
        char t = 'A';
        t += y;
        // check have possible move or not
        if (checkMove(x + 1, t, black)){
            //right
            if (right(x + 1, t, black)) {
                //turn color of nodes
                while (test[x][y + counter] != black && ( y + counter < MAP_SIZE )) {
                    // check if reach empty box break
                    if (test[x][y + counter] == EMPTY) {
                        break;
                    } else {
                        // put node on source Board
                        test[x][y + counter] = black;
                        board.put(x, y + counter, black);
                    }
                    counter++;
                }
            }
            counter = 1;
            //left
            if (left(x + 1, t, black)) {
                while (( y - counter >= 0 ) && test[x][y - counter] != black) {
                    if (test[x][y - counter] == EMPTY) {

                        break;
                    } else {
                        test[x][y - counter] = black;
                        board.put(x, y - counter, black);
                    }
                    counter++;
                }
            }
            counter = 1;
            //down
            if (Down(x + 1, t, black)) {
                while (test[x + counter][y] != black && ( x + counter < MAP_SIZE )) {
                    if (test[x + counter][y] == EMPTY) {
                        break;
                    } else {
                        test[x + counter][y] = black;
                        board.put(x + counter, y, black);
                    }
                    counter++;
                }
            }
            counter = 1;
            //up side.
            if (Up(x + 1, t, black)) {
                while (test[x - counter][y] != black && ( x - counter >= 0 )) {
                    if (test[x - counter][y] == EMPTY) {

                        break;
                    } else {
                        test[x - counter][y] = black;
                        board.put(x - counter, y, black);
                    }
                    counter++;
                }
            }
            int counter1;
            counter1 = 1;
            counter = 1;
            // right down side.
            if (rightDown(x + 1, t, black)) {
                while (test[x + counter][y + counter1] != black && ( x + counter < MAP_SIZE ) && ( y + counter1 < MAP_SIZE )) {
                    if (test[x + counter][y + counter1] == EMPTY) {

                        break;
                    } else {
                        test[x + counter][y + counter1] = black;
                        board.put(x + counter, y + counter1, black);
                    }

                    counter++;
                    counter1++;
                }
            }
            counter1 = 1;
            counter = 1;
            // left upside.
            if (leftUp(x + 1, t, black)) {
                while (test[x - counter][y - counter1] != black && ( x - counter >= 0 ) && ( y - counter1 >= 0 )) {
                    if (test[x - counter][y - counter1] == EMPTY) {

                        break;
                    } else {
                        test[x - counter][y - counter1] = black;
                        board.put(x - counter, y - counter1, black);
                    }
                    counter++;
                    counter1++;
                }
            }
            counter1 = 1;
            counter = 1;
            //left down side.
            if (leftDown(x + 1, t, black)) {
                while (test[x + counter][y - counter1] != black && ( x + counter < MAP_SIZE ) && ( y - counter1 >= 0 )) {
                    if (test[x + counter][y - counter1] == EMPTY) {

                        break;
                    } else {
                        test[x + counter][y - counter1] = black;
                        board.put(x + counter, y - counter1, black);
                    }
                    counter++;
                    counter1++;
                }
            }
            counter1 = 1;
            counter = 1;
            //right upside.
            if (rightUp(x + 1, t, black)) {
                while (test[x - counter][y + counter1] != black && ( x - counter >= 0 ) && ( y + counter1 < MAP_SIZE )) {
                    if (test[x - counter][y + counter1] == EMPTY) {
                        break;
                    } else {
                        test[x - counter][y + counter1] = black;
                        board.put(x - counter, y + counter1, black);
                    }
                    counter++;
                    counter1++;
                }
            }
            // put node want to play
            board.put(x, y, black);}
    }

    /**
     * check right side.
     * @param q node row.
     * @param z node column
     * @param color node color
     * @return if can do move to right return true.
     */
    public boolean right(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();

        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        // if full return false
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;
        // move to right and count black and white nodes.
        for (int i = y + 1; i < MAP_SIZE; i++) {
            if (test[x][i] == WHITE)
                whiteCounter++;
            else if (test[x][i] == BLACK)
                blackCounter++;
            //if reach node break for.
            if (test[x][i] == EMPTY)
                break;
//         reach white without black
            if (color == WHITE) {
                if (whiteCounter > 0 && blackCounter == 0) {
                    break;
                }
            }
            //reach black without white
            if (color == BLACK) {
                if (whiteCounter == 0 && blackCounter > 0) {
                    break;
                }
            }
            if (whiteCounter == 0 && blackCounter == 0)
                break;
        }
        // if all thing right return true
        if (whiteCounter > 0 && blackCounter > 0) {
            return true;
//
        }
        return false;
    }
    /**
     * check left side.
     * @param q node row.
     * @param z node column
     * @param color node color
     * @return if can do move to right return true.
     */
    public boolean left(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;

        //move left side.
        for (int i = y - 1; i >= 0; i--) {
            // count black and white
            if (test[x][i] == WHITE)
                whiteCounter++;
            else if (test[x][i] == BLACK)
                blackCounter++;
            if (test[x][i] == EMPTY)
                break;
//         reach color without opposite side.
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
            // check just node
            if (whiteCounter == 0 && blackCounter == 0)
                break;
        }
        if (whiteCounter > 0 && blackCounter > 0)
            return true;
        return false;
    }
    /**
     * check down side.
     * @param q node row.
     * @param z node column
     * @param color node color
     * @return if can do move to right return true.
     */
    public boolean Down(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;

        // move down side.
        for (int i = x + 1; i < MAP_SIZE; i++) {
            if (test[i][y] == WHITE)
                whiteCounter++;
            else if (test[i][y] == BLACK)
                blackCounter++;
            if (test[i][y] == EMPTY)
                break;
//
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
    /**
     * check up  side.
     * @param q node row.
     * @param z node column
     * @param color node color
     * @return if can do move to right return true.
     */
    public boolean Up(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;

        // move up side.
        for (int i = x - 1; i >= 0; i--) {
            if (test[i][y] == WHITE)
                whiteCounter++;
            else if (test[i][y] == BLACK)
                blackCounter++;
//
            if (test[i][y] == EMPTY)
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
    /**
     * check rightdown side.
     * @param q node row.
     * @param z node column
     * @param color node color
     * @return if can do move to right return true.
     */
    public boolean rightDown(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;
        int counter = x + 1;
        int counter1 = y + 1;
        // move right up side.
        while (counter < MAP_SIZE && counter1 < MAP_SIZE) {
            if (test[counter][counter1] == WHITE)
                whiteCounter++;
            else if (test[counter][counter1] == BLACK)
                blackCounter++;
//
            if (test[counter][counter1] == EMPTY)
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
    /**
     * check leftup side.
     * @param q node row.
     * @param z node column
     * @param color node color
     * @return if can do move to right return true.
     */
    public boolean leftUp(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();

        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;
        int counter = x - 1;
        int counter1 = y - 1;

        // move toward left up side.
        while (counter >= 0 && counter1 >= 0) {
            if (test[counter][counter1] == WHITE)
                whiteCounter++;
            else if (test[counter][counter1] == BLACK)
                blackCounter++;
//
            if (test[counter][counter1] == EMPTY)
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
    /**
     * check left down  side..
     * @param q node row.
     * @param z node column
     * @param color node color
     * @return if can do move to right return true.
     */
    public boolean leftDown(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;
        int counter;
        int counter1;

        counter = x + 1;
        counter1 = y - 1;
        //left down
        while (counter < MAP_SIZE && counter1 >= 0) {
            if (test[counter][counter1] == WHITE)
                whiteCounter++;
            else if (test[counter][counter1] == BLACK)
                blackCounter++;
//
            if (test[counter][counter1] == EMPTY)
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
    /**
     * check rightup side.
     * @param q node row.
     * @param z node column
     * @param color node color
     * @return if can do move to right return true.
     */
    public boolean rightUp(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
//
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        if (test[x][y] == WHITE || test[x][y] == BLACK)
            return false;

        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;
        int counter;
        int counter1;

        counter = x - 1;
        counter1 = y + 1;
        //right up side..;
        while (counter >= 0 && counter1 < MAP_SIZE) {
            if (test[counter][counter1] == WHITE)
                whiteCounter++;
            else if (test[counter][counter1] == BLACK)
                blackCounter++;
//
            if (test[counter][counter1] == EMPTY)
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
    /**
     * check all  sides.
     * @param q node row.
     * @param z node column
     * @param color node color
     * @return if can do move to right return true.
     */
    public boolean checkMove(int q, char z, char color) {
        int ways = 0;
        // all side
        if (right(q, z, color))
            ways++;
        if (left(q, z, color))
            ways++;
        if (Up(q, z, color))
            ways++;
        if (Down(q, z, color))
            ways++;
        if (rightUp(q, z, color))
            ways++;
        if (rightDown(q, z, color))
            ways++;
        if (leftUp(q, z, color))
            ways++;
        if (leftDown(q, z, color))
            ways++;
        if (ways > 0)
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
        // start game.
        while (!othello.endGame()) {

            if (othello.possibleMove(WHITE)) {
                System.out.println("WHITE  your turn ");
                // scan row and column.
                int row = scanner.nextInt();
                char column = scanner.next().charAt(0);
                //valid moves
                while (!othello.validInput(row, column) || !othello.checkMove(row, column, WHITE)) {
//
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
//
            if (othello.possibleMove(BLACK)) {
                System.out.println("BLACK  your turn ");
                int row = scanner.nextInt();
                char column = scanner.next().charAt(0);
                while (!othello.validInput(row, column) || !othello.checkMove(row, column, BLACK)) {
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