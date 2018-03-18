package function;

import java.util.Objects;

/**
 * @author Kwanil, Lee
 */
@FunctionalInterface
public interface ExceptionalConsumer<T,E extends Throwable> {
    void accept(T value) throws E;

    default ExceptionalConsumer<T, E> andThen(ExceptionalConsumer<? super T, ? extends E> after) throws E {
        Objects.requireNonNull(after);
        return (T t) -> { accept(t); after.accept(t); };
    }
}
