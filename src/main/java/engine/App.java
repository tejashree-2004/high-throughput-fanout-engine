package engine;

import engine.engine.FanOutEngine;
import engine.metrics.Metrics;
import engine.queue.RecordQueue;
import engine.reader.FileStreamer;

import java.util.concurrent.*;

public class App {

    public static void main(String[] args) throws Exception {

        BlockingQueue<String> queue = RecordQueue.create(1000);

        new Thread(() -> {
            try {
                new FileStreamer().stream("input.txt", queue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

        ScheduledExecutorService timer =
                Executors.newSingleThreadScheduledExecutor();

        timer.scheduleAtFixedRate(Metrics::report, 5, 5, TimeUnit.SECONDS);

        new FanOutEngine().start(queue);
    }
}
