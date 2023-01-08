import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class WebToPdfConverterTest {
    private WebToPdfConverter converter;

    @BeforeEach
    void setUp() {
        converter = new WebToPdfConverter();
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

        /**
         * Get method through reflection api and invoke it with given parameter
         * @param filePath
         * @return result of the invoked method
         * @throws NoSuchMethodException
         * @throws InvocationTargetException
         * @throws IllegalAccessException
         */
        private String invokeGetFileExtension(String filePath) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
            Method getFileExtension = WebToPdfConverter.class.getDeclaredMethod("getFileExtension", String.class);
            getFileExtension.setAccessible(true);
            Object response = getFileExtension.invoke(converter, filePath);
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
        Method getFileExtension = WebToPdfConverter.class.getDeclaredMethod("isValidFileName", String.class, String.class);
        getFileExtension.setAccessible(true);
        Object response = getFileExtension.invoke(converter, filePath, extension);
        if(response != null)return (Boolean) response;
        return null;
    }

}