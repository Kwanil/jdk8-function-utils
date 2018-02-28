package function;

/**
 * @author Kwanil, Lee
 */
public interface ExceptionalSupplier<T,E extends Exception> {
    T get() throws E;
}
