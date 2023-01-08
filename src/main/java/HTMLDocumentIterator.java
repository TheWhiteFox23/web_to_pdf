import org.jsoup.nodes.Document;

/**
 * JSoup document iterator, use processor to process individual nodes
 */
public interface HTMLDocumentIterator {
    public void iterate(Document document, Processor processor);
}
