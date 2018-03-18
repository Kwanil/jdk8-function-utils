package function;

import java.util.function.Consumer;
import java.util.function.Function;

public class Consumers {
    private Consumers() {
        throw new UnsupportedOperationException("Consumers initialization is not supported");
    }

    public static <T,E extends Throwable> Consumer<T> unchecked(ExceptionalConsumer<T,E> consumer) {
        return unchecked(consumer, RuntimeException::new);
    }

    public static <T,E extends Throwable> Consumer<T> unchecked(ExceptionalConsumer<T,E> consumer, Function<Throwable, ? extends RuntimeException> exceptionSupplier) {
        return v -> {
            try {
                consumer.accept(v);
            } catch (Throwable e) {
                throw exceptionSupplier.apply(e);
            }
        };
    }

    public static <T,E extends Throwable> Consumer<T> defaultIfException(ExceptionalConsumer<T,E> consumer, Consumer<Throwable> onError) {
        return v -> {
            try {
                consumer.accept(v);
            } catch (Throwable e) {
                onError.accept(e);
            }
        };
    }

    public static <T> Consumer<T> doNothing(){
        return e-> {};
    }
}
