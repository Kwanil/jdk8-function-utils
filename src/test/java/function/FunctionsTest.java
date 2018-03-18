package function;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static function.Functions.unchecked;
import static java.util.stream.Collectors.toList;

public class FunctionsTest {
    @Test
    public void testUnchecked() throws Exception {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        List<Future<Object>> futureList = executorService.invokeAll(Arrays.asList(() -> 1, ()->"a"));
        List<Object> objects = futureList.stream().map(unchecked(Future::get)).collect(toList());
        System.out.println(objects);
    }
}
