import com.itextpdf.text.pdf.qrcode.WriterException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Responsible for interpreting the run arguments and response to them
 */
public class Interpreter {
    private String helpKey = "-help";
    private String fileKey = "-file";
    private String urlKey = "-url";
    private String helpText = "Use -file and -url args to specify file name and url to convert.\n" +
            "Example: -file final/file/name.pdf -url https://www.example.com";
    private String validFileExtension = ".pdf";


    public void interpret(String[] args) throws WebToPDFConversionException, FileNotFoundException {
        if(containsKey(helpKey, args)){
            manageHelpRequest();
        }else if(containsKey(fileKey, args) && containsKey(urlKey, args)){
            String file = getValue(fileKey, args);
            String url = getValue(urlKey, args);
            if(isValidFileName(file, validFileExtension)){
                System.out.println("Converting...");
                convert(file, url);
                System.out.println("Conversion finished. Web was successfully converted into file " + file);
            }else{
                throw new WebToPDFConversionException("File name is nod valid");
            }
        }else{
            manageHelpRequest();
        }
    }

    private void manageHelpRequest() {
        System.out.println(helpText);
    }


    private void convert(String file, String url) throws WebToPDFConversionException, FileNotFoundException {
        Document html;
        try {
            html = Jsoup.connect(url).get();
        }catch (Exception e){
            throw new WebToPDFConversionException("Invalid URL of the web");
        }
        doConvert(html, file);
    }


    private void doConvert(Document html, String file) throws FileNotFoundException {
        HTMLDocumentIterator iterator = new InOrderIterator();
        PDFProcessor processor = new PDFITextProcessor();
        processor.createFile(file);
        iterator.iterate(html,processor);
        processor.save();
    }

    private boolean isValidFileName(String fileName, String extension){
        String[] invalidChars = "< > : \" | ? *".split(" ");
        for(String s : invalidChars){
            if(fileName.contains(s)) return false;
        }
        return getFileExtension(fileName).equals(extension.toLowerCase().replace(".", ""));
    }

    private String getFileExtension(String filePath) {
        Pattern pattern = Pattern.compile("\\.[a-zA-z0-9]+\\s*$");
        Matcher matcher = pattern.matcher(filePath);
        if (matcher.find()) {
            return matcher.group(0).toLowerCase().replace('.', ' ').trim();
        }
        return null;
    }


    private String getValue(String key, String[] args){
        for(int i = 0; i< args.length; i++){
            if(args[i].toLowerCase().equals(key.toLowerCase()) && i+1< args.length){
                return args[i+1];
            }
        }
        return null;
    }


    private boolean containsKey(String key, String[] args){
        for(String s: args){
            if(s.toLowerCase().equals(key.toLowerCase()))return true;
        }
        return false;
    }


}
