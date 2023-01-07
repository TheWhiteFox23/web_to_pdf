import org.w3c.dom.Node;

public interface Processor<T> {
    public void process(T node);
}
