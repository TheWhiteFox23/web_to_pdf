/**
 * Exception thrown during conversion of the web page into the PDF file
 */
public class WebToPDFConversionException extends Exception{
    public WebToPDFConversionException(String errorMessage) {
        super(errorMessage);
    }
}
