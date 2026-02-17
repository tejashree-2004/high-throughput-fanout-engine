package engine.dlq;

public class DLQ {

    public static void send(String record) {
        System.out.println("DLQ → " + record);
    }
}

