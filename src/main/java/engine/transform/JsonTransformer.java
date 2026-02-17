package engine.transform;

public class JsonTransformer implements Transformer {

    public String transform(String input) {
        return "{\"data\":\"" + input + "\"}";
    }
}
