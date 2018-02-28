package function;

/**
 * @author Kwanil, Lee
 */
public interface ExceptionalConsumer<T,E extends Exception> {
    void accept(T value) throws E;
}
