package engine.reader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.concurrent.BlockingQueue;

public class FileStreamer {

    public void stream(String file, BlockingQueue<String> queue) throws Exception {

        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;

        while ((line = br.readLine()) != null) {
            queue.put(line);
        }

        br.close();
    }
}
