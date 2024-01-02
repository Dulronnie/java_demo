package org.ThreadLocalExample;

import com.sun.org.apache.xerces.internal.util.EntityResolverWrapper;

import java.io.IOException;

/**
 * @author hongchuzhen
 * @date 12/27/2023
 */
public class Application {
    public static void main(String[] args) throws IOException {
        try(
                UserContext userContext = new UserContext()
        ) {
            System.out.println("first new UserContext");
            userContext.setContextUser("this is a userId");
            String contextUser = userContext.getContextUser();
            System.out.println(contextUser);
        }

        UserContext userContext2 = new UserContext();
        String contextUser2 = userContext2.getContextUser();
        System.out.println("second new UserContext");
        System.out.println(contextUser2);
        userContext2.setContextUser("this is a second userId");
        //
        try(
                UserContext userContext3 = new UserContext()
        ) {
            System.out.println("third new UserContext");
            String contextUser = userContext3.getContextUser();
            System.out.println(contextUser);
        }
    }
}
