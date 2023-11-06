package advanced_java2_deitel.chapter7_security.jaas__java_auth_service;

import javax.security.auth.*;
import javax.security.auth.login.*;

public class AuthenticateNT {

    // java -Djava.security.policy=java.policy -Djava.security.auth.policy=jaas.policy -Djava.security.auth.login.config=jaas.config AuthenticateNT
    public static void main(String[] args) {
        // authenticate user and perform PrivilegedAction
        try {
            // create LoginContext for AuthenticateNT context
            LoginContext loginContext = new LoginContext("AuthenticateNT");

            // perform login
            loginContext.login();

            // if login executes without exceptions, login was successful
            System.out.println("Login Successful");

            // get Subject now associated with LoginContext
            Subject subject = loginContext.getSubject();

            // display Subject details
            System.out.println(subject);

            // perform the WriteFileAction as current Subject
            Subject.doAs(subject, new WriteFileAction());

            // log out current Subject
            loginContext.logout();

            System.exit(0);

        } catch (LoginException loginException) {
            loginException.printStackTrace();
            System.exit(-1);
        }
    }
/*
Login Successful
Subject:
	Principal: NTUserPrincipal: admin
	Principal: NTSidUserPrincipal: S-1-5-21-3504735893-3622797146-2951561334-1001
	Principal: NTDomainPrincipal: videal_vp
	Principal: NTSidDomainPrincipal: S-1-5-21-3504735893-3622797146-2951561334
	Principal: NTSidPrimaryGroupPrincipal: S-1-5-21-3504735893-3622797146-2951561334-513
	Principal: NTSidGroupPrincipal: S-1-1-0
	Principal: NTSidGroupPrincipal: S-1-5-114
	Principal: NTSidGroupPrincipal: S-1-5-21-3504735893-3622797146-2951561334-1005
	Principal: NTSidGroupPrincipal: S-1-5-21-3504735893-3622797146-2951561334-1002
	Principal: NTSidGroupPrincipal: S-1-5-21-3504735893-3622797146-2951561334-1004
	Principal: NTSidGroupPrincipal: S-1-5-32-544
	Principal: NTSidGroupPrincipal: S-1-5-32-545
	Principal: NTSidGroupPrincipal: S-1-5-4
	Principal: NTSidGroupPrincipal: S-1-2-1
	Principal: NTSidGroupPrincipal: S-1-5-11
	Principal: NTSidGroupPrincipal: S-1-5-15
	Principal: NTSidGroupPrincipal: S-1-5-113
	Principal: NTSidGroupPrincipal: S-1-5-5-0-37398701
	Principal: NTSidGroupPrincipal: S-1-2-0
	Principal: NTSidGroupPrincipal: S-1-5-64-10
	Principal: NTSidGroupPrincipal: S-1-16-8192
	Public Credential: NTNumericCredential: 1100
 */
}