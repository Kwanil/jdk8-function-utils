package function;

import java.util.function.Function;
import java.util.function.Supplier;

public class Suppliers {
    private Suppliers() {
        throw new UnsupportedOperationException("Suppliers initialization is not supported");
    }

    public static <T,E extends Throwable> Supplier<T> unchecked(ExceptionalSupplier<T,E> supplier) {
        return unchecked(supplier, RuntimeException::new);
    }

    public static <T,E extends Throwable> Supplier<T> unchecked(ExceptionalSupplier<T,E> supplier, Function<Throwable, ? extends RuntimeException> exceptionSupplier) {
        return () -> {
            try {
                return supplier.get();
            } catch (Throwable e) {
                throw exceptionSupplier.apply(e);
            }
        };
    }

    public static <T,E extends Throwable> Supplier<T> defaultIfException(ExceptionalSupplier<T,E> supplier, Function<Throwable, T> onError) {
        return () -> {
            try {
                return supplier.get();
            } catch (Throwable e) {
                return onError.apply(e);
            }
        };
    }
}
