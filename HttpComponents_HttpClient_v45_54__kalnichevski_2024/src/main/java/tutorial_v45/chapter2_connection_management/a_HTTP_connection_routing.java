package tutorial_v45.chapter2_connection_management;

public class a_HTTP_connection_routing {

    public static void main(String[] args) {
        // [2.2.1. Route computation]
        // HttpClient ships with two default HttpRoutePlanner implementations.
        // SystemDefaultRoutePlanner is based on java.net.ProxySelector

        // By default, it will pick up the proxy settings of the JVM,
        // either from system properties or from the browser running the application.

        // The DefaultProxyRoutePlanner implementation does not make use of any Java system properties,
        // nor any system or browser proxy settings.
        // It always computes routes via the same default proxy.

        // [2.2.2. Secure HTTP connections]

    }

}
