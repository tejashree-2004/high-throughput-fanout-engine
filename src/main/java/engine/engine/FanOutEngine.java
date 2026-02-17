package engine.engine;

import java.util.concurrent.*;

import engine.sink.MockSink;
import engine.transform.JsonTransformer;
import engine.transform.Transformer;

public class FanOutEngine {

    public void start(BlockingQueue<String> queue) {

        ExecutorService pool = Executors.newFixedThreadPool(4);

        MockSink rest = new MockSink("REST", 50);
        MockSink db = new MockSink("DB", 100);

        Transformer transformer = new JsonTransformer();

        while (true) {
            try {
                String record = queue.take();

                pool.submit(() -> {
                    String out = transformer.transform(record);
                    rest.send(out);
                    db.send(out);
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
