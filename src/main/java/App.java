import java.io.FileNotFoundException;


public class App {
    public static void main(String[] args)  {
        Interpreter interpreter = new Interpreter();
        try {
            interpreter.interpret(args);
        } catch (WebToPDFConversionException e) {
            System.err.println(e.getMessage());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

}
