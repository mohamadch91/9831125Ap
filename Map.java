public class Map {
    private int size;
    private  int size1;
    protected char[][] map;
    private char[][] visualmap;
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_pink = "\u001B[38;5;197m";
    private final String ANSIF_ghermez = "\u001B[48;5;93m";
    private final String ANSIF_khakestari = "\u001B[48;5;243m";
    private final String ANSIF_Reset = "\u001B[49m";
    private final String BLACK = "\u26AA";
    private final String WHITE = "\u26AB";

    public Map(int size,int size1) {
        map = new char[size][size1];
        this.size = size;
        this.size1=size1;
        visualmap = new char[size * 4][size1 * 8];
    }

    protected char[][] getMap() {
        return map;
    }

    protected void first() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = ' ';
            }
        }
    }

    protected void turn(int jahat) {
        char[][] test = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                test[i][j] = map[i][j];
            }
        }
        help(jahat, test, map);
    }

    protected void setMap(char[][] map) {
        this.map = map;
    }

    protected void updateVisualBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size1; j++) {
                visualmap[i * 3 + 2][j * 7 + 5] = map[i][j];

            }

        }
    }

    protected void setVisualBoard() {
        first();
        for (int i = 0; i <= size * 3; i++) {
            for (int j = 0; j <= size1 * 7; j++) {
                if (i % 3 == 0 && !( j % 7 == 0 )) {

                    visualmap[i][j] = '-';
                } else if (( i % 3 == 0 ) && ( j % 7 == 0 )) {
                    visualmap[i][j] = '|';
                } else if (!( i % 3 == 0 ) && ( j % 7 == 0 )) {
                    visualmap[i][j] = '|';
                } else {
                    visualmap[i][j] = ' ';
                }
            }

        }
    }

    protected void show() {
        int counter = 0;
        int counter1 = 1;
        System.out.print("   ");
        for (int k = 0; k < size1 * 7 + 4; k++) {
            System.out.print("_");
        }
        System.out.println();
        System.out.print("   ");
        for (int k = 0; k < size1 * 7 + 4; k++) {
            System.out.print("_");
        }
        System.out.println();
        for (int i = 0; i <= size * 3; i++) {
            System.out.print("|| ");
            for (int j = 0; j <= size1* 7; j++) {
                if(j%21==0&&j!=0&&j!=size1*7)
                    System.out.print("|| ");
                if (visualmap[i][j] == ' ') {
                    System.out.print(ANSIF_ghermez + ' ' + ANSIF_Reset);
                } else if (visualmap[i][j] == 'r') {
                    System.out.print(ANSIF_ghermez + WHITE + ANSIF_Reset);
                } else if (visualmap[i][j] == 'b') {
                    System.out.print(ANSIF_ghermez + BLACK + ANSIF_Reset);
                } else {
                    System.out.print(ANSIF_khakestari + ANSI_pink + " " + ANSIF_Reset + ANSI_RESET);
                }
            }
            System.out.print(" || ");
            System.out.println();
        }
        System.out.print("   ");
        for (int k = 0; k < size1 * 7 + 4; k++) {
            System.out.print("_");
        }
        System.out.println();
        System.out.print("   ");
        for (int k = 0; k < size1 * 7 + 4; k++) {
            System.out.print("_");
        }
        System.out.println();
        System.out.println("\n");
    }

    protected boolean turnCheck(int jahat) {
        char[][] test = new char[size][size];
        for (int i = 0; i < size; i++) System.arraycopy(map[i], 0, test[i], 0, size);
        char[][] test1 = new char[size][size];
        for (int i = 0; i < size; i++) System.arraycopy(map[i], 0, test1[i], 0, size);
        help(jahat, test, test1);
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (test1[i][j] != test[i][j])
                    return false;
            }
        }
        return true;
    }

    private void help(int jahat, char[][] test, char[][] test1) {
        if (jahat == 1) {
            int a = 0;
            int b = 0;
            for (int i = size - 1; i >= 0; i--) {
                for (int j = 0; j < size; j++) {
                    test1[j][i] = test[a++][b++];
                }
            }
        } else if (jahat == 2) {
            int a = 0;
            int b = 0;
            for (int i = 0; i < size; i++) {
                for (int j = size - 1; j >= 0; j--) {
                    test1[j][i] = test[a++][b++];
                }
            }
        }
    }

    protected void put(int number, char color) {
        if(check(number)){
        int row = number / 3;
        int column = number % 3;

        map[row][column] = color;
    }
        updateVisualBoard();
    }

    protected void setSize(int size) {
        this.size = size;
    }

    protected void setSize1(int size1) {
        this.size1 = size1;
    }

    protected boolean check(int number) {
        int row = number / 3;
        int column = number % 3;
        if (map[row][column] == ' ')
            return true;
        return false;
    }

}