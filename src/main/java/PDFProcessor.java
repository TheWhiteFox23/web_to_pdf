import com.itextpdf.text.DocumentException;
import org.jsoup.nodes.Node;

import java.io.FileNotFoundException;

public interface PDFProcessor extends Processor<Node> {
    public void createFile(String file) throws FileNotFoundException;
    void process(Node node);
    public void save();
}
