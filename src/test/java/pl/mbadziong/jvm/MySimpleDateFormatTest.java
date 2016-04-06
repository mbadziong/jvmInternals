package pl.mbadziong.jvm;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static junit.framework.Assert.assertTrue;

public class MySimpleDateFormatTest {

    private static final MySimpleDateFormat mySimpleDateFormat = new MySimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    private final int threadPool = 1000;

    @Test
    public void testMySimpleDateFormat() {
        ExecutorService executor = Executors.newFixedThreadPool(threadPool);
        final boolean[] result = {true};
        for (int i = 0; i < 10000; i++) {
            executor.submit(() -> {
                try {
                    mySimpleDateFormat.parse("12:12:12 12.12.2012");
                } catch (Exception exception) {
                    result[0] = false;
                }
                assertTrue(result[0]);
            });
        }
    }
}