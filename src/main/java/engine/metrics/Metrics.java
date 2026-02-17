package engine.metrics;

import java.util.concurrent.atomic.AtomicInteger;

public class Metrics {

    public static AtomicInteger success = new AtomicInteger();
    public static AtomicInteger failure = new AtomicInteger();

    public static void report() {
        System.out.println("Success: " + success.get() +
                           " | Failure: " + failure.get());
    }
}
