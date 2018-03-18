package function;

import java.util.Arrays;
import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public class Predicates {
    private Predicates() {
        throw new UnsupportedOperationException("Predicates initialization is not supported");
    }

    public static <T, E extends Throwable> Predicate<T> unchecked(ExceptionalPredicate<T,E> predicate) {
        return unchecked(predicate, RuntimeException::new);
    }

    public static <T,E extends Throwable> Predicate<T> unchecked(ExceptionalPredicate<T,E> predicate, Function<Throwable, ? extends RuntimeException> exceptionSupplier) {
        return input -> {
            try {
                return predicate.test(input);
            } catch (Throwable e) {
                throw exceptionSupplier.apply(e);
            }
        };
    }

    public static <T,E extends Throwable> Predicate<T> defaultIfException(ExceptionalPredicate<T,E> predicate, Function<Throwable, Boolean> onError) {
        return input -> {
            try {
                return predicate.test(input);
            } catch (Throwable e) {
                return onError.apply(e);
            }
        };
    }

    public static <T> Predicate<T> True() {
        return t -> true;
    }

    public static <T> Predicate<T> False() {
        return t -> false;
    }

    public static <T> Predicate<T> instanceOf(Class<? extends T> clazz) {
        return Objects.requireNonNull(clazz)::isInstance;
    }

    public static <T> Predicate<Class<T>> isAssignableFrom(Class<? super T> clazz) {
        return Objects.requireNonNull(clazz)::isAssignableFrom;
    }

    public static <T> Predicate<T> contains(T ... objects) {
        return Arrays.asList(objects)::contains;
    }

}
