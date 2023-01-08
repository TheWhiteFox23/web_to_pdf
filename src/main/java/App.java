import com.itextpdf.text.*;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Node;


import java.io.*;

import java.net.URL;


public class App {
    public static void main(String[] args) throws IOException, InterruptedException, DocumentException {
        String strURL = "https://medium.seznam.cz/clanek/eduin-systemove-priciny-uzkosti-ve-skolstvi-1602#dop_ab_variant=867130&dop_source_zone_name=hpfeed.sznhp.box&dop_vert_ab=867130&dop_vert_id=leg0&dop_req_id=GuVU5dm0dZ8-202301071247&dop_id=19099625&utm_source=www.seznam.cz&utm_medium=sekce-z-internetu";
        String URLen= "https://9to5answer.com/jsoup-character-encoding-issue";

        //TODO parameter parsing

        Document html = Jsoup.connect(URLen).get();
        HTMLDocumentIterator iterator = new InOrderIterator();
        PDFProcessor processor = new PDFBoxProcessor();
        processor.createFile("TestPdfExport.pdf");
        iterator.iterate(html,processor);
        processor.save();
    }

}
