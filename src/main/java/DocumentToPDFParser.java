import org.jsoup.nodes.Document;

import java.lang.annotation.Documented;

public interface DocumentToPDFParser {
    public void parse(Document documented);
}
