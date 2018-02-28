package function;

/**
 * @author Kwanil, Lee
 */
public interface ExceptionalFunction<T,R,E extends Exception> {
    R apply(T input) throws E;
}