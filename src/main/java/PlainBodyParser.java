import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/**
 * Parse text elements of the html document, do not distinguish fonts and headlines
 */
public class PlainBodyParser implements DocumentToPDFParser{
    @Override
    public void parse(Document documented) {
        searchNode(documented.body(), plainNodeProcessor);
    }

    private void searchNode(Node node, Processor<Node> processor){
        processor.process(node);
        node.childNodes().forEach(n -> searchNode(n, processor));
    }

    private Processor<Node> plainNodeProcessor = new Processor<Node>() {
        @Override
        public void process(Node node) {
            if(node instanceof Element){
                String tag = ((Element)node).tagName();
                switch (tag){
                    case "p","h1","h2","h3"->{
                        System.out.println(((Element)node).text());
                    }
                }
            }
        }
    };
}
