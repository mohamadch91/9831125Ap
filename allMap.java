public class allMap extends Map {
    private char[][] big1;
    private char[][] big2;
    private char[][] big3;
    private char[][] big4;
    private int size;

    public allMap(int size) {
        super(size,size);
        big1 = new char[size][size];
        big2 = new char[size][size];
        big3 = new char[size][size];
        big4 = new char[size][size];
        this.size = size;
    }

    @Override
    protected void first() {
        super.setMap(big1);
        super.first();
        big1 = super.getMap();
        super.setMap(big2);
        super.first();
        big2 = super.getMap();
        super.setMap(big3);
        super.first();
        big3 = super.getMap();
        super.setMap(big4);
        super.first();
        big4 = super.getMap();
    }

    public void turn(int jahat, int makan) {
        switch (makan) {
            case 1:
                super.setMap(big1);
                super.turn(jahat);
                big1 = super.getMap();
                break;
            case 2:
                super.setMap(big2);
                super.turn(jahat);
                big2 = super.getMap();
                break;
            case 3:
                super.setMap(big3);
                super.turn(jahat);
                big3 = super.getMap();
                break;
            case 4:
                super.setMap(big4);
                super.turn(jahat);
                big4 = super.getMap();
                break;
        }
    }
    public boolean turnCheck(int jahat,int makan){
        switch (makan) {
            case 1:
                super.setMap(big1);
               if(super.turnCheck(jahat))
                   return true;
                big1 = super.getMap();
                break;
            case 2:
                super.setMap(big2);
                if(super.turnCheck(jahat))
                    return true;
                big2 = super.getMap();
                break;
            case 3:
                super.setMap(big3);
                if(super.turnCheck(jahat))
                    return true;
                big3 = super.getMap();
                break;
            case 4:
                super.setMap(big4);
                if(super.turnCheck(jahat))
                    return true;
                big4 = super.getMap();
                break;
        }
        return false;

    }
    public char[][] merge() {
        char[][] all = new char[size * 2][size * 2];
        int counter = 0;
        int counter1 = 0;
        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {
                all[i][j] = big1[i][j];
            }
            for (int j = size; j < size * 2; j++) {
                all[i][j] = big2[counter][counter1++];
            }
            counter++;
            counter1 = 0;

        }
        counter = 0;
        counter1 = 0;
        int c1 = 0;
        int c2 = 0;
        for (int i = size; i < 2 * size; i++) {
            counter1 = 0;
            for (int j = 0; j < size; j++) {
                all[i][j] = big3[counter][counter1++];

            }
            counter++;
            for (int j = size; j < 2 * size; j++) {
                all[i][j] = big4[c1][c2++];

            }
            c1++;
            c2 = 0;
        }
        return all;
    }
    public char[][] mergeShow(int m){
        char[][] all = new char[size][size * 2];
        int counter = 0;
        int counter1 = 0;
        if(m==1) {
            for (int i = 0; i < size; i++) {

                for (int j = 0; j < size; j++) {
                    all[i][j] = big1[i][j];
                }
                for (int j = size; j < size * 2; j++) {
                    all[i][j] = big2[counter][counter1++];
                }
                counter++;
                counter1 = 0;


            }
            return all;
        }
        else{
        counter = 0;
        counter1 = 0;
        int c1 = 0;
        int c2 = 0;
        for (int i = 0; i < size; i++) {
            counter1 = 0;
            for (int j = 0; j < size; j++) {
                all[i][j] = big3[counter][counter1++];

            }
            counter++;
            for (int j = size; j < 2 * size; j++) {
                all[i][j] = big4[c1][c2++];

            }
            c1++;
            c2 = 0;
        }}
        return all;

    }
    public boolean validPut(int makan,int number) {
        int check = 0;
        if (number > 0 && number <= 9)
            check++;
        switch (makan) {
            case 1:
                super.setMap(big1);
                if(super.check(number))
                    check++;
                big1 = super.getMap();
                break;
            case 2:
                super.setMap(big2);
                if(super.check(number))
                    check++;
                big2 = super.getMap();
                break;
            case 3:
                super.setMap(big3);
                if(super.check(number))
                    check++;
                big3 = super.getMap();
                break;
            case 4:
                super.setMap(big4);
                if(super.check(number))
                    check++;
                big4 = super.getMap();
                break;
        }
        if(check==2)
            return true;
        return false;

    }

    public void putall(int makan,int number,char color){
        switch (makan) {
            case 1:
                super.setMap(big1);
                super.put(number, color);
                big1 = super.getMap();
                break;
            case 2:
                super.setMap(big2);
                super.put(number, color);
                big2 = super.getMap();
                break;
            case 3:
                super.setMap(big3);
                super.put(number, color);
                big3 = super.getMap();
                break;
            case 4:
                super.setMap(big4);
                super.put(number, color);
                big4 = super.getMap();
                break;
        }
    }
    public boolean win(char color){
        char[][] test=new char[size*2][size*2];
        test=merge();
        int counter=1;
        for (int i = 0; i <size*2 ; i++) {
            for (int j = 0; j <size ; j++) {
                if(test[i][j]==color){
                    for (int k = j; k <2*size ; k++) {
                        if(test[i][k]==color)
                            counter++;
                    }
                    if(counter==5)
                        return true;
                    counter=1;
                    for (int k = i; k <2*size ; k++) {
                        if(test[k][j]==color)
                            counter++;
                    }
                    if(counter==5)
                        return true;
                    counter=1;
                    int c=i;
                    int c1=j;
                    while (c<2*size&&c1<2*size) {
                        if (test[c][c1] == color)
                            counter++;
                        c1++;
                        c++;
                    }
                    if(counter==5)
                        return true;
                }
            }
        }
        return false;
    }

    @Override
    protected void show() {
        setSize(size);
        setSize1(size*2);
        setMap(mergeShow(1));
        super.updateVisualBoard();
        super.show();
        setMap(mergeShow(2));
        updateVisualBoard();
        super.show();
    }
}
