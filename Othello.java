import java.util.HashSet;

public class Othello {
    private static final char WHITE = 'w';
    private static final char BLACK = 'B';
    private static final char NODE = ' ';
    private static final int MAP_SIZE = '8';

    private BoardGame board = new BoardGame(MAP_SIZE, MAP_SIZE);

    public boolean validInput(int i, char j) {
        int check = 0;
        if (i >= 1 && i <= 8) {
            check++;
        }
        int check1 = (int) (j - 'A');
        if (check1 >= 0 && check1 <= 7) {
            check++;
        }
        if (check == 2) {
            return true;
        } else
            return false;
    }

    public void firstStation() {
        board.setVisualBoard();
        board.put(4, 'D', WHITE);
        board.put(5, 'E', WHITE);
        board.put(4, 'E', BLACK);
        board.put(5, 'D', BLACK);
    }

    public boolean endGame() {
        int i = 0;
        i = board.getBlack().size() + board.getWhite().size();
        if (i >= MAP_SIZE * MAP_SIZE) {
            return true;
        }
        if (possibleMove(WHITE) && possibleMove(BLACK)) {
            return false;
        } else {
            return true;
        }
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

    private int getCheck(char[][] test, int check, char white, char black) {
        for (int i = 0; i < MAP_SIZE; i++) {
            for (int j = 0; j < MAP_SIZE; j++) {
                if (test[i][j] == white) {

                    if (j + 1 < MAP_SIZE && test[i][j + 1] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j + 1;
                        if (checkMove(i, ch, black)) {
                            check++;
                        }
                    }
                    if (j - 1 >= 0 && test[i][j - 1] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j - 1;
                        if (checkMove(i, ch, black)) {
                            check++;
                        }
                    }
                    if (i + 1 < MAP_SIZE && test[i + 1][j] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j;
                        if (checkMove(i + 1, ch, black)) {
                            check++;
                        }
                    }
                    if (i - 1 >= 0 && test[i - 1][j] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j;
                        if (checkMove(i - 1, ch, black)) {
                            check++;
                        }
                    }
                    if (j + 1 < MAP_SIZE && i + 1 < MAP_SIZE && test[i + 1][j + 1] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j + 1;
                        if (checkMove(i + 1, ch, black)) {
                            check++;
                        }
                    }
                    if (i - 1 >= 0 && j - 1 >= 0 && test[i - 1][j - 1] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j - 1;
                        if (checkMove(i - 1, ch, black)) {
                            check++;
                        }
                    }
                    if (j + 1 < MAP_SIZE && i - 1 >= 0 && test[i - 1][j + 1] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j + 1;
                        if (checkMove(i - 1, ch, black)) {
                            check++;
                        }
                    }
                    if (j - 1 >= 0 && i + 1 < MAP_SIZE && test[i + 1][j - 1] == NODE) {
                        char ch;
                        ch = 'A';
                        ch += j - 1;
                        if (checkMove(i + 1, ch, black)) {
                            check++;
                        }
                    }
                }


            }

        }
        return check;
    }

    //bargardondan
    public void move(int x, char y, char color) {

    }

    //check krdn harekat age bashe
    public boolean checkMove(int q, char z, char color) {
        Mohre check = new Mohre(q, z, color);
        int y = check.getY();
        int x = check.getX();
        char[][] test = new char[MAP_SIZE][MAP_SIZE];
        test = board.getBoard();
        int whiteCounter = 0;
        int blackCounter = 0;
        int ways = 0;
        // harekat ofoghi be jolo
        for (int i = y + 1; i < MAP_SIZE; i++) {
            if (test[x][i] == WHITE)
                whiteCounter++;
            else if (test[x][i] == BLACK)
                blackCounter++;
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
        }
        if (whiteCounter > 0 && blackCounter > 0)
            ways++;
        whiteCounter = 0;
        blackCounter = 0;
        //harekat ofoghi be aghab
        for (int i = y - 1; i >= 0; i--) {
            if (test[x][i] == WHITE)
                whiteCounter++;
            else if (test[x][i] == BLACK)
                blackCounter++;
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
        }
        if (whiteCounter > 0 && blackCounter > 0)
            ways++;
        whiteCounter = 0;
        blackCounter = 0;
        //harekat amodi be payin
        for (int i = x + 1; i < MAP_SIZE; i++) {
            if (test[i][y] == WHITE)
                whiteCounter++;
            else if (test[i][y] == BLACK)
                blackCounter++;
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
        }
        if (whiteCounter > 0 && blackCounter > 0)
            ways++;
        whiteCounter = 0;
        blackCounter = 0;
        //harekat amodi be bala
        for (int i = x - 1; i >= 0; i--) {
            if (test[i][y] == WHITE)
                whiteCounter++;
            else if (test[i][y] == BLACK)
                blackCounter++;
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
        }
        if (whiteCounter > 0 && blackCounter > 0)
            ways++;
        whiteCounter = 0;
        blackCounter = 0;
        int counter = x + 1;
        int counter1 = y + 1;
        //harekat oribbe rast payin;
        while (counter < MAP_SIZE && counter1 < MAP_SIZE) {
            if (test[counter][counter1] == WHITE)
                whiteCounter++;
            else if (test[counter][counter1] == BLACK)
                blackCounter++;
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

        }
        if (whiteCounter > 0 && blackCounter > 0)
            ways++;
        whiteCounter = 0;
        blackCounter = 0;
        counter = x - 1;
        counter1 = y - 1;
        //harekat oribbe chap bala;
        while (counter >= 0 && counter1 >= 0) {
            if (test[counter][counter1] == WHITE)
                whiteCounter++;
            else if (test[counter][counter1] == BLACK)
                blackCounter++;
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

        }
        if (whiteCounter > 0 && blackCounter > 0)
            ways++;
        whiteCounter = 0;
        blackCounter = 0;
        counter = x + 1;
        counter1 = y - 1;
        //harekat oribbe chap payin;
        while (counter < MAP_SIZE && counter1 >= 0) {
            if (test[counter][counter1] == WHITE)
                whiteCounter++;
            else if (test[counter][counter1] == BLACK)
                blackCounter++;
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

        }
        if (whiteCounter > 0 && blackCounter > 0)
            ways++;
        whiteCounter = 0;
        blackCounter = 0;
        counter = x - 1;
        counter1 = y + 1;
        //harekat oribbe rast bala;
        while (counter >= 0 && counter1 < MAP_SIZE) {
            if (test[counter][counter1] == WHITE)
                whiteCounter++;
            else if (test[counter][counter1] == BLACK)
                blackCounter++;
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

        }
        if (whiteCounter > 0 && blackCounter > 0)
            ways++;
        if (ways > 0)
            return true;
        else
            return false;
    }


}
