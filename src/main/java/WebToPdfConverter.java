import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.FileNotFoundException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Converts web pages to pdf files
 */
public class WebToPdfConverter {

    /**
     * Convert html web page on given URL into the pdf filw
     * @param url URL of the page to convert
     * @param file Path to the destination file
     * @throws WebToPDFConversionException
     * @throws FileNotFoundException
     */
    public void convert(String url, String file) throws WebToPDFConversionException, FileNotFoundException {
        if(!isValidFileName(file, ".pdf"))throw new WebToPDFConversionException("Invalid file name");

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

    /**
     * Validate file name is valid. Check file extension and content of invalid symbols
     * @param fileName
     * @param extension
     * @return
     */
    private boolean isValidFileName(String fileName, String extension){
        String[] invalidChars = "< > : \" | ? *".split(" ");
        for(String s : invalidChars){
            if(fileName.contains(s)) return false;
        }
        return getFileExtension(fileName).equals(extension.toLowerCase().replace(".", ""));
    }

    /**
     * Return file extension of the file, based on the file name
     * @param filePath String representation of the file path
     * @return string representation of the extension or null
     */
    private String getFileExtension(String filePath) {
        Pattern pattern = Pattern.compile("\\.[a-zA-z0-9]+\\s*$");
        Matcher matcher = pattern.matcher(filePath);
        if (matcher.find()) {
            return matcher.group(0).toLowerCase().replace('.', ' ').trim();
        }
        return null;
    }

}
