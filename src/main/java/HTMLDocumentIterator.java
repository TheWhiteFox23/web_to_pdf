import org.jsoup.nodes.Document;

public interface HTMLDocumentIterator {
    public void iterate(Document document, Processor processor);
}
