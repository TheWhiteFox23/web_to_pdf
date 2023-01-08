import java.io.*;


public class App {
    public static void main(String[] args)  {
        ArgumentParser argumentParser = new ArgumentParser(args);
        String url = argumentParser.getValue("-url");
        String fileName = argumentParser.getValue("-file");

        WebToPdfConverter converter = new WebToPdfConverter();
        System.out.println("Converting...");
        try {
            converter.convert(url, fileName);
        } catch (WebToPDFConversionException e) {
            System.out.println("Conversion failed, see exception message form more info");
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            System.out.println("Conversion failed, see exception message form more info");
            e.printStackTrace();
        }
        System.out.println("Web page has been successfully converted into the " + fileName);
    }

}
