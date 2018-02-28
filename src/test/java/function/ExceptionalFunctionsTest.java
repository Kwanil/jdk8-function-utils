package function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.function.Supplier;

import static function.ExceptionalFunctions.unchecked;
import static java.util.stream.Collectors.toList;
import static org.junit.Assert.assertTrue;


public class ExceptionalFunctionsTest {

    @Test
    public void testUncheckedFunction() throws Exception{
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<Future<Object>> futureList = executorService.invokeAll(Arrays.<Callable<Object>>asList(() -> 1, ()->"a"));
        List<Supplier<Object>> list = futureList.stream().map(i -> unchecked(i::get)).collect(toList());
        assertTrue(list.size() > 0);
    }
}