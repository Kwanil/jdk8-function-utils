package function;

import java.util.Objects;
import java.util.function.Function;


public class Functions {
    private Functions() {
        throw new UnsupportedOperationException("Functions initialization is not supported");
    }

    public static <T,R,E extends Throwable> Function<T,R> unchecked(ExceptionalFunction<T,R,E> function) {
        return unchecked(function, RuntimeException::new);
    }

    public static <T,R,E extends Throwable> Function<T,R> unchecked(ExceptionalFunction<T,R,E> function, Function<Throwable, ? extends RuntimeException> exceptionSupplier) {
        return input -> {
            try {
                return function.apply(input);
            } catch (Throwable e) {
                throw exceptionSupplier.apply(e);
            }
        };
    }

    public static <T,R,E extends Throwable> Function<T,R> defaultIfException(ExceptionalFunction<T,R,E> function, Function<Throwable, ? extends R> onError) {
        return input -> {
            try {
                return function.apply(input);
            } catch (Throwable e) {
                return onError.apply(e);
            }
        };
    }

    public static <T,R> Function<T, R> cast(Class<R> clazz) {
        return Objects.requireNonNull(clazz)::cast;
    }
}
