import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;


class ArgumentParserTest {
    private ArgumentParser argumentParser;

    @BeforeEach
    void setUp() {
        this.argumentParser = new ArgumentParser();
    }

    @Nested
    class getValueTest{
        @Test
        void simpleSeparatedValue(){
            String validValue = "validValue$6$";
            argumentParser.setArgs(new String[]{"-test",validValue, "-test2","invalidValue"});
            String result = argumentParser.getValue("-test");
            Assertions.assertEquals(validValue, result);
        }

        @Test
        void caseInsensitivityTest(){
            String validValue = "validValue$6$";
            argumentParser.setArgs(new String[]{"-TeSt",validValue, "-test2","invalidValue"});
            String result = argumentParser.getValue("-tEsT");
            Assertions.assertEquals(validValue, result);
        }

        @Test
        void simpleEndOfTheArr(){
            argumentParser.setArgs(new String[]{"-test2","invalidValue","-test"});
            String result = argumentParser.getValue("-tEsT");
            Assertions.assertNull(result);
        }
    }

}