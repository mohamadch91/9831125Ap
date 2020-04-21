import java.util.ArrayList;

public class CircularArrayList<E> extends ArrayList<E>
{
    private static final long serialVersionUID = 1L;
@Override
    public E get(int index)
    {
        index=index%size();

        return super.get(index);
    }
}