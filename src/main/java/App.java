import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;

import java.io.IOException;
import java.lang.annotation.Documented;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.EventListener;
import java.util.List;

public class App {
    public static void main(String[] args) throws IOException, InterruptedException {
        Document html = Jsoup.connect("https://medium.seznam.cz/clanek/eduin-systemove-priciny-uzkosti-ve-skolstvi-1602#dop_ab_variant=867130&dop_source_zone_name=hpfeed.sznhp.box&dop_vert_ab=867130&dop_vert_id=leg0&dop_req_id=GuVU5dm0dZ8-202301071247&dop_id=19099625&utm_source=www.seznam.cz&utm_medium=sekce-z-internetu").get();
        DocumentToPDFParser parser = new PlainBodyParser();
        parser.parse(html);

    }

    public static void searchDocument(Document document, NodeProcessor processor){
        document.childNodes().forEach(node -> searchNode(node, processor));
    }

    public static void searchNode(Node node, NodeProcessor nodeProcessor){
        nodeProcessor.process(node);
        node.childNodes().forEach(n -> searchNode(n, nodeProcessor));
    }

    public static interface NodeProcessor{
        public void process(Node node);
    }
}
