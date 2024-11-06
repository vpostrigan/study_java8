package tutorial_v45.chapter2_connection_management;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpHost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.conn.ConnectionRequest;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoute;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class b_HTTP_connection_managers {

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        // [2.3.1. Managed connections and connection managers]
        HttpClientConnectionManager connMrg = new BasicHttpClientConnectionManager();

        HttpClientContext context = HttpClientContext.create();

        HttpRoute route = new HttpRoute(new HttpHost("localhost", 80));

        // Request new connection. This can be a long process
        ConnectionRequest connRequest = connMrg.requestConnection(route, null);
        // Wait for connection up to 10 sec
        HttpClientConnection conn = connRequest.get(10, TimeUnit.SECONDS);
        try {
            if (!conn.isOpen()) { // If not open
                // establish connection based on its route info
                connMrg.connect(conn, route, 1000, context);
                // and mark it as route complete
                connMrg.routeComplete(conn, route, context);
            }
        } finally {
            connMrg.releaseConnection(conn, null, 1, TimeUnit.MINUTES);
        }

        // The connection request can be terminated prematurely
        // by calling ConnectionRequest#cancel() if necessary.
        // This will unblock the thread blocked in the ConnectionRequest#get() method.



        // [2.3.2. Simple connection manager]
        // BasicHttpClientConnectionManager is a simple connection manager
        // that maintains only one connection at a time.

        // BasicHttpClientConnectionManager will make an effort to reuse the connection
        // for subsequent requests with the same route.



        // [2.3.3. Pooling connection manager]
        // is able to service connection requests from multiple execution threads
        // Connections are pooled on a per route basis

        PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager();
        // Increase max total connection to 200
        cm.setMaxTotal(200);
        // Increase default max connection per route to 20
        cm.setDefaultMaxPerRoute(20);
        // Increase max connections for localhost:80 to 50
        HttpHost localhost = new HttpHost("locahost", 80);
        cm.setMaxPerRoute(new HttpRoute(localhost), 50);
        CloseableHttpClient httpClient = HttpClients.custom()
                .setConnectionManager(cm)
                .build();

        // [2.3.4. Connection manager shutdown]
        // When an HttpClient instance is no longer needed
        // shutdown its connection manager to ensure
        // that all connections kept alive by the manager get closed
        httpClient.close();
    }

}
