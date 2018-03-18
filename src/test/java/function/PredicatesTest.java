package function;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class PredicatesTest {


    @Test
    public void test() {
        Object o = 1;
        Number number = Optional.of(o).filter(Predicates.instanceOf(Number.class)).map(Functions.cast(Number.class)).get();
        assertThat(number, is(1));
    }
}