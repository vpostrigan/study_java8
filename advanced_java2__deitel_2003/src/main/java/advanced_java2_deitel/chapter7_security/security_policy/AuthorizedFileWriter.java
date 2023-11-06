package advanced_java2_deitel.chapter7_security.security_policy;

import java.io.*;

/**
 * // will show exception
 * -Djava.security.policy=authorized.policy authorize.txt "My text 18"
 * // will work
 * -Djava.security.policy=authorized.policy authorized.txt "My text 18"
 */
public class AuthorizedFileWriter {

    public static void main(String[] args) {
        // create and set security manager
        System.setSecurityManager(new SecurityManager());

        // check command-line arguments for proper usage
        if (args.length != 2)
            System.err.println("Usage: " +
                    "java com.deitel.advjhtp1.security.policyfile.AuthorizedFileWriter file filebody");
        else {
            String file = args[0];
            String fileBody = args[1];

            try {
                FileWriter fileWriter = new FileWriter(file);
                fileWriter.write(fileBody);
                fileWriter.close();
                System.exit(0);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                System.exit(1);
            }
        }
    }

}
