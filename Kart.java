import java.util.Objects;

public class Kart {
    private int number;
    // 10 for skip 11 for reverse  12 for draw and -1 for wild draw ane -2 for wild
    private char color;
    private int point;
    public Kart(int number,char color){
        this.number=number;
        this.color=color;
        setPoint();
    }

    public char getColor() {
        return color;
    }

    public int  getPoint() {
        return point;
    }

    public int getNumber() {
        return number;
    }

    private void setPoint() {
        if(color!='b'){
            if(number==10||number==11||number==12) point = 20;
            else point = number;
        }
        else
            point = 50;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!( o instanceof Kart )) return false;
        Kart kart = (Kart) o;
        return getNumber() == kart.getNumber() &&
                getColor() == kart.getColor();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getNumber(), getColor());
    }
    public void show(){
        
    }
}
