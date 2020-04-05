import java.util.Objects;

public class Mohre {
    private int x;
    private int y;
    private char color;

    public Mohre(int x, char y, char color) {
        this.x = x - 1;
        this.color=color;
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
            default:
                this.y=-1;
                break;
        }
    }
    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Mohre)) return false;
        Mohre mohre = (Mohre) o;
        return x == mohre.x &&
                y == mohre.y ;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}