package mit6005.w1;

public interface ImList<E> {
    public static <E> ImList<E> empty() {
        return new Empty<>();
    }

    public ImList<E> cons(E e);

    public E first();

    public ImList<E> rest();
}
