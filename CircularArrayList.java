import java.util.ArrayList;

/**
 * make circular Array List.
 * @author Mohammad
 * @param <E> object.
 */

public class CircularArrayList<E> extends ArrayList<E>
{
    private static final long serialVersionUID = 1L;
@Override
    public E get(int index)
    {
        // index between size
        index=index%size();
        switch (index){
            case -1:
                index=3;
                break;
            case-2:
                index=2;
                break;
            case -3:
                index=1;
                break;
        }
        return super.get(index);
    }
}