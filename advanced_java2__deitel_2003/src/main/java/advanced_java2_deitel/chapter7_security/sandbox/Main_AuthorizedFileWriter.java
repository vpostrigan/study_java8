package advanced_java2_deitel.chapter7_security.sandbox;

import java.io.FileWriter;
import java.io.IOException;

// writes to file using a security manager.
// Permissions must be given via policy files.
public class Main_AuthorizedFileWriter {

    public static void main(String[] args) {
        // create and set security manager
        System.setSecurityManager(new SecurityManager());

        // java -Djava.security.policy=authorized.policy com.deitel.advjhtp1.security.policyfile.AuthorizedFileWriter "authorized.txt" "Policy file authorized.policy granted file write permission for file authorized.txt"
        // java -Djava.security.policy=codebase_authorized.policy com.deitel.advjhtp1.security.policyfile.AuthorizedFileWriter "codebase_authorized.txt" "Policy file codebase_authorized.policy granted file write permission for file codebase_authorized.txt to codebase c:/myclasses"

        // C:\Progra~1\Java\jdk1.8.0\bin\java.exe -Djava.security.policy=D:\workspace_study\study\Java8\advanced_java2_deitel\src\main\java\advanced_java2_deitel\chapter7_security\sandbox\authorized.policy "-javaagent:C:\Program Files\JetBrains\IntelliJ IDEA 2022\lib\idea_rt.jar=52486:C:\Program Files\JetBrains\IntelliJ IDEA 2022\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0\jre\lib\rt.jar;D:\workspace_study\study\Java8\advanced_java2_deitel\target\classes;C:\Users\admin\.m2\repository\java3d\j3d-core\1.3.1\j3d-core-1.3.1.jar;C:\Users\admin\.m2\repository\java3d\vecmath\1.3.1\vecmath-1.3.1.jar;C:\Users\admin\.m2\repository\java3d\j3d-core-utils\1.3.1\j3d-core-utils-1.3.1.jar" advanced_java2_deitel.chapter7_security.sandbox.Main_AuthorizedFileWriter authorized.txt "Policy file authorized.policy granted file write permission for file authorized.txt"

        // check command-line arguments for proper usage
        if (args.length != 2)
            System.err.println("Usage: java com.deitel.advjhtp1.security.policyfile.AuthorizedFileWriter file filebody");
        else {
            String file = args[0];
            String fileBody = args[1];

            int r = 0;
            try (FileWriter fileWriter = new FileWriter(file)) {
                fileWriter.write(fileBody);
            } catch (IOException ioException) {
                ioException.printStackTrace();
                r = 1;
            }
            System.exit(r);
        }
    }

}