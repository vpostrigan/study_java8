package image;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

public class IoUtil {

    public static byte[] getResource(Class<?> clazz, String fileName) {
        try (InputStream is = clazz.getResourceAsStream(fileName);) {
            return IOUtils.toByteArray(is);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
