package function;

import java.util.Objects;

/**
 * @author Kwanil, Lee
 */
@FunctionalInterface
public interface ExceptionalFunction<T,R,E extends Throwable> {
    R apply(T input) throws E;

    default <V> ExceptionalFunction<V, R, E> compose(ExceptionalFunction<? super V, ? extends T, ?  extends E> before) throws E {
        Objects.requireNonNull(before);
        return (V v) -> apply(before.apply(v));
    }

    default <V> ExceptionalFunction<T, V, E> andThen(ExceptionalFunction<? super R, ? extends V, ? extends E> after) throws E {
        Objects.requireNonNull(after);
        return (T t) -> after.apply(apply(t));
    }

    static <T, E extends Exception> ExceptionalFunction<T, T, E> identity() throws E {
        return t -> t;
    }
}