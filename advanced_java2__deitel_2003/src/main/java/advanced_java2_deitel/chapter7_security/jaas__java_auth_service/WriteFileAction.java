package advanced_java2_deitel.chapter7_security.jaas__java_auth_service;

import java.io.*;
import java.security.PrivilegedAction;

public class WriteFileAction implements PrivilegedAction {

    // perform the PrivilegedAction
    public Object run() {
        // attempt to write a message to the specified file
        try {
            File file = new File("D:/", "privilegedFile.txt");
            FileWriter fileWriter = new FileWriter(file);

            // write message to File and close FileWriter
            fileWriter.write("Welcome to JAAS!");
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
