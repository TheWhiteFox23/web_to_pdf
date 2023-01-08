import com.itextpdf.text.DocumentException;
import org.jsoup.nodes.Node;

import java.io.FileNotFoundException;

/**
 * Specialized processor used for document to PDF conversion
 */
public interface PDFProcessor extends Processor<Node> {
    /**
     * Create file, which will used as destination of the conversion process
     * @param file name of the destination file
     * @throws FileNotFoundException
     */
    public void createFile(String file) throws FileNotFoundException;

    /**
     * Process the element node
     * @param node to process
     */
    void process(Node node);

    /**
     * Finalize conversion process and create pdf file with converted content of the web page
     */
    public void save();
}
