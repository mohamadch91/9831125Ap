public class Mohre {
    private int x;
    private int y;
    private String color;

    public Mohre(int x, char y, int color) {
        this.x = x - 1;
        if (color == 0) {
            this.color = "white";

        } else
            this.color = "black";
        switch (y) {
            case 'A':
                this.y = 0;
                break;
            case 'B':
                this.y = 1;
                break;
            case 'C':
                this.y = 2;
                break;
            case 'D':
                this.y = 3;
                break;
            case 'E':
                this.y = 4;
                break;
            case 'F':
                this.y = 5;
                break;
            case 'G':
                this.y=6;
                break;
            case 'H':
                this.y=7;
                break;
        }
    }
    public void changeColor(){
        if(color.equals("white")){
            color="black";
        }
        else{
            color="white";
        }
    }

}