import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfWriter;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFBoxProcessor implements PDFProcessor{
    private Document document;
    private String font;
    private BaseColor baseColor;

    public PDFBoxProcessor() {
        this.font = FontFactory.TIMES_ROMAN;
        this.baseColor = BaseColor.BLACK;
    }

    @Override
    public void createFile(String file) throws FileNotFoundException {
        this.document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream(file));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        this.document.open();
    }


    @Override
    public void process(Node node) {
        if(node instanceof Element){
            String tag = ((Element)node).tagName();
            switch (tag){
                case "p":{
                    tryAddParagraph(((Element)node).text(), FontFactory.getFont(font, 16, baseColor));
                    break;
                }
                case "h1":{
                    tryAddPhrase(((Element)node).text(), FontFactory.getFont(font, 22, baseColor));
                    break;
                }
                case "h2":{
                    tryAddPhrase(((Element)node).text(),FontFactory.getFont(font, 20, baseColor));
                    break;
                }
                case "h3":{
                    tryAddPhrase(((Element)node).text(),FontFactory.getFont(font, 18, baseColor));
                    break;
                }
            }
        }
    }

    @Override
    public void save(){
        document.close();
    }

    private void tryAddParagraph(String text, Font font){
        try {
            document.add(new Paragraph(text, font));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void tryAddPhrase(String text, Font font){
        try {
            document.add(new Phrase(text, font));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }
}
