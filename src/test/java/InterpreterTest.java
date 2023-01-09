import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


class InterpreterTest {
    private Interpreter interpreter;

    @BeforeEach
    void setUp() {
        this.interpreter = new Interpreter();
    }

    @Nested
    class getValueTest{
        @Test
        void simpleSeparatedValue() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            String validValue = "validValue$6$";
            String[] args = new String[]{"-test",validValue, "-test2","invalidValue"};
            String result = invokeGetFileExtension("-test", args);
            Assertions.assertEquals(validValue, result);
        }

        @Test
        void caseInsensitivityTest() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            String validValue = "validValue$6$";
            String[] args =  new String[]{"-TeSt",validValue, "-test2","invalidValue"};
            String result = invokeGetFileExtension("-tEsT", args);
            Assertions.assertEquals(validValue, result);
        }

        @Test
        void simpleEndOfTheArr() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            String[] args = new String[]{"-test2","invalidValue","-test"};
            String result = invokeGetFileExtension("-tEsT", args);
            Assertions.assertNull(result);
        }

        private String invokeGetFileExtension(String key, String[] args) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Method getValue = Interpreter.class.getDeclaredMethod("getValue", String.class, String[].class);
            getValue.setAccessible(true);
            Object response = getValue.invoke(interpreter, key, args);
            if(response != null)return (String) response;
            return null;
        }
    }

    @Nested
    class GetFileExtensionTest{

        @Test
        public void simpleValueAvi() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            String result = invokeGetFileExtension("someFileName.avi");
            Assertions.assertEquals("avi", result);
        }

        @Test
        public void upperCase() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            String result =  invokeGetFileExtension("someFileName.aVi");
            Assertions.assertEquals("avi", result);
        }

        @Test
        public void doubleExtension() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            String result =  invokeGetFileExtension("someFileName.exe.avi");
            Assertions.assertEquals("avi", result);
        }

        @Test
        public void whitespaceAfter() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            String result =  invokeGetFileExtension("someFileName.avi ");
            Assertions.assertEquals("avi", result);
        }

        @Test
        public void noExtension() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            String result =  invokeGetFileExtension("someFileName");
            Assertions.assertEquals(null, result);
        }

        private String invokeGetFileExtension(String filePath) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Method getFileExtension = Interpreter.class.getDeclaredMethod("getFileExtension", String.class);
            getFileExtension.setAccessible(true);
            Object response = getFileExtension.invoke(interpreter, filePath);
            if(response != null)return (String) response;
            return null;
        }
    }



    @Nested
    class isValidFileName{
        @Test
        void validFileName() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            boolean isValid = invokeIsValidFileName("fileName.avi", ".avi");
            Assertions.assertTrue(isValid);
        }

        @Test
        void validFileNameNoDot() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            boolean isValid = invokeIsValidFileName("fileName.avi", "avi");
            Assertions.assertTrue(isValid);
        }

        @Test
        void invalidExtension() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            boolean isValid = invokeIsValidFileName("fileName.avi", "pdf");
            Assertions.assertFalse(isValid);
        }

        @Test
        void invalidSymbol() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
            boolean isValid = invokeIsValidFileName("<>\"fileName.avi", "avi");
            Assertions.assertFalse(isValid);
        }

        /**
         * Get method through reflection api and invoke it with given parameter
         * @param filePath
         * @param extension
         * @return result of the invoked method
         * @throws NoSuchMethodException
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        private Boolean invokeIsValidFileName(String filePath, String extension) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Method isValidFileName = Interpreter.class.getDeclaredMethod("isValidFileName", String.class, String.class);
            isValidFileName.setAccessible(true);
            Object response = isValidFileName.invoke(interpreter, filePath, extension);
            if(response != null)return (Boolean) response;
            return null;
        }
    }



}