package pl.mbadziong.jvm;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static junit.framework.Assert.assertTrue;

public class AppTest {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss dd.MM.yyyy");
    private final int threadPool = 1000;

    @Test
    public void testSimpleDateFormat() {
        ExecutorService executor = Executors.newFixedThreadPool(threadPool);
        final boolean[] result = {true};
        for (int i = 0; i < 10000; i++) {
            executor.submit(() -> {
                try {
                    simpleDateFormat.parse("12:12:12 12.12.2012");
                } catch (Exception exception) {
                    result[0] = false;
                }
            });
        }
        assertTrue(result[0]);

    }
}
