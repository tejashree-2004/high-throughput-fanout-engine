package engine.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class RecordQueue {

    public static BlockingQueue<String> create(int size) {
        return new ArrayBlockingQueue<>(size);
    }
}

