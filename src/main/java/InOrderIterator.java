import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;

/**
 * Iterator used to in order iteration through the JSoup Documet
 */
public class InOrderIterator implements HTMLDocumentIterator {
    @Override
    public void iterate(Document document, Processor processor) {
        document.childNodes().forEach(n -> searchNode(n, processor));
    }

    private void searchNode(Node node, Processor processor){
        processor.process(node);
        node.childNodes().forEach(n -> searchNode(n, processor));
    }
}
