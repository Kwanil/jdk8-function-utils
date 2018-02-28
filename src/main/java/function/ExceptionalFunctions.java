package function;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Kwanil, Lee
 */
public class ExceptionalFunctions {

    private ExceptionalFunctions() {
        throw new UnsupportedOperationException("ExceptionalFunctions initialization is not supported");
    }

    public static <T,R,E extends Exception> Function<T,R> unchecked(ExceptionalFunction<T,R,E> function) {
        return unchecked(function, RuntimeException::new);
    }

    public static <T,R,E extends Exception> Function<T,R> unchecked(ExceptionalFunction<T,R,E> function, Supplier<? extends RuntimeException> exceptionSupplier) {
        return input -> {
            try {
                return function.apply(input);
            } catch (Exception e) {
                throw exceptionSupplier.get();
            }
        };
    }

    public static <T,E extends Exception> Supplier<T> unchecked(ExceptionalSupplier<T,E> supplier) {
        return unchecked(supplier, RuntimeException::new);
    }

    public static <T,E extends Exception> Supplier<T> unchecked(ExceptionalSupplier<T,E> supplier, Supplier<? extends RuntimeException> exceptionSupplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Exception e) {
                throw exceptionSupplier.get();
            }
        };
    }

    public static <T,E extends Exception> Consumer<T> unchecked(ExceptionalConsumer<T,E> consumer) {
        return unchecked(consumer, RuntimeException::new);
    }

    public static <T,E extends Exception> Consumer<T> unchecked(ExceptionalConsumer<T,E> consumer, Supplier<? extends RuntimeException> exceptionSupplier) {
        return v -> {
            try {
                consumer.accept(v);
            } catch (Exception e) {
                throw exceptionSupplier.get();
            }
        };
    }
}
