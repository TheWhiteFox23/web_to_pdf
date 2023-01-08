import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PDFBoxProcessor implements Processor<Node>{
    private Document document;
    //private BaseFont openSans;

    public PDFBoxProcessor() {


        /*try {
            openSans = BaseFont.createFont(
                    "src/main/resources/fonts/OpenSans-VariableFont_wdth,wght.ttf",
                    "cp852",
                    BaseFont.EMBEDDED);
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    public void createFile(String file) throws FileNotFoundException, DocumentException {
        this.document = new Document();
        //todo made modular and move logic to separate entity
        PdfWriter.getInstance(document, new FileOutputStream("testExport.pdf"));
        //todo font encoding options and font management
        //todo set node processing
        this.document.open();
    }


    //TODO image and list processing
    @Override
    public void process(Node node) {
        //System.out.println(node.getClass());
        if(node instanceof Element){
            String tag = ((Element)node).tagName();
            switch (tag){
                case "p":{
                    tryAddParagraph(((Element)node).text(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 16, BaseColor.BLACK));
                    break;
                }
                case "h1":{
                    tryAddPhrase(((Element)node).text(), FontFactory.getFont(FontFactory.TIMES_ROMAN, 22, BaseColor.BLACK));
                    break;
                }
                case "h2":{
                    tryAddPhrase(((Element)node).text(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 20, BaseColor.BLACK));
                    break;
                }
                case "h3":{
                    tryAddPhrase(((Element)node).text(),FontFactory.getFont(FontFactory.TIMES_ROMAN, 18, BaseColor.BLACK));
                    break;
                }
            }
        }
    }

    public void save(){
        document.close();
    }

    private void tryAddParagraph(String text, Font font){
        try {
            //System.out.println(text);
            document.add(new Paragraph(text, font));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private void tryAddPhrase(String text, Font font){
        try {
            //System.out.println(text);
            document.add(new Phrase(text, font));
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

}
