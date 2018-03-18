package function;

/**
 * @author Kwanil, Lee
 */
@FunctionalInterface
public interface ExceptionalSupplier<T,E extends Throwable> {

    /**
     * Gets a result.
     *
     * @return a result
     */
    T get() throws E;
}
