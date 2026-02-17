package engine.sink;

import com.google.common.util.concurrent.RateLimiter;
import engine.dlq.DLQ;
import engine.metrics.Metrics;

public class MockSink {

    private final String name;
    private final RateLimiter limiter;

    public MockSink(String name, double rate) {
        this.name = name;
        this.limiter = RateLimiter.create(rate);
    }

    public void send(String data) {

        for (int i = 0; i < 3; i++) {
            try {
                limiter.acquire();
                System.out.println(name + " → " + data);
                Metrics.success.incrementAndGet();
                return;

            } catch (Exception e) {
                Metrics.failure.incrementAndGet();
            }
        }

        DLQ.send(data);
    }
}

